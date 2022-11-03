package org.android.wanolza;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import org.android.wanolza.base.BaseActivity;

public class StartActivity extends BaseActivity  {
  
  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    
    new Handler(getMainLooper()).postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
      }
    }, 5000);
  }
}
