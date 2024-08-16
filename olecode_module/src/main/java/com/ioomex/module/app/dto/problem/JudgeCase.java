package com.ioomex.module.app.dto.problem;

import lombok.Data;

/**
 * 题目用例
 * @author yangwan
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
