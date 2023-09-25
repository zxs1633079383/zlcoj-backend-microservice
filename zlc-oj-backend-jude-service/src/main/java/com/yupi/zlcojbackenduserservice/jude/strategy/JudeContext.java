package com.yupi.zlcojbackenduserservice.jude.strategy;


import com.yupi.zlcojbackendjudemode.model.codestandbox.JudeInfo;
import com.yupi.zlcojbackendjudemode.model.dto.question.JudeCase;
import com.yupi.zlcojbackendjudemode.model.entity.Question;
import com.yupi.zlcojbackendjudemode.model.entity.QuestionSubmit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 上下文(在策略上下文传递的信息)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class JudeContext {

    private JudeInfo judeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudeCase> judeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
