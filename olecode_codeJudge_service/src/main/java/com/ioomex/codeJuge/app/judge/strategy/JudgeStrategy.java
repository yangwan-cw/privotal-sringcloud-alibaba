package com.ioomex.codeJuge.app.judge.strategy;


import com.ioomex.module.app.dto.problem.problemSubmit.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
