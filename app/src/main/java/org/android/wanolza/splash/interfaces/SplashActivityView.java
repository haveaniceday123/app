package org.android.wanolza.splash.interfaces;

public interface SplashActivityView {
  
  void getJwtSuccess(int code, String message);
  
  void getJwtFail(int code, String message);
  
  void toast(String s);
}
