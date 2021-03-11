
package com.theone.tools.oa.client.bean;

import com.google.gson.annotations.SerializedName;

public class OaTokenResp extends OaBaseResp {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

}
