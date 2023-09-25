package com.yupi.zlcojbackendquestionservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.zlcojbackendjudemode.model.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 16330
* @description 针对表【question(题目)】的数据库操作Mapper
* @createDate 2023-09-12 14:22:30
* @Entity com.yupi.zlcoj.model.entity.Question
*/
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}




