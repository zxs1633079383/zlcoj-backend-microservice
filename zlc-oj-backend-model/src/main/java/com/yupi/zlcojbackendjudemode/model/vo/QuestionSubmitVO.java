package com.yupi.zlcojbackendjudemode.model.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yupi.zlcojbackendjudemode.model.codestandbox.JudeInfo;
import com.yupi.zlcojbackendjudemode.model.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子视图
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class QuestionSubmitVO implements Serializable {

    private final static Gson GSON = new Gson();

    /**
     * id
     */
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息(json duix)
     */
    private JudeInfo judeInfo;

    /**
     * 判题状态 (0 待判题, 1 判题中, 2 判题成功,3失败)
     */
    private Integer status;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private UserVO userVO;

    private QuestionVO questionVO;

    /**
     * 包装类转对象
     *
     * @return
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        JudeInfo judeInfo1 = questionSubmitVO.getJudeInfo();
        if (judeInfo1 != null) {
            questionSubmit.setJudeInfo(GSON.toJson(judeInfo1));
        }



        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     *
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        questionSubmitVO.setJudeInfo(GSON.fromJson(questionSubmit.getJudeInfo(),new TypeToken<JudeInfo>(){}.getType()));


        return questionSubmitVO;
    }




}
