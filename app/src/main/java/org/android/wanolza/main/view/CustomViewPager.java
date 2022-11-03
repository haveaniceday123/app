package org.android.wanolza.main.view;

import android.content.Context;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import org.jetbrains.annotations.NotNull;

public class CustomViewPager extends ViewPager {
  private boolean enabled;
  
  public CustomViewPager(@NonNull @NotNull Context context) {
    super(context);
    this.enabled = true;
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    if (this.enabled) {
      return super.onTouchEvent(ev);
    }
    return false;
  }
  
  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    if (this.enabled) {
      return super.onInterceptTouchEvent(ev);
    }
  
    return false;
  }
  
  public void setPagingEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}


