
package com.theone.tools.horde.bean.Im;

import com.google.gson.annotations.SerializedName;
import com.theone.tools.oa.client.bean.OaBaseResp;

import java.util.List;

/**
 * @author chenxiaotong
 */
public class DingUserListResp extends OaBaseResp {

    @SerializedName("userlist")
    private List<DingUserSimple> userList;

    public List<DingUserSimple> getUserList() {
        return userList;
    }

    public void setUserList(List<DingUserSimple> userList) {
        this.userList = userList;
    }
}
