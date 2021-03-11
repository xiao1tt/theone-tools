package com.theone.tools.oa.client;

import com.theone.common.base.lang.BizException;
import com.theone.tools.oa.client.bean.OaBaseResp;

public class OaUtils {
    public static void check(OaBaseResp resp) {
        if (resp.getErrCode() != 0) {
            throw new BizException("请求 OA 异常，code:" + resp.getErrCode() + ", msg:" + resp.getErrMsg());
        }
    }
}
