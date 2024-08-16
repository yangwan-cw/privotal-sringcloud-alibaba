package com.ioomex.codeJuge.app.judge.controller;

import com.ioomex.codeJuge.app.judge.JudgeService;
import com.ioomex.module.app.entity.QuestionSubmit;
import com.ioomex.service.client.service.JudgeFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/inner")
@Api(tags = "内部评测管理")
public class QuestioninnerController implements JudgeFeign {

    @Resource
    private JudgeService judgeService;

    @Override
    @GetMapping("/do")
    @ApiOperation(value = "执行评测", notes = "根据提交 ID 执行评测，并返回评测结果")
    public QuestionSubmit doJudge(long questionSubmitId) {
        return judgeService.doJudge(questionSubmitId);
    }
}
