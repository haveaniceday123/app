package org.android.wanolza.main.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.android.wanolza.R;
import org.android.wanolza.main.adapter.BannerAdapter;

public class BannerView extends RelativeLayout {
  
  View rootview;
  ViewPager2 viewPager;
  TextView textView;
  
  BannerAdapter adapter;
  
  Callback mCallback;
  
  public BannerView(@NonNull Context context) {
    super(context);
    initView();
  }
  
  public BannerView(@NonNull Context context, BannerAdapter bannerAdapter) {
    super(context);
    adapter = bannerAdapter;
    initView();
  }
  
  public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    initView();
    getAttrs(attrs);
  }
  
  public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs);
    initView();
    getAttrs(attrs, defStyleAttr);
  }
  
  private void initView() {
    
    String infService = Context.LAYOUT_INFLATER_SERVICE;
    LayoutInflater layoutInflater =
      (LayoutInflater) getContext()
        .getSystemService(infService);
    
    View view = layoutInflater
      .inflate(R.layout.layout_banner, this, false);
    
    rootview = view;
    
    textView = view.findViewById(R.id.banner_view_pager_text);
    viewPager = view.findViewById(R.id.banner_view_pager);
  
  
    addView(view);
  
  }
  
  public void viewPagerSetting() {
    viewPager.registerOnPageChangeCallback(
      new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
          super.onPageSelected(position);
          Log.d("<<<<<<<<", position + "");
          
          if (mCallback != null) {
            mCallback.callback();
          }
        }
      }
    );
  }
  
  private void getAttrs(AttributeSet attrs) {
    try {
      TypedArray typedArray =
        getContext()
          .obtainStyledAttributes(attrs, R.styleable.BannerView);
      
      setTypeArray(typedArray);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void getAttrs(AttributeSet attrs, int defStyle) {
    try {
      TypedArray typedArray =
        getContext()
          .obtainStyledAttributes(attrs, R.styleable.BannerView, defStyle, 0) ;
    
      setTypeArray(typedArray);
    
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void setTypeArray(TypedArray typedArray) {
    typedArray.recycle();
  }
  
  public void setTextView(String str) {
    textView.setText(str);
  }
  
  public void adaptation(
    BannerAdapter adapter,
    int currentItem
  ) {
    
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(3);
    viewPager.setCurrentItem(currentItem);
    requestLayout();
    
  }
  
  public interface Callback {
    void callback();
  }
  
  public void setCallback(Callback callback) {
    this.mCallback = callback;
  }
  
  
}
