package com.ioomex.codeJuge.app.judge;


import com.ioomex.codeJuge.app.judge.strategy.DefaultJudgeStrategy;
import com.ioomex.codeJuge.app.judge.strategy.JavaLanguageJudgeStrategy;
import com.ioomex.codeJuge.app.judge.strategy.JudgeContext;
import com.ioomex.codeJuge.app.judge.strategy.JudgeStrategy;
import com.ioomex.module.app.dto.problem.problemSubmit.JudgeInfo;
import com.ioomex.module.app.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
