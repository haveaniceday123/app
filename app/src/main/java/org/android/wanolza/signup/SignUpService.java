package org.android.wanolza.signup;

import android.util.Log;
import android.widget.Toast;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.android.wanolza.signup.interfaces.SignUpAcitivtyView;
import org.android.wanolza.signup.interfaces.SignUpApi;
import org.android.wanolza.signup.models.SignUpBody;
import org.android.wanolza.signup.models.SignUpResponse;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

import static org.android.wanolza.GlobalClass.getRetrofit;

public class SignUpService {
  
  private SignUpAcitivtyView acitivtyView;
  
  private CompositeDisposable disposable = new CompositeDisposable();
  
  public SignUpService(SignUpAcitivtyView acitivtyView) {
    this.acitivtyView = acitivtyView;
  }
  
  final private SignUpApi sign = getRetrofit().create(SignUpApi.class);
  
  void signUpUser(String id, String pw) {
    disposable.add(
      sign.signUp(new SignUpBody(id, pw))
        .subscribeOn(Schedulers.io())
        .delay(1, TimeUnit.SECONDS)
        
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<SignUpResponse>() {
          @Override
          public void onSuccess(@NonNull SignUpResponse signUpResponse) {
            Log.d("<<<<<<<<<<<<", signUpResponse.toString());
            acitivtyView.signUpSuccess(signUpResponse.getCode(), signUpResponse.getMessage());
          }
          
          @Override
          public void onError(@NonNull Throwable e) {
            Log.d("@@@@@@@@@@@@@@", e.getMessage());
            acitivtyView.signUpFail(500, e.getMessage());
          }
        })
    );
    
  }
}
