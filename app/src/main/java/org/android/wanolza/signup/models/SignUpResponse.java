package org.android.wanolza.signup.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SignUpResponse {
  
  @SerializedName("code")
  private int code;
  
  @SerializedName("message")
  private String message;
  
}
