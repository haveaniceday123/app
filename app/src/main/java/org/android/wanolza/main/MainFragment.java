package org.android.wanolza.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import org.android.wanolza.MainActivity;
import org.android.wanolza.R;
import org.android.wanolza.databinding.ActivityFragmentMainBinding;
import org.android.wanolza.main.adapter.MainActivityAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {
  
  ActivityFragmentMainBinding binding;
  
  List<String> titleList = Arrays.asList("추천", "국내숙소", "즐길거리", "교통/항공", "해외여행");
  
  ViewPager2 viewPager2;
  
  TabLayout tabLayout;
  
  FragmentManager fragmentManager;
  
  boolean buttonIsShowing = false;
  NestedScrollView scrollView;
  
  Animation traslateBottomAnim;
  Animation traslateTopAnim;
  Button toTop;
  
  @Nullable
  @Override
  public View onCreateView(
    @NonNull @NotNull LayoutInflater inflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    binding = ActivityFragmentMainBinding.inflate(inflater, container, false);
    View rootView = binding.getRoot();
    
    viewPager2 = rootView.findViewById(R.id.main_view_pager);
    tabLayout = rootView.findViewById(R.id.main_tab_layout);
    fragmentManager = requireActivity().getSupportFragmentManager();

    MainActivity main = (MainActivity)getActivity();
    main.setViewPgaer(viewPager2);
    
    
    MainActivityAdapter adapter = new MainActivityAdapter(fragmentManager, getLifecycle(), viewPager2);
    viewPager2.setAdapter(adapter);
    viewPager2.setOffscreenPageLimit(8);
    
    new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
      @Override
      public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
        tab.setText(titleList.get(position));
      }
    }).attach();
    
    setMarginTab();
    
    return rootView;
  }
  
  void setMarginTab() {
    
    ViewGroup tab = (ViewGroup) tabLayout.getChildAt(0);
    
    for (int i = 0; i < tab.getChildCount(); i++) {
      View tabView = tab.getChildAt(i);
      tabView.setPadding(0, 5, 0, 5);
      tabView.setMinimumHeight(0);
      LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) tabView.getLayoutParams();
      param.setMargins(40, 0, 40, 0);
      tabView.setLayoutParams(param);
    }
    tabLayout.requestLayout();
  }
  
  public void enableTabViewPager(boolean b) {
    viewPager2.setUserInputEnabled(b);
  }
  
  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
  
}
