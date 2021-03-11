package com.theone.tools.horde.service;

import com.theone.tools.horde.bean.Im.DingUserInfoResp;
import com.theone.tools.horde.bean.Im.DingUserSimple;

import java.util.List;

public interface ImRemoteService {

    String token();

    List<DingUserSimple> userList(String token);

    DingUserInfoResp userInfo(String token, String userid);
}
