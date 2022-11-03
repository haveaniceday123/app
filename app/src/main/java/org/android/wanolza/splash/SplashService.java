package org.android.wanolza.splash;

import android.animation.Animator;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.android.wanolza.splash.interfaces.SplashActivityView;
import org.android.wanolza.splash.interfaces.SplashRetrofitInterface;
import org.android.wanolza.splash.models.SplashResponse;

import java.util.concurrent.TimeUnit;

import static org.android.wanolza.GlobalClass.TAG;
import static org.android.wanolza.GlobalClass.SUCCES_CODE;
import static org.android.wanolza.GlobalClass.FAIL_CODE;

import static org.android.wanolza.GlobalClass.getRetrofit;

public class SplashService implements Animator.AnimatorListener {
  
  private final StartActivity startActivity;
  
  CompositeDisposable disposable  = new CompositeDisposable();
  
  LottieAnimationView view;
  
  Handler handler;
  
  public SplashService(StartActivity startActivity, LottieAnimationView view) {
    this.startActivity = startActivity;
    this.view = view;
    
    view.addAnimatorListener(this);
    view.playAnimation();
    handler = new Handler(Looper.getMainLooper());
  }
  
  final SplashRetrofitInterface api =
    getRetrofit().create(SplashRetrofitInterface.class);
  
  public void validateToken() {
  
    disposable.add(
      api.getJwt()
        .subscribeOn(Schedulers.io())
        .delay(2000L, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<SplashResponse>() {
          @Override
          public void onSuccess(@NonNull SplashResponse splashResponse) {
            int code = splashResponse.getMCode();
            Log.d("<<<<<<<<<<<", splashResponse.toString());
  
            if (code == SUCCES_CODE) {
              startActivity.getJwtSuccess(splashResponse.getMCode(), splashResponse.getMMessage());
              Log.d("<<<<<<<<<<<", splashResponse.toString());
            } else if (code == FAIL_CODE) {
              startActivity.getJwtFail(splashResponse.getMCode(), splashResponse.getMMessage());
            }
            
          }
        
          @Override
          public void onError(@NonNull Throwable e) {
            e.printStackTrace();
            startActivity.toast("서버에 연결할 수 없습니다.");
          }
        })
    );

    
  }
  
  @Override
  public void onAnimationStart(Animator animation) {
  
  }
  
  @Override
  public void onAnimationEnd(Animator animation) {
    
    handler.post(new Runnable() {
      @Override
      public void run() {
        if (startActivity.getJwtChecked()) {
          startActivity.goLoginActivity();
        } else {
          view.playAnimation();
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
