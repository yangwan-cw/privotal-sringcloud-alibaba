package com.ioomex.codeJuge.app.judge.codesandbox.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ioomex.codeJuge.app.judge.codesandbox.CodeSandbox;
import com.ioomex.codeJuge.app.judge.codesandbox.model.ExecuteCodeRequest;
import com.ioomex.codeJuge.app.judge.codesandbox.model.ExecuteCodeResponse;
import com.ioomex.common.app.common.ErrorCode;
import com.ioomex.common.app.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
@Slf4j
public class RemoteCodeSandbox implements CodeSandbox {
    private final static String URL = "http://localhost:9000/api/code/native/execute";

    private final static String AUTH = "auth";

    private final static String MD5_HASH_kEY= "auth";



    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("远程代码沙箱");
        String encode = DigestUtil.md5Hex(MD5_HASH_kEY);
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String body = HttpUtil.createPost(URL)
          .header(AUTH,encode)
          .body(json)
          .execute()
          .body();

        if (StrUtil.isEmpty(body)) {
            throw new BusinessException(ErrorCode.REMOTE_ERROR, "远程服务异常");
        }
        ExecuteCodeResponse bean = JSONUtil.toBean(body, ExecuteCodeResponse.class);

        return bean;
    }
}
