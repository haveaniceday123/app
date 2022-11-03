package org.android.wanolza.main.fragment;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.android.wanolza.R;
import org.android.wanolza.main.adapter.Banner;
import org.android.wanolza.main.adapter.BannerAdapter;
import org.android.wanolza.main.adapter.Ranking;
import org.android.wanolza.main.adapter.RankingAdapter;
import org.android.wanolza.main.listener.ICallback;
import org.android.wanolza.main.view.RankingButton;
import org.android.wanolza.main.view.RankingData;
import org.android.wanolza.util.Util;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class InternalFragment extends Fragment {
  ViewPager2 internalAdvertiseViewPager;
  
  ViewPager2 internalRanking;
  RecyclerView internalRankingViewPager;
  
  TextView bannerText;
  
  List<Integer> internalBannerItems = Arrays.asList(
    R.drawable.banner6,
    R.drawable.banner5,
    R.drawable.banner4,
    R.drawable.banner3,
    R.drawable.banner2,
    R.drawable.banner1
  );
  
  List<String> internalRakingButtons = Arrays.asList(
    "할인중인 모텔",
    "성남 모텔",
    "잠실 모텔",
    "강남 모텔",
    "마감 임박 호텔"
  );
  
  RankingAdapter buttonAdapter;
  
  ArrayList<Banner> internalBannerList;
  ArrayList<RankingButton> internalRakingButtonList;
  
  BannerAdapter adapter;
  RankingAdapter rankingButtonAdapter;
  
  CompositeDisposable disposable = new CompositeDisposable();
  
  ViewPager2 mainViewPager;
  
  Disposable dis;
  
  float lastX;
  float lastY;
  
  public InternalFragment(ViewPager2 mainViewPager) {
    this.mainViewPager = mainViewPager;
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    View internalView = inflater.inflate(R.layout.fragment_internal, container, false);
    initView(internalView);
    
    internalBannerList
      = initViewPagerList(internalBannerItems);
    
    internalRakingButtonList
      = initViewPagerRakingList(internalRakingButtons);
    
    initBanner(internalView);
    initRanking(internalView);
    initRankings(internalView);
    
    bannerButtonTextChange();
    
    
    return internalView;
  }
  
  private void initView(View view) {
    internalRankingViewPager = view.findViewById(R.id.ranking_tab_button_viewpager);
    internalRanking = view.findViewById(R.id.ranking_view);
    bannerText = view.findViewById(R.id.banner_view_pager_text);
  }
  
  @Override
  public void onResume() {
    initTimer();
    super.onResume();
  }
  
  private void bannerButtonTextChange() {
    int size = internalBannerList.size();
    int currentPage =
      (internalAdvertiseViewPager.getCurrentItem() % size) + 1;
    
    String changeText = setOnPageText(currentPage, size);
  
    settingText(changeText, bannerText);
  }
  
  public static void settingText(String changeText, TextView bannerText) {
    Spannable span = new SpannableString(changeText);
    int changeTextLength = changeText.length();
    
    span.setSpan(new StyleSpan(Typeface.BOLD), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    span.setSpan(new StyleSpan(Typeface.BOLD), changeTextLength - 4, changeTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    
    bannerText.setText(span);
  }
  
  private void initTimer() {
    
    dis = Util.intervalObserver(5)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(it -> {
          internalAdvertiseViewPager.setCurrentItem(
            internalAdvertiseViewPager.getCurrentItem() + 1
          );
          bannerButtonTextChange();
        }, thr -> Log.e("ERROR", thr.getMessage())
      );
    
  }
  
  private void initBanner(View view) {
    internalAdvertiseViewPager
      = view.findViewById(R.id.banner_view_pager);
    
    internalAdvertiseViewPager.setAdapter(
      new BannerAdapter(internalBannerList)
    );
    
    internalAdvertiseViewPager
      .setOffscreenPageLimit(3);
    
    internalAdvertiseViewPager
      .setCurrentItem(internalBannerList.size() * 10);
  }
  
  private  void initRankings(View view) {
    ArrayList<RankingData> rankingData = new ArrayList<>();
    rankingData.add(
      new RankingData(R.drawable.ranking_item1, 1, "성남(모란) bunker 1149", 4.3f, 751, 4.2f, 60000)
    );
    rankingData.add(
      new RankingData(R.drawable.ranking_item2, 2, "성남 호텔 K", 4.5f, 4078, 4.3f, 30000)
    );
    rankingData.add(
      new RankingData(R.drawable.ranking_item3, 3, "성남 해바라기", 4.7f, 123, 4.9f, 100000)
    );
    rankingData.add(
      new RankingData(R.drawable.ranking_item4, 1, "성남 YAJA 위례점", 2.0f, 5391, 2.0f, 145000)
    );
    rankingData.add(
      new RankingData(R.drawable.ranking_item5, 2, "성남 YAJA 분당점", 2.3f, 1212, 1.1f, 1200)
    );
    rankingData.add(
      new RankingData(R.drawable.ranking_item6, 3, "성남 호텔 K", 1.1f, 311, 1.2f, 1600)
    );
    
    internalRanking.setAdapter(
      new Ranking(getParentFragmentManager(),getLifecycle(), 6, rankingData)
    );
    
    internalRanking.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
    internalRanking.setCurrentItem(1000);
    internalRanking.setOffscreenPageLimit(4);
    
    internalRanking.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        Log.d("<<<<<<<<", position + ":::::::::::");
//        buttonAdapter.setIndexClicked(position);
        internalRaking
      }
    });
    
  }
  
  private void initRanking(View view) {
    internalRankingViewPager
      = view.findViewById(R.id.ranking_tab_button_viewpager);
  
    buttonAdapter = new RankingAdapter(internalRakingButtonList);
    buttonAdapter.setCallback(new ICallback() {
      @Override
      public void myCallback(int[] position, int w) {
        scrollingRanking(position, w);
      }
    });
  
    internalRankingViewPager.setAdapter(
      buttonAdapter
    );
  
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
  
    internalRankingViewPager.setLayoutManager(layoutManager);
  
    rankingCallback(internalRankingViewPager);
  }
  
  private void scrollingRanking(int[] position, int w) {
    LinearLayoutManager manager = (LinearLayoutManager) internalRankingViewPager.getLayoutManager();
    int width = internalRankingViewPager.getWidth() / 2;
    internalRankingViewPager.smoothScrollBy(position[0] - (width - w), 0);
  }
  
  
  private ArrayList<Banner> initViewPagerList(List<Integer> items) {
    ListIterator<Integer> itemIterator = items.listIterator();
    ArrayList<Banner> resultList = new ArrayList<>();
    
    while (itemIterator.hasNext()) {
      Banner banner = new Banner(items.get(itemIterator.nextIndex()));
      resultList.add(banner);
      itemIterator.next();
    }
    
    return resultList;
  }
  
  private ArrayList<RankingButton> initViewPagerRakingList(List<String> items) {
    ListIterator<String> itemIterator = items.listIterator();
    ArrayList<RankingButton> resultList = new ArrayList<>();
    
    while (itemIterator.hasNext()) {
      RankingButton button = new RankingButton(items.get(itemIterator.nextIndex()));
      resultList.add(button);
      itemIterator.next();
    }
    
    resultList.get(0).setBlocked(true);
    
    return resultList;
  }
  
  
  private String setOnPageText(int number1, int number2) {
    String formatFirst = Util.intFormatting(number1);
    String formatSecond = Util.intFormatting(number2);
    
    return Optional.ofNullable(getContext())
      .map(c -> c.getString(R.string.banner_text, formatFirst, formatSecond))
      .orElse("정보없음");
    
  }
  
  private void rankingCallback(RecyclerView rv) {
    rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
        mainViewPager.setUserInputEnabled(newState != RecyclerView.SCROLL_STATE_DRAGGING);
      }
    });
    
  }
  
  private void setRankingNestedScrollalable() {
      internalRankingViewPager.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {
          internalRankingViewPager.requestDisallowInterceptTouchEvent(true);
  
  
          if (e.getAction() == MotionEvent.ACTION_DOWN) {
            lastX = e.getX();
            lastY = e.getY();
            internalRankingViewPager.requestDisallowInterceptTouchEvent(true);
          } else if (e.getAction() == MotionEvent.ACTION_MOVE) {
            float dx = e.getX() - lastX;
            float dy = e.getY() - lastY;
    
    
            boolean isVpHorizontal = mainViewPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL;
    
            // assuming ViewPager2 touch-slop is 2x touch-slop of child
            float scaledDx = Math.abs(dx) * (isVpHorizontal ? .5f : 1f);
            float scaledDy = Math.abs(dy) * (isVpHorizontal ? 1f : .5f);
    
            if (isVpHorizontal == (scaledDy > scaledDx)) {
              internalRankingViewPager.requestDisallowInterceptTouchEvent(false);
      
              return false;
            } else {
              // Gesture is parallel, query child if movement in that direction is possible
              if (internalRankingViewPager.canScrollHorizontally(-1)) {
                // Child can scroll, disallow all parents to intercept
                internalRankingViewPager.requestDisallowInterceptTouchEvent(true);
              } else if (internalRankingViewPager.canScrollHorizontally(1)) {
                internalRankingViewPager.requestDisallowInterceptTouchEvent(true);
              } else {
                // Child cannot scroll, allow all parents to intercept
                internalRankingViewPager.requestDisallowInterceptTouchEvent(false);
              }
            }
          } else if (e.getAction() == MotionEvent.ACTION_UP) {
            internalRankingViewPager.requestDisallowInterceptTouchEvent(false);
    
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
  
  
  @Override
  public void onPause() {
    Log.d("DISPOSE", "DISPOSE");
    dis.dispose();
    super.onPause();
  
  }
}
