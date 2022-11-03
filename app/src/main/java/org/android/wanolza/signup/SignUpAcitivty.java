package org.android.wanolza.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.android.wanolza.MainActivity;
import org.android.wanolza.R;
import org.android.wanolza.base.BaseActivity;
import org.android.wanolza.login.LoginActivity;
import org.android.wanolza.signup.interfaces.SignUpAcitivtyView;

public class SignUpAcitivty extends BaseActivity implements SignUpAcitivtyView {
  private final static String TAG = SignUpAcitivty.class.getName();
  
  private SignUpService service;
  
  
  ImageView back;
  
  EditText editText;
  
  Button singInButton;
  
  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);
    service = new SignUpService(this);
    initView();
  }
  
  boolean validDate(String id, String pw) {
    if (id.length() == 0 || pw.length() == 0) {
      Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show();
      return false;
    }
    return true;
  }
  
  void signUpRequest() {
    String id = editText.getText().toString();
    String pw = editText.getText().toString();
    
    if (!validDate(id, pw)) return;
    
    showProgressDialog();
    service.signUpUser(id, pw);
  }
  
  void initView() {
    back = findViewById(R.id.back);
    editText = findViewById(R.id.sign_up_id);
    singInButton = findViewById(R.id.sign_up_button);
    
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
    
    singInButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        signUpRequest();
      }
    });
    
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      
      }
  
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
          singInButton.setBackgroundResource(R.drawable.enable_button);
          singInButton.setEnabled(true);
        } else {
          singInButton.setBackgroundResource(R.drawable.disabled_button);
          singInButton.setEnabled(false);
        }
      }
      
      
  
      @Override
      public void afterTextChanged(Editable s) {
    
      }
    });
  }
  
  @Override
  public void signUpSuccess(int code, String message) {
    Log.d(TAG, message + " : " + code);
    hideProgressDialog();
    Toast.makeText(getApplicationContext(), "Sign up success", Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(SignUpAcitivty.this, LoginActivity.class);
    startActivity(intent);
    
  }
  
  @Override
  public void signUpFail(int code, String message) {
    Log.d(TAG, message + " : " + code);
    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    hideProgressDialog();
  }
}
