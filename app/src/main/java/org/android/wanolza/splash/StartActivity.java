package org.android.wanolza.splash;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import org.android.wanolza.MainActivity;
import org.android.wanolza.R;
import org.android.wanolza.base.BaseActivity;
import org.android.wanolza.login.LoginActivity;
import org.android.wanolza.splash.interfaces.SplashActivityView;

import static org.android.wanolza.GlobalClass.TAG;
import static org.android.wanolza.GlobalClass.SUCCES_CODE;

public class StartActivity extends BaseActivity implements SplashActivityView, Animator.AnimatorListener {
  SplashService splashService;
  
  private boolean isJwtChecked = false;
  
  LottieAnimationView animationView;
  
  Handler handler;
  
  int code = 0;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    animationView = findViewById(R.id.lottie_location);
    splashService = new SplashService(this, animationView);
    checkJwt();
  }
  
  @Override
  protected void onStart() {
    super.onStart();
  }
  
  public void goLoginActivity() {
    Intent intent;
    Log.d("<<<<<", code + "");
    if (code != 200) {
      intent = new Intent(this, LoginActivity.class);
    } else {
      intent = new Intent(this, MainActivity.class);
    }
    startActivity(intent);
    finish();
  }
  
  public void checkJwt() {
    splashService.validateToken();
  }
  
  @Override
  public void getJwtSuccess(int code, String message) {
    this.code = code;
    isJwtChecked = true;
  }
  
  public boolean getJwtChecked() {
    return isJwtChecked;
  }
  
  @Override
  public void getJwtFail(int code, String message) {
    isJwtChecked = true;
  }
  
  @Override
  public void toast(String s) {
    toastMessage(s);
  }
  
  @Override
  protected void onStop() {
    super.onStop();
  }
  
  @Override
  public void onAnimationStart(Animator animation) {

  }
  
  @Override
  public void onAnimationEnd(Animator animation) {
    Log.d("END", "ENDDDDDDDDDDD");
  
    handler.post(new Runnable() {
      @Override
      public void run() {
        if (isJwtChecked) {
          goLoginActivity();
        } else {
          animationView.playAnimation();
        }
      }
    });
  }
  
  @Override
  public void onAnimationCancel(Animator animation) {
  
  }
  
  @Override
  public void onAnimationRepeat(Animator animation) {
  
  }
}