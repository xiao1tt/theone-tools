package com.theone.tools.sso.client;

import cn.hutool.http.HttpUtil;
import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
import com.theone.common.base.json.JSON;
import com.theone.common.base.lang.APIResponse;
import com.theone.common.base.lang.BizException;

import java.util.HashMap;

/**
 * @author chenxiaotong
 */
public class IUserClient {

    private final String serverDomain = "http://127.0.0.1:8001";
    private final String userPath = "/sso/auth";

    public IUser query(String token) {
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("token", token);

        String res = HttpUtil.get(serverDomain + userPath, param);
        APIResponse<IUser> response = JSON.fromJson(res, new TypeToken<APIResponse<IUser>>() {}.getType());

        if (!response.ok()) {
            throw new BizException("token 获取用户异常，" + response.getMsg());
        }

        return response.getData();
    }
}
