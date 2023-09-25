package com.yupi.zlcojbackenduserservice.jude.strategy;

import cn.hutool.json.JSONUtil;
import com.yupi.zlcojbackendjudemode.model.codestandbox.JudeInfo;
import com.yupi.zlcojbackendjudemode.model.dto.question.JudeCase;
import com.yupi.zlcojbackendjudemode.model.dto.question.JudeConfig;
import com.yupi.zlcojbackendjudemode.model.entity.Question;
import com.yupi.zlcojbackendjudemode.model.enums.JudeInfoMessageEnum;


import java.util.List;
import java.util.Optional;

/**
 * 默认判题策略
 */
public class JavaLanguageJudeStrategyImpl implements JudeStrategy {

    /**
     * 执行判题
     *
     * @param judeContext
     * @return
     */
    @Override
    public JudeInfo doJude(JudeContext judeContext) {
        JudeInfo judeInfo = judeContext.getJudeInfo();
        List<String> inputList = judeContext.getInputList();
        List<String> outputList = judeContext.getOutputList();
        Question question = judeContext.getQuestion();
        List<JudeCase> judeCaseList = judeContext.getJudeCaseList();

        //todo 获取程序运行时间和内存消耗
        JudeInfoMessageEnum judeInfoMessageEnum = JudeInfoMessageEnum.WAITING;
        Long memory = Optional.ofNullable(judeInfo.getMemory()).orElse(0L);
        Long time = Optional.ofNullable(judeInfo.getTime()).orElse(0L);
        JudeInfo judeInfoResponse = new JudeInfo();
        judeInfoResponse.setMemory(memory);
        judeInfoResponse.setTime(time);

        // 判断预期长度是否相等
        if (outputList.size() != inputList.size()) {
            judeInfoMessageEnum = JudeInfoMessageEnum.WRONG_ANSWER;
            judeInfoResponse.setMessage(judeInfoMessageEnum.getValue());
            return judeInfoResponse;
        }
        //判断每一项
        for (int i = 0; i < judeCaseList.size(); i++) {
            JudeCase judeCaseLs = judeCaseList.get(i);
            if (!judeCaseLs.getOutput().equals(outputList.get(i))) {
                judeInfoMessageEnum = JudeInfoMessageEnum.WRONG_ANSWER;
                judeInfoResponse.setMessage(judeInfoMessageEnum.getValue());
                return judeInfoResponse;
            }
        }
        // 判断题目限制

        String judeConfigstr = question.getJudeConfig();
        JudeConfig judeConfig = JSONUtil.toBean(judeConfigstr, JudeConfig.class);
        Long memoryLimit = judeConfig.getMemoryLimit();
        Long timeLimit = judeConfig.getTimeLimit();

//        if (memoryLimit < memory) {
//            judeInfoMessageEnum = JudeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
//            judeInfoResponse.setMessage(judeInfoMessageEnum.getValue());
//            return judeInfoResponse;
//        }
        // java 逻辑额外执行一段时间
        long JAVA_PROGRAM_TIME_COST = 10000L;
        if (timeLimit < time - JAVA_PROGRAM_TIME_COST) {
            judeInfoMessageEnum = JudeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judeInfoResponse.setMessage(judeInfoMessageEnum.getValue());
            return judeInfoResponse;
        }

        judeInfoResponse.setMessage(JudeInfoMessageEnum.ACCEPTED.getValue());

        return judeInfoResponse;


    }
}
