package com.ioomex.service.client.service;


import com.ioomex.module.app.entity.Question;
import com.ioomex.module.app.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "olecode-question-service", path = "/api/question/inner")
public interface QuestionFeign {

    @PostMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") long questionId);

    @PostMapping("/questionsubmit/get/id")
    QuestionSubmit getQvestionSubmitById(@RequestParam("guestionId") long questionSubmitId);

    @PostMapping("/question _submit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);

}
