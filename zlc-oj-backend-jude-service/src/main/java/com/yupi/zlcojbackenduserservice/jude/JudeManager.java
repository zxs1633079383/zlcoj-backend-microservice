package com.yupi.zlcojbackenduserservice.jude;


import com.yupi.zlcojbackendjudemode.model.codestandbox.JudeInfo;
import com.yupi.zlcojbackendjudemode.model.entity.QuestionSubmit;
import com.yupi.zlcojbackenduserservice.jude.strategy.DefaultJudeStrategyImpl;
import com.yupi.zlcojbackenduserservice.jude.strategy.JavaLanguageJudeStrategyImpl;
import com.yupi.zlcojbackenduserservice.jude.strategy.JudeContext;
import com.yupi.zlcojbackenduserservice.jude.strategy.JudeStrategy;
import org.springframework.stereotype.Service;

/**
 * 判题管理 简化调用
 */
@Service
public class JudeManager {

    /**
     * 执行判题
     * @param judeContext
     * @return
     */
    JudeInfo doJuge(JudeContext judeContext){
        QuestionSubmit questionSubmit = judeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudeStrategy judeStrategy = new DefaultJudeStrategyImpl();
        if("java".equals(language)){
            judeStrategy = new JavaLanguageJudeStrategyImpl();
        }

        return  judeStrategy.doJude(judeContext);

    }


}

