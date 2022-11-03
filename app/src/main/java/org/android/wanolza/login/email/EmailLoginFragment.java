package org.android.wanolza.login.email;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.android.wanolza.MainActivity;
import org.android.wanolza.R;
import org.android.wanolza.login.LoginActivity;
import org.android.wanolza.login.email.interfaces.LoginFragmentInterface;
import org.jetbrains.annotations.NotNull;

public class EmailLoginFragment extends Fragment implements View.OnClickListener, LoginFragmentInterface {
  
  EditText id;
  
  EditText password;
  
  Button login;
  
  ImageView back;
  
  LoginActivity parentActivity;
  
  LoginService service;
  
  
  @Nullable
  @org.jetbrains.annotations.Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.activity_email_login, container, false);
    parentActivity = (LoginActivity) getActivity();
    service = new LoginService(this);
    initView(rootView);
    return rootView;
  }
  
  @Override
  public void onStart() {
    super.onStart();
    password.setText("admin");
    id.setText("admin");
  }
  
  private void login() {
    String userId = id.getText().toString();
    String pw = password.getText().toString();
    
    parentActivity.showProgressDialog();
    service.postLogin(userId, pw);
  }
  
  private void back() {
    parentActivity.onFragmentBack();
  }
  
  private void validate() {
    String userId = id.getText().toString();
    String pw = password.getText().toString();
    
    if (userId.length() == 0) {
//      parentActivity.toast("이메일을 입력하세요");
      login.setBackgroundColor(getActivity().getResources().getColor(R.color.blurred));
      login.setEnabled(false);
      return;
    }
    
    if (pw.length() == 0 ) {
//      parentActivity.toast("비밀번호를 입력하세요");
      login.setBackgroundColor(getActivity().getResources().getColor(R.color.blurred));
      login.setEnabled(false);
      return;
    }
    
    login.setBackgroundColor(getActivity().getResources().getColor(R.color.yanolz));
    login.setEnabled(true);
  
  }
  
  void initView(View view) {
    
    id = view.findViewById(R.id.login_id);
    password = view.findViewById(R.id.login_pw);
    login = view.findViewById(R.id.login_button);
    back = view.findViewById(R.id.back);
    
    login.setOnClickListener(this);
    
    editListner(password);
    editChangeListener(id);
    editChangeListener(password);
    
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        back();
      }
    });
  
  }
  
  private void editChangeListener(EditText id) {
    id.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    
      }
  
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        validate();
      }
  
      @Override
      public void afterTextChanged(Editable s) {
    
      }
    });
  }
  
  private void editListner(EditText id) {
    id.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
          login();
          return true;
        }
      
        return false;
      }
    });
  }
  
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.login_button:
        Log.d("TEST", "login button clicked");
        login();
        break;
    }
  }
  
  @Override
  public void loginSuccess(int userNo) {
    parentActivity.hideProgressDialog();
    Intent intent = new Intent(parentActivity, MainActivity.class);
    startActivity(intent);
    parentActivity.finish();
  }
  
  @Override
  public void loginFail(String message) {
    Toast.makeText(parentActivity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    parentActivity.hideProgressDialog();
  
  }
}
