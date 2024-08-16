package com.ioomex.question.app.controller.inner;


import com.ioomex.module.app.entity.Question;
import com.ioomex.module.app.entity.QuestionSubmit;
import com.ioomex.question.app.service.QuestionService;
import com.ioomex.question.app.service.QuestionSubmitService;
import com.ioomex.service.client.service.QuestionFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/inner")
@Api(tags = "内部题目管理")
public class QuestioninnerController implements QuestionFeign {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;


    @Override
    @GetMapping("/get/id")
    @ApiOperation(value = "获取题目", notes = "根据题目 ID 获取题目信息")
    public Question getQuestionById(long questionId) {
        return questionService.getById(questionId);
    }

    @Override
    @GetMapping("/questionsubmit/get/id")
    @ApiOperation(value = "获取题目提交", notes = "根据提交 ID 获取题目提交信息")
    public QuestionSubmit getQvestionSubmitById(long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    @Override
    @PostMapping("/question_submit/update")
    @ApiOperation(value = "更新题目提交", notes = "根据题目提交 ID 更新题目提交信息")
    public boolean updateQuestionSubmitById(QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }
}
