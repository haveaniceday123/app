package org.android.wanolza.main.listener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class RecyclerItemClickListner implements RecyclerView.OnItemTouchListener {
  private GestureDetector detector;
  private OnItemClickListener listener;
  
  public RecyclerItemClickListner(Context context, RecyclerView recyclerView, OnItemClickListener listener) {
    this.listener = listener;
    detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
      @Override
      public boolean onDown(MotionEvent e) {
        return true;
      }
  
      
      
    });
  }
  
  public interface OnItemClickListener {
    public void onItemClick(View view, int position);
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
}
