package com.yupi.zlcojbackenduserservice.jude.codesandbox.impl;


import com.yupi.zlcojbackendjudemode.model.codestandbox.ExecuteCodeRequest;
import com.yupi.zlcojbackendjudemode.model.codestandbox.ExecuteCodeResponse;
import com.yupi.zlcojbackenduserservice.jude.codesandbox.CodeSandbox;

/**
 * 第三方代码沙箱
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("三方接口沙箱");
        return  null;
    }
}
