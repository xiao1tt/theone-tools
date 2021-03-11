package com.theone.tools.oa.client.bean;

import com.google.gson.annotations.SerializedName;

public class OaBaseResp {

    @SerializedName("errcode")
    private Long errCode;
    @SerializedName("errmsg")
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
