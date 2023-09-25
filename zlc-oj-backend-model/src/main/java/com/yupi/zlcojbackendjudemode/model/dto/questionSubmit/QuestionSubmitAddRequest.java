package com.yupi.zlcojbackendjudemode.model.dto.questionSubmit;

import lombok.Data;

import java.io.Serializable;

/***
 * 提交题目
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {


    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;



    /**
     * 题目 id
     */
    private Long questionId;



}
