package org.android.wanolza;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import org.android.wanolza.config.AccessTokenInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

public class GlobalClass extends Application {
  
  // TAG
  public final static String TAG = "WANOLZA_APP";
  
  // AccessToken
  public static String ACCESS_TOKEN = "Authorization";
  
  // SharedPreferences
  public static SharedPreferences mSharedPreferences = null;
  
  // OkHttp TimeOut
  public static final int TIME_OUT = 1000;
  
  
  
  // Server Address
  public static final String SERVER_ADDRESS = "http://172.30.1.34:8080/api/v1/";
  
  //BASE_URL
  public static final String BASE_URL = "";
  
  // Global Retrofit Object
  public static Retrofit retrofit;
  
  // success code
  public final static int SUCCES_CODE = 200;
  
  // fail code
  public final static int FAIL_CODE = 500;
  
  // Get Retrofit
  public static Retrofit getRetrofit() {
    
    if (retrofit == null) {
      OkHttpClient client = new OkHttpClient.Builder()
        .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .addNetworkInterceptor(new AccessTokenInterceptor())
        .build();
      
      retrofit = new Retrofit.Builder()
        .baseUrl(SERVER_ADDRESS)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build();
    }
    
    return retrofit;
  }
  
  public static String getAccessToken() {
    return ACCESS_TOKEN;
  }
  
  public static void setAccessToken(String accessToken) {
    ACCESS_TOKEN = accessToken;
  }
  
  public static SharedPreferences getmSharedPreferences() {
    return mSharedPreferences;
  }
  
  public static void setmSharedPreferences(SharedPreferences mSharedPreferences) {
    GlobalClass.mSharedPreferences = mSharedPreferences;
  }
  
  @Override
  public void onCreate() {
    super.onCreate();
    
    if (mSharedPreferences == null) {
      mSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }
  
    RxJavaPlugins.setErrorHandler(e -> {
      if (e instanceof UndeliverableException) {
        e = e.getCause();
      }
      if ((e instanceof IOException) || (e instanceof SocketException)) {
        return;
      }
      if (e instanceof InterruptedException) {
        return;
      }
      if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
        Thread.currentThread().getUncaughtExceptionHandler()
          .uncaughtException(Thread.currentThread(), e);
        return;
      }
      if (e instanceof IllegalStateException) {
        Thread.currentThread().getUncaughtExceptionHandler()
          .uncaughtException(Thread.currentThread(), e);
        return;
      }
      Log.e("RxJava_HOOK", "Undeliverable exception received, not sure what to do" + e.getMessage());
    });
  }
}
