package com.yupi.zlcojbackendjudemode.model.codestandbox;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    //todo timelimit 可加可不加, 可自行扩展, 及时中断程序.

    private List<String> outputList;

    //接口信息
    private String message;

    // 执行状态
    private Integer status;

    private JudeInfo judeInfo;

}
