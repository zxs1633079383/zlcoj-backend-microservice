package com.yupi.zlcojbackenduserservice.controller;

import com.yupi.zlcojbackendjudemode.model.entity.QuestionSubmit;
import com.yupi.zlcojbackendserviceclient.service.JudgeFeignClient;
import com.yupi.zlcojbackenduserservice.jude.JudgeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/inner")
@RestController
public class JudgeController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;

    @Override
    @PostMapping("/do")
    public QuestionSubmit duJudge(@RequestParam("questionSubmitId") long questionSubmitId) {
        return judgeService.duJudge(questionSubmitId);
    }
}
