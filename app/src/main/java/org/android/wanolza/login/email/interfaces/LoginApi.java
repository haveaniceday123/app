package org.android.wanolza.login.email.interfaces;

import io.reactivex.rxjava3.core.Single;
import org.android.wanolza.login.email.model.LoginBody;
import org.android.wanolza.login.email.model.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginApi {
  
  @POST("login")
  Single<LoginResponse> postLogin(@Body LoginBody params);
  
}
