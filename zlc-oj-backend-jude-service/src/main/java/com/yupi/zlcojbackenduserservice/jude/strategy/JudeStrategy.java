package com.yupi.zlcojbackenduserservice.jude.strategy;


import com.yupi.zlcojbackendjudemode.model.codestandbox.JudeInfo;

/**
 * 判题策略
 */
public interface JudeStrategy {

    /**
     * 执行判题
     *
     * @param judeContext
     * @return
     */
    JudeInfo doJude(JudeContext judeContext);
}
