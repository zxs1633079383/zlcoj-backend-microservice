package com.yupi.zlcojbackendserviceclient.service;


import com.yupi.zlcojbackendjudemode.model.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {

    /**
     * 判断业务
     *
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit duJudge(long questionSubmitId);


}
