package org.android.wanolza.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.android.wanolza.databinding.TestFragment2Binding;

import org.jetbrains.annotations.NotNull;

public class TestFragment2 extends Fragment {
  
  private TestFragment2Binding binding;
  
  @Nullable
  @org.jetbrains.annotations.Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    binding = TestFragment2Binding.inflate(inflater, container, false);
    return binding.getRoot();
  }
}
