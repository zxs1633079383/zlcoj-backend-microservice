package com.yupi.zlcojbackenduserservice.jude.codesandbox;


import com.yupi.zlcojbackenduserservice.jude.codesandbox.impl.ExampleCodeSandboxImpl;
import com.yupi.zlcojbackenduserservice.jude.codesandbox.impl.RemoteCodeSandboxImpl;
import com.yupi.zlcojbackenduserservice.jude.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 工厂模式 根据字符串创建不同的代码沙箱
 */
public class CodeSandboxFactory {


    /**
     * 创建代码沙箱的类型
     *
     * @param type
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        //todo 扩展 实现单例模式
        if (type.equals("example")) {
            return new ExampleCodeSandboxImpl();
        } else if (type.equals("remote")) {
            return new RemoteCodeSandboxImpl();
        } else if (type.equals("thirdParty")) {
            return new ThirdPartyCodeSandbox();
        } else {
            return new ExampleCodeSandboxImpl();
        }
    }

}
