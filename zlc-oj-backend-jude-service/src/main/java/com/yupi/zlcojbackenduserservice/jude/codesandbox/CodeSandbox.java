package com.yupi.zlcojbackenduserservice.jude.codesandbox;


import com.yupi.zlcojbackendjudemode.model.codestandbox.ExecuteCodeRequest;
import com.yupi.zlcojbackendjudemode.model.codestandbox.ExecuteCodeResponse;

public interface CodeSandbox {

    //todo 提供可提供查看代码沙箱状态的接口。。

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
