package org.android.wanolza.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.android.wanolza.MainActivity;
import org.android.wanolza.R;
import org.android.wanolza.main.activity.AdvertiseActivity;
import org.android.wanolza.main.adapter.Banner;
import org.android.wanolza.main.adapter.BannerAdapter;
import org.android.wanolza.main.adapter.QuickItemAdapter;
import org.android.wanolza.main.adapter.RecentlyItemAdapteer;
import org.android.wanolza.main.fragment.InternalFragment;
import org.android.wanolza.main.listener.RecyclerItemClickListner;
import org.android.wanolza.main.view.BannerPageButton;
import org.android.wanolza.main.view.QuickButton;
import org.android.wanolza.main.view.RecentlyItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RecommendFragment extends Fragment {
  
  private ViewPager2 viewPager2;
  private List<Banner> bannerList;
  private BannerAdapter bannerAdapter;
  
  private CompositeDisposable disposable;
  
  private MainFragment mainFragment;
  
  TabLayout tabLayout;
  
  private int touchSlope;
  
  
  private RecyclerView quickButtonRecycle;
  private RecyclerView recentlyRecycle;
  private ArrayList<QuickButton> quickButtonList;
  
  RecyclerItemClickListner listner;
  
  
  private TextView textView;
  
  Disposable disposable1;
  
  float lastX;
  float lastY;
  
  boolean buttonIsShowing = false;
  NestedScrollView scrollView;
  
  Animation traslateBottomAnim;
  Animation traslateTopAnim;
  Button toTop;
  
  ViewPager2 mainViewPager;
  int currentPage = 1;
  
  public RecommendFragment(ViewPager2 viewPager2) {
    this.mainViewPager = viewPager2;
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.banner_container, container, false);
    
    viewPager2 = view.findViewById(R.id.banner_view_pager);
    bannerList = new ArrayList<>();
    textView = view.findViewById(R.id.banner_button);
    quickButtonRecycle = view.findViewById(R.id.quick_icon_recyclerview);
    recentlyRecycle = view.findViewById(R.id.recently_recyclerview);

    
    ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
    touchSlope = viewConfiguration.getScaledTouchSlop();

    scrollView = view.findViewById(R.id.main_scrollview);

    SlidingPageAnimationListener listener = new SlidingPageAnimationListener();

    toTop = view.findViewById(R.id.main_to_top);

    traslateTopAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_top);
    traslateBottomAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_bottom);

    traslateTopAnim.setAnimationListener(listener);
    traslateBottomAnim.setAnimationListener(listener);

    toTop.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        scrollView.smoothScrollTo(0, 0);
      }
    });

    scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
      @Override
      public void onScrollChange(@NonNull @NotNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.d("position", v.getScrollY() + "");
        if (v.getScrollY() > 450 && !buttonIsShowing) {
          buttonIsShowing = true;
          toTop.startAnimation(traslateBottomAnim);
          toTop.setVisibility(View.VISIBLE);
        } else if (v.getScrollY() < 450 && buttonIsShowing){
          buttonIsShowing = false;
          toTop.startAnimation(traslateTopAnim);
          toTop.setVisibility(View.GONE);
        }
      }
    });

    
   
    
    bannerList.add(new Banner(R.drawable.banner1));
    bannerList.add(new Banner(R.drawable.banner2));
    bannerList.add(new Banner(R.drawable.banner3));
    bannerList.add(new Banner(R.drawable.banner4));
    bannerList.add(new Banner(R.drawable.banner5));
    bannerList.add(new Banner(R.drawable.banner6));
    
    bannerAdapter = new BannerAdapter(bannerList);
    viewPager2.setAdapter(bannerAdapter);
    viewPager2.setOffscreenPageLimit(3);
    viewPager2.setCurrentItem(bannerList.size() * 10);
    
    initQuickButtonList();
    
    initRecentlyList();
    
    itemListener(quickButtonRecycle);
    
    itemListener(recentlyRecycle);
    
    

    

    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        setBannerButtonText((position % bannerList.size()) + 1, bannerList.size());
        if (disposable1.isDisposed()) {
          autoSlide();
        }
      }
    });
    
    MainFragment fragment = (MainFragment) getParentFragment();
  
    quickButtonRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {

          mainViewPager.setUserInputEnabled(false);

        } else {
          mainViewPager.setUserInputEnabled(true);
        }
      }
    });
    
    
    
    recentlyRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {

          mainViewPager.setUserInputEnabled(false);

        } else {
          mainViewPager.setUserInputEnabled(true);
        }
      }
    });
 
    
    setBannerButtonText(currentPage, bannerList.size());
    
    textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBannerButtonClick();
      }
    });
    
    return view;
  }
  
  private void itemListener(RecyclerView recentlyRecycle) {
    
    getActivity();
    
    recentlyRecycle.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
      @Override
      public boolean onInterceptTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {
  

        MainActivity mainActivity = (MainActivity) getActivity();
        recentlyRecycle.requestDisallowInterceptTouchEvent(true);
  

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
          lastX = e.getX();
          lastY = e.getY();
          recentlyRecycle.requestDisallowInterceptTouchEvent(true);
        } else if (e.getAction() == MotionEvent.ACTION_MOVE) {
          float dx = e.getX() - lastX;
          float dy = e.getY() - lastY;
        

          boolean isVpHorizontal = mainViewPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL;

          // assuming ViewPager2 touch-slop is 2x touch-slop of child
          float scaledDx = Math.abs(dx) * (isVpHorizontal ? .5f : 1f);
          float scaledDy = Math.abs(dy) * (isVpHorizontal ? 1f : .5f);

          if (isVpHorizontal == (scaledDy > scaledDx)) {
            recentlyRecycle.requestDisallowInterceptTouchEvent(false);
  
            return false;
          } else {
            // Gesture is parallel, query child if movement in that direction is possible
            if (recentlyRecycle.canScrollHorizontally(-1)) {
              // Child can scroll, disallow all parents to intercept
              recentlyRecycle.requestDisallowInterceptTouchEvent(true);
            } else if (recentlyRecycle.canScrollHorizontally(1)) {
              recentlyRecycle.requestDisallowInterceptTouchEvent(true);
            } else {
              // Child cannot scroll, allow all parents to intercept
              recentlyRecycle.requestDisallowInterceptTouchEvent(false);
            }
          }
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
          recentlyRecycle.requestDisallowInterceptTouchEvent(false);
  
        }
        
        return false;

        
        
      }
      
      @Override
      public void onTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {
      
      }
      
      @Override
      public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
      
      }
    });
  }
  
  void setBannerButtonText(int currentPage, int totalCount) {
    String contents = intFormatting(currentPage) + " / " + intFormatting(totalCount) + " | 전체보기";
    InternalFragment.settingText(contents, textView);
  }
  
  String intFormatting(int number) {
    return String.format(Locale.KOREA, "%02d", number);
  }
  
  
  @Override
  public void onResume() {
    autoSlide();
    super.onResume();
  }
  
  void initRecentlyList() {
    ArrayList list = new ArrayList();
    list.add(new RecentlyItem("가평 열호당펜션", "4.3", 174, 60000, R.drawable.recently1, 4.4f));
    list.add(new RecentlyItem("가평 사과꽃향기펜션", "4.3", 74, 620000, R.drawable.recently2, 4.8f));
    list.add(new RecentlyItem("가평 풀빌라펠리스", "4.3", 1300, 89000, R.drawable.recently3, 4.0f));
    list.add(new RecentlyItem("가평 힐펜션", "4.3", 895, 4000, R.drawable.recently4, 3.9f));
    
    RecentlyItemAdapteer adapteer = new RecentlyItemAdapteer();
    adapteer.setList(list);
    recentlyRecycle.setAdapter(adapteer);
    
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    
    recentlyRecycle.setLayoutManager(layoutManager);
  }
  
  void initQuickButtonList() {
    
    quickButtonList = new ArrayList<>();
    quickButtonList.add(new QuickButton(R.drawable.hotel, "호텔"));
    quickButtonList.add(new QuickButton(R.drawable.motel, "모텔"));
    quickButtonList.add(new QuickButton(R.drawable.hotel3, "펜션/풀빌라"));
    quickButtonList.add(new QuickButton(R.drawable.hotel4, "레저/티켓"));
    quickButtonList.add(new QuickButton(R.drawable.hotel5, "고속버스"));
    quickButtonList.add(new QuickButton(R.drawable.hotel6, "전시예매"));
    quickButtonList.add(new QuickButton(R.drawable.hotel7, "렌터카"));
    quickButtonList.add(new QuickButton(R.drawable.hotel8, "게하/한옥"));
    quickButtonList.add(new QuickButton(R.drawable.hotel9, "해외숙소"));
    
    QuickItemAdapter quickItemAdapter = new QuickItemAdapter();
    quickItemAdapter.setList(quickButtonList);
    quickButtonRecycle.setAdapter(quickItemAdapter);
    
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    
    quickButtonRecycle.setLayoutManager(layoutManager);
  }
  
  void autoSlide() {
    disposable1 = Single.just(viewPager2).subscribeOn(Schedulers.io()).delay(4, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(result -> viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1));
    
  }
  
  void onBannerButtonClick() {
    Intent intent = new Intent(getContext(), AdvertiseActivity.class);
    startActivity(intent);
  }
  
  @Override
  public void onPause() {
    disposable1.dispose();
    super.onPause();
  }
  
  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
  
  
  private class SlidingPageAnimationListener implements Animation.AnimationListener {
    
    @Override
    public void onAnimationStart(Animation animation) {
    
    }
    
    @Override
    public void onAnimationEnd(Animation animation) {
      if (buttonIsShowing) {
        toTop.setVisibility(View.VISIBLE);
      } else {
        toTop.setVisibility(View.GONE);
      }
    }
    
    @Override
    public void onAnimationRepeat(Animation animation) {
    
    }
  }
  
}

