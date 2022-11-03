package org.android.wanolza.signup.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

@Data
public class SignUpBody {
  
  @NonNull
  @SerializedName("userId")
  private String userId;
 
  @NonNull
  @SerializedName("userPw")
  private String userPw;
  
}
