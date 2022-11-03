package org.android.wanolza.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import org.android.wanolza.main.RecommendFragment;
import org.android.wanolza.main.fragment.InternalFragment;
import org.android.wanolza.test.TestFragment2;
import org.jetbrains.annotations.NotNull;

public class MainActivityAdapter extends FragmentStateAdapter {
  private ViewPager2 viewPager2;
  
  private final int NUM_TABS = 5;
  
  public MainActivityAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle, ViewPager2 viewPager2) {
    super(fragmentManager, lifecycle);
    this.viewPager2 = viewPager2;
  }
  
  @NonNull
  @NotNull
  @Override
  public Fragment createFragment(int position) {
    switch (position) {
      case 0:
        return new RecommendFragment(viewPager2);
      case 1:
        return new InternalFragment(viewPager2);
      default:
        return new TestFragment2();
    }
  }
  
  @Override
  public int getItemCount() {
    return NUM_TABS;
  }
}
