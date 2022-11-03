package org.android.wanolza.signup.interfaces;

import io.reactivex.rxjava3.core.Single;
import org.android.wanolza.signup.models.SignUpBody;
import org.android.wanolza.signup.models.SignUpResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpApi {
  
  @POST("signUp")
  Single<SignUpResponse> signUp (@Body SignUpBody params);
  
}
