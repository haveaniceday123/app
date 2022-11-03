package org.android.wanolza.config;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.android.wanolza.GlobalClass.mSharedPreferences;
import static org.android.wanolza.GlobalClass.ACCESS_TOKEN;
import static org.android.wanolza.GlobalClass.BASE_URL;

public class AccessTokenInterceptor implements Interceptor {
  
  
  @NotNull
  @Override
  public Response intercept(@NotNull Chain chain) throws IOException {
    final Request.Builder builder = chain.request().newBuilder();
    final String jwtToken = mSharedPreferences.getString(ACCESS_TOKEN, null);
    StringBuilder sb = new StringBuilder("bearer ");
    if (jwtToken != null) {
      builder.addHeader(ACCESS_TOKEN, sb.append(jwtToken).toString());
    }
    
//    StringBuilder sb = new StringBuilder(chain.request().url().toString());
//    Log.d("WANOLZA", sb.append(BASE_URL).toString());
//
//    builder.url(sb.append(BASE_URL).toString());
    return chain.proceed(builder.build());
  }
}
