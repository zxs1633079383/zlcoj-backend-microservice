package com.yupi.zlcojbackendserviceclient.service;


import com.yupi.zlcojbackendjudemode.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 判题服务
 */
@FeignClient(name = "zlc-oj-backend-jude-service", path = "/api/judge/inner")
public interface JudgeFeignClient {

    /**
     * 判断业务
     *
     * @param questionSubmitId
     * @return
     */
    @PostMapping("/do")
    QuestionSubmit duJudge(@RequestParam("questionSubmitId") long questionSubmitId);


}
