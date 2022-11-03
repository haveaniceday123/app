package org.android.wanolza.splash.interfaces;

import io.reactivex.rxjava3.core.Single;
import org.android.wanolza.splash.models.SplashResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashRetrofitInterface {

  @GET("tokenChk")
  Single<SplashResponse> getJwt();
  
}
