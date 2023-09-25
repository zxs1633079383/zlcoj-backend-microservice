package com.yupi.zlcojbackendjudemode.model.codestandbox;

import lombok.Data;

/***
 * 题目判题消息
 */
@Data
public class JudeInfo {

    private String message;

    private Long memory;

    private Long time;
}
