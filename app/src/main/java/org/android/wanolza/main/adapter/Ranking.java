package org.android.wanolza.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import org.android.wanolza.main.fragment.RankingFragment;
import org.android.wanolza.main.view.RankingData;
import org.android.wanolza.test.TestFragment2;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Ranking extends FragmentStateAdapter {
  public int count;
  private ArrayList<RankingData> list;
  
  public Ranking(@NonNull @NotNull FragmentActivity fragmentActivity, int count) {
    super(fragmentActivity);
    this.count = count;
  }
  
  public Ranking(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle, int count
                 , ArrayList<RankingData> list
  
  ) {
    super(fragmentManager, lifecycle);
    this.count = count;
    this.list = list;
  }
  
  @NonNull
  @NotNull
  @Override
  public Fragment createFragment(int position) {
    if (position % 2 == 0) {
      return new RankingFragment(list, 0, 1, 2);
    } else {
      return new RankingFragment(list, 3, 4, 5);
    }
  }
  
  @Override
  public int getItemCount() {
    return 2000;
  }
  
  public int getRealPosition(int position) {
    return position % count;
  }
}
