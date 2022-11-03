package org.android.wanolza.login.email;

import android.content.SharedPreferences;
import android.util.Log;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.android.wanolza.login.email.interfaces.LoginApi;
import org.android.wanolza.login.email.interfaces.LoginFragmentInterface;
import org.android.wanolza.login.email.model.LoginBody;
import org.android.wanolza.login.email.model.LoginResponse;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.android.wanolza.GlobalClass.*;

public class LoginService {
  
  private final LoginFragmentInterface loginFragmentInterface;
  
  CompositeDisposable disposable = new CompositeDisposable();
  
  
  final LoginApi api =
    getRetrofit().create(LoginApi.class);
  
  
  public LoginService(LoginFragmentInterface loginFragmentInterface) {
    this.loginFragmentInterface = loginFragmentInterface;
  }
  
  Single<LoginResponse> savePreference(Single<LoginResponse> response) {
    return response;
  }
  
  void postLogin(String id, String pw) {
    Log.d("TTTTTTTTT", id + " : " + pw);
    
    disposable.add(
      api.postLogin(new LoginBody(id, pw))
        .subscribeOn(Schedulers.io())
        .map(t -> {
          SharedPreferences.Editor editor = getmSharedPreferences().edit();
          if (t.getIsSuccess()) {
            editor.putString(ACCESS_TOKEN, t.getResult());
            editor.apply();
          }
          return t;
        })
        .delay(1000L, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
          @Override
          public void onSuccess(@NonNull LoginResponse loginResponse) {
            Log.d("TASSSSSSS", loginResponse + "");
            if (loginResponse.getIsSuccess()) {
              loginFragmentInterface.loginSuccess(loginResponse.getCode());
            } else {
              loginFragmentInterface.loginFail("Not found Id");
            }
          }
          
          @Override
          public void onError(@NonNull Throwable e) {
            loginFragmentInterface.loginFail(e.getMessage());
          }
        })
    );
    
  }
}
