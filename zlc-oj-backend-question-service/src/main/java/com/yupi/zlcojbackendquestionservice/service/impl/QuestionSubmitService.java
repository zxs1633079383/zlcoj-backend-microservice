package com.yupi.zlcojbackendquestionservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.zlcojbackendjudemode.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.yupi.zlcojbackendjudemode.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.yupi.zlcojbackendjudemode.model.entity.QuestionSubmit;
import com.yupi.zlcojbackendjudemode.model.entity.User;
import com.yupi.zlcojbackendjudemode.model.vo.QuestionSubmitVO;


/**
 * @author 16330
 * @description 针对表【question_submit(题目提交表)】的数据库操作Service
 * @createDate 2023-09-12 14:22:31
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {


    /**
     * 题目提交
     *
     * @param question
     * @param loginnUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest question, User loginnUser);


    /**
     * 获取查询条件
     *
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取帖子封装
     *
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取帖子封装
     *
     * @param questionPage
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionPage, User loginUser);

}
