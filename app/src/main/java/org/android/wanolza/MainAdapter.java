package org.android.wanolza;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import org.android.wanolza.main.MainFragment;
import org.jetbrains.annotations.NotNull;

public class MainAdapter extends FragmentStateAdapter {
  private final int PAGE_SIZE = 5;
  
  public MainAdapter(@NonNull @NotNull Fragment fragment) {
    super(fragment);
  }
  
  public MainAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
    super(fragmentManager, lifecycle);
  }
  
  @NonNull
  @NotNull
  @Override
  public Fragment createFragment(int position) {
    return new MainFragment();
  }
  
  @Override
  public int getItemCount() {
    return PAGE_SIZE;
  }
}
