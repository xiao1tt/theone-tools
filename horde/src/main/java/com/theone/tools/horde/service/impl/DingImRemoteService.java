package com.theone.tools.horde.service.impl;

import com.google.common.collect.Maps;
import com.theone.common.base.http.HttpClient;
import com.theone.tools.horde.bean.Im.DingUserInfoResp;
import com.theone.tools.horde.bean.Im.DingUserListResp;
import com.theone.tools.horde.bean.Im.DingUserSimple;
import com.theone.tools.horde.service.ImRemoteService;
import com.theone.tools.oa.client.OaClient;
import com.theone.tools.oa.client.OaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author chenxiaotong
 */
@Service
public class DingImRemoteService implements ImRemoteService {

    private final HttpClient httpClient = new HttpClient();
    private final OaClient oaClient = new OaClient();

    @Value("${im.ding.app.key}")
    private String appKey;
    @Value("${im.ding.app.secret}")
    private String appSecret;

    @Value("${im.ding.user.list.url}")
    private String userListUrl;
    @Value("${im.ding.user.info.url}")
    private String userInfoUrl;

    @Override
    public String token() {
        return oaClient.token(appKey, appSecret);
    }

    @Override
    public List<DingUserSimple> userList(String token) {
        Map<String, String> param = Maps.newHashMap();
        param.put("token", token);
        DingUserListResp userListResp = httpClient.get(userListUrl, param, DingUserListResp.class);
        OaUtils.check(userListResp);
        return userListResp.getUserList();
    }

    @Override
    public DingUserInfoResp userInfo(String token, String userid) {
        Map<String, String> param = Maps.newHashMap();
        param.put("token", token);
        param.put("userid", userid);
        DingUserInfoResp userInfoResp = httpClient.get(userInfoUrl, param, DingUserInfoResp.class);
        OaUtils.check(userInfoResp);
        return userInfoResp;
    }
}
