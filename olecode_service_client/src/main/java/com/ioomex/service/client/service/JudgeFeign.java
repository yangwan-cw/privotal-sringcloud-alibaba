package com.ioomex.service.client.service;


import com.ioomex.module.app.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 判题服务
 */
@FeignClient(name="olecode-judge-service",path="/api/judge/inner")
public interface JudgeFeign {

    @GetMapping("/do")
    QuestionSubmit doJudge(long questionSubmitId);
}
