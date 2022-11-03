package org.android.wanolza.splash.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SplashResponse {
  
  @SerializedName("code")
  private int mCode;
  
  @SerializedName("isSuccess")
  private Boolean mIsSuccess;
  
  @SerializedName("message")
  private String mMessage;
  

}
