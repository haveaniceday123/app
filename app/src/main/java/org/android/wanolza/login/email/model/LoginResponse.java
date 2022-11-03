package org.android.wanolza.login.email.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;
@Data
public class LoginResponse {
  
  @SerializedName("code")
  private int code;
  
  @SerializedName("isSuccess")
  private Boolean isSuccess;
  
  @SerializedName("result")
  private String result;
  
}
