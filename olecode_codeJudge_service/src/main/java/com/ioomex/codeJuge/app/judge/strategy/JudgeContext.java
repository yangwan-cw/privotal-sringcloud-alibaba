package com.ioomex.codeJuge.app.judge.strategy;


import com.ioomex.module.app.dto.problem.JudgeCase;
import com.ioomex.module.app.dto.problem.problemSubmit.JudgeInfo;
import com.ioomex.module.app.entity.Question;
import com.ioomex.module.app.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
