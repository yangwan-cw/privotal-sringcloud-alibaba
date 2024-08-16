package com.ioomex.question.app.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ioomex.module.app.dto.problem.problemSubmit.QuestionSubmitAddRequest;
import com.ioomex.module.app.dto.problem.problemSubmit.QuestionSubmitQueryRequest;
import com.ioomex.module.app.entity.QuestionSubmit;
import com.ioomex.module.app.entity.SysUser;
import com.ioomex.module.app.vo.QuestionSubmitVO;

public interface QuestionSubmitService extends IService<QuestionSubmit> {

    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, SysUser loginUser);


    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, SysUser loginUser);


    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, SysUser loginUser);

}
