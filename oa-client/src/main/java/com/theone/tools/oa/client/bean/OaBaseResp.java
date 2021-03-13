package com.theone.tools.oa.client.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class OaBaseResp {

    @JSONField(name = "errcode")
    private Long errCode;
    @JSONField(name = "errmsg")
    private String errMsg;

    public Long getErrCode() {
        return errCode;
    }

    public void setErrCode(Long errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
