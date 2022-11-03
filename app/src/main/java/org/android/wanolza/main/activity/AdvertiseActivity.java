package org.android.wanolza.main.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import org.android.wanolza.base.BaseActivity;
import org.android.wanolza.databinding.ActivityAdvertiseBinding;

public class AdvertiseActivity extends BaseActivity {
  ActivityAdvertiseBinding binding;
  
  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAdvertiseBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
  }
}
