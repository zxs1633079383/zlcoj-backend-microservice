package com.yupi.zlcojbackendjudemode.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class QuestionUpdateRequest implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 标准答案
     */
    private String answer;



    /**
     * 判题用例(json 数据)
     */
    private List<JudeCase> judeCase;
//    private String judeCase;

    /**
     * 判题配置(json 对象)
     */
    private JudeConfig judeConfig;

    private static final long serialVersionUID = 1L;
}