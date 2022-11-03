package org.android.wanolza.main;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class OnRecyclerItemTouchInterceptorListenr implements RecyclerView.OnItemTouchListener {
  private GestureDetector gestureDetector;
  
  private RecyclerView rv;
  
  public OnRecyclerItemTouchInterceptorListenr(Context context, RecyclerView rv) {
    this.gestureDetector = new GestureDetector(context, new GestureListener());
    this.rv = rv;
  }
  
  @Override
  public boolean onInterceptTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {
    return false;
  }
  
  @Override
  public void onTouchEvent(@NonNull @NotNull RecyclerView rv, @NonNull @NotNull MotionEvent e) {
  
  }
  
  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
  
  }
  
  private class GestureListener extends GestureDetector.SimpleOnGestureListener {
    private int minRange = 10;
  
    @Override
    public boolean onDown(MotionEvent e) {
      rv.getParent().requestDisallowInterceptTouchEvent(true);
      return super.onDown(e);
    }
  
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
      if (Math.abs(distanceX) < Math.abs(distanceY)) {
        rv.getParent().requestDisallowInterceptTouchEvent(false);
      } else  if (Math.abs(distanceX) > minRange) {
        rv.getParent().requestDisallowInterceptTouchEvent(true);
      }
      return super.onScroll(e1, e2, distanceX, distanceY);
    }
  }
}
