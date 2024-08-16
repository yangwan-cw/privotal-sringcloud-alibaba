package com.ioomex.question.app.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ioomex.module.app.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangwan
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}