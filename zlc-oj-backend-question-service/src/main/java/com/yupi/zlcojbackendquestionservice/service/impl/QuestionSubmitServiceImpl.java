package com.yupi.zlcojbackendquestionservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yupi.zlcojbackendjudecommon.common.ErrorCode;
import com.yupi.zlcojbackendjudecommon.constant.CommonConstant;
import com.yupi.zlcojbackendjudecommon.exception.BusinessException;
import com.yupi.zlcojbackendjudecommon.utils.SqlUtils;
import com.yupi.zlcojbackendjudemode.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.yupi.zlcojbackendjudemode.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.yupi.zlcojbackendjudemode.model.entity.Question;
import com.yupi.zlcojbackendjudemode.model.entity.QuestionSubmit;
import com.yupi.zlcojbackendjudemode.model.entity.User;
import com.yupi.zlcojbackendjudemode.model.enums.QuestionSubmitLanguageEnum;
import com.yupi.zlcojbackendjudemode.model.enums.QuestionSubmitStatusEnum;
import com.yupi.zlcojbackendjudemode.model.vo.QuestionSubmitVO;
import com.yupi.zlcojbackendquestionservice.mapper.QuestionSubmitMapper;
import com.yupi.zlcojbackendserviceclient.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author 16330
 * @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
 * @createDate 2023-09-12 14:22:31
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitMapper questionSubmitMapper;

    @Resource
    private UserFeignClient userFeightClient;

    @Resource
    @Lazy //循环依赖, 按需加载
    private JudgeFeignClient judgeFeightClient;

    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginnUser) {
        // todo 检验编程语言是否范围内.
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum enumByValue = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (enumByValue == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }

        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }


        //是否已提交题目
        long userId = loginnUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setLanguage(language);
//        questionSubmit.setCode("123");
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setUserId(userId);
        //设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudeInfo("{}");
        boolean save = this.save(questionSubmit);
//        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目提交失败");
        }

        Long questionSubmitId = questionSubmit.getId();
        //todo 执行判题服务
        CompletableFuture.runAsync(() -> {
            QuestionSubmit questionSubmit1 = judgeFeightClient.duJudge(questionSubmitId);

        });

        return questionSubmitId;

    }

    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {


        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }

        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();


        // 拼接查询条件
        queryWrapper.eq(StringUtils.isNotEmpty(language), "language", language);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);

        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }


    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        long questionSubmitUserId = questionSubmit.getUserId();
        Long userId = loginUser.getId();
        if (userId != questionSubmitUserId && !userFeightClient.isAdmin(loginUser)) {
            questionSubmitVO.setCode(null);

        }


        return questionSubmitVO;
        // 2. 已登录，获取用户点赞、收藏状态
//        User loginUser = userFeightClient.getLoginUserPermitNull(request);
//        if (loginUser != null) {
//            // 获取点赞
//            QueryWrapper<QuestionSubmitThumb> questionSubmitThumbQueryWrapper = new QueryWrapper<>();
//            questionSubmitThumbQueryWrapper.in("questionSubmitId", questionSubmitId);
//            questionSubmitThumbQueryWrapper.eq("userId", loginUser.getId());
//            QuestionSubmitThumb questionSubmitThumb = questionSubmitThumbMapper.selectOne(questionSubmitThumbQueryWrapper);
//            questionSubmitVO.setHasThumb(questionSubmitThumb != null);
//            // 获取收藏
//            QueryWrapper<QuestionSubmitFavour> questionSubmitFavourQueryWrapper = new QueryWrapper<>();
//            questionSubmitFavourQueryWrapper.in("questionSubmitId", questionSubmitId);
//            questionSubmitFavourQueryWrapper.eq("userId", loginUser.getId());
//            QuestionSubmitFavour questionSubmitFavour = questionSubmitFavourMapper.selectOne(questionSubmitFavourQueryWrapper);
//            questionSubmitVO.setHasFavour(questionSubmitFavour != null);
//        }

    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollectionUtils.isEmpty(questionSubmitList)) {
            return questionSubmitVOPage;
            // todo
//            return  null;
        }

        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream().map(questionSubmit -> {
            return getQuestionSubmitVO(questionSubmit, loginUser);
        }).collect(Collectors.toList());

        questionSubmitVOPage.setRecords(questionSubmitVOList);

        return questionSubmitVOPage;
    }

}




