package org.android.wanolza.login.email.model;

import com.google.gson.annotations.SerializedName;


public class LoginBody {
  
  @SerializedName("userId")
  private String userId;
  
  @SerializedName("userPw")
  private String userPw;
  
  public LoginBody(String userId, String userPw) {
    this.userId = userId;
    this.userPw = userPw;
  }
  
  public String getUserId() {
    return userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getUserPw() {
    return userPw;
  }
  
  public void setUserPw(String userPw) {
    this.userPw = userPw;
  }
}
