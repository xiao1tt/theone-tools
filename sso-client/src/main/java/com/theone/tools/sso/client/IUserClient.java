package com.theone.tools.sso.client;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Maps;
import com.theone.common.base.lang.APIResponse;
import com.theone.common.base.lang.BizException;

import java.util.HashMap;
import java.util.List;

/**
 * @author chenxiaotong
 */
public class IUserClient {

    private final String serverDomain = "http://127.0.0.1:8001";
    private final String userInfoPath = "/sso/auth";
    private final String userListPath = "/sso/list";

    public IUser query(String token) {
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("token", token);

        String res = HttpUtil.get(serverDomain + userInfoPath, param);
        APIResponse<IUser> response = JSON.parseObject(res, new TypeReference<APIResponse<IUser>>() {
        }, Feature.AllowComment);

        if (!response.ok()) {
            throw new BizException("token 获取用户异常，" + response.getMsg());
        }

        return response.getData();
    }

    public List<IUser> all() {
        String res = HttpUtil.get(serverDomain + userListPath);
        APIResponse<List<IUser>> response = JSON.parseObject(res, new TypeReference<APIResponse<List<IUser>>>() {
        }, Feature.AllowComment);

        if (!response.ok()) {
            throw new BizException("token 获取用户列表异常，" + response.getMsg());
        }

        return response.getData();
    }
}
