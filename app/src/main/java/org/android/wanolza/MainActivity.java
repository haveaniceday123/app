package org.android.wanolza;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import org.android.wanolza.base.BaseActivity;
import org.android.wanolza.databinding.ActivityMainBinding;
import org.android.wanolza.main.MainFragment;
import org.android.wanolza.main.adapter.MainActivityAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {
  
  
  ActivityMainBinding binding;
  
  TabLayout tab;
  
  NavigationBarView navigationBarView;
  
  FragmentManager fragmentManager;
  
  LinearLayout container;
  
  MainFragment mainFragment;
  
  ViewPager2 mainViewPager;
  
  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    
    fragmentManager = getSupportFragmentManager();
    container = binding.mainContainer;
    
    mainFragment = new MainFragment();
    
    navigationBarView = binding.bottomNavigationView;
    navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        return true;
      }
    });
    
    findViewById(R.id.main_container).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("clicked", "<>DSFSDFSDF");
      }
    });
    
    
    fragmentManager.beginTransaction().replace(R.id.main_container, mainFragment).commit();
    
    
  }
  
  public ViewPager2 getViewPager() { return mainViewPager; }
  
  public void setViewPgaer(ViewPager2 viewPager2) { this.mainViewPager = viewPager2; }
  
  public TabLayout getTabLayout() {
    return tab;
  }
  
  public void setTabLayout(TabLayout tab) {
    this.tab = tab;
  }
}