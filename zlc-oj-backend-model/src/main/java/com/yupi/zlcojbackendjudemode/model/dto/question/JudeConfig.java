package com.yupi.zlcojbackendjudemode.model.dto.question;

import lombok.Data;

@Data
public class JudeConfig {

    private Long timeLimit;

    private Long memoryLimit;

    private Long stackLimit;
}
