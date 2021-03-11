package com.theone.tools.oa.client;

import com.google.common.collect.Maps;
import com.theone.common.base.http.HttpClient;
import com.theone.tools.oa.client.bean.OaTokenResp;

import java.util.Map;

/**
 * @author chenxiaotong
 */
public class OaClient {
    private final HttpClient httpClient = new HttpClient();

    private final String tokenUrl = "https://oapi.dingtalk.com/gettoken?appkey={key}&appsecret={secret}";

    public String token(String appKey, String appSecret) {
        Map<String, String> param = Maps.newHashMap();
        param.put("key", appKey);
        param.put("secret", appSecret);
        OaTokenResp tokenResp = httpClient.get(tokenUrl, param, OaTokenResp.class);
        OaUtils.check(tokenResp);
        return tokenResp.getAccessToken();
    }
}
