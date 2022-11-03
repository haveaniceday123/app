package org.android.wanolza.main.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import org.android.wanolza.R;

public class RippleItemView extends LinearLayout {
  
  ImageView imageView;
  TextView textView;
  
  public RippleItemView(Context context) {
    super(context);
    initView();
  }
  
  public RippleItemView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    initView();
    getAttrs(attrs);
  }
  
  public RippleItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView();
    getAttrs(attrs, defStyleAttr);
  }
  
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  
  private void initView() {
    
    String infService = Context.LAYOUT_INFLATER_SERVICE;
    LayoutInflater inflater
      = (LayoutInflater) getContext().getSystemService(infService);
    
    View view
      = inflater.inflate(R.layout.button_item, this, false);
    
    addView(view);
    
    imageView = view.findViewById(R.id.ripple_item_image);
    textView = view.findViewById(R.id.ripple_item_text);
    
  }
  
  private void getAttrs(AttributeSet attrs) {
    TypedArray typedArray
      = getContext().obtainStyledAttributes(attrs, R.styleable.RippleItemView);
    
    setTypeArray(typedArray);
  }
  
  private void getAttrs(AttributeSet attrs, int defStyle) {
    TypedArray typedArray
      = getContext()
      .obtainStyledAttributes(attrs, R.styleable.RippleItemView, defStyle, 0);
    
    setTypeArray(typedArray);
  }
  
  private void setTypeArray(TypedArray typedArray) {
    int backroundResource
      = typedArray.getResourceId(
        R.styleable.RippleItemView_rippleItemBg,
        R.drawable.hotel5
    );
    
    String text
      = typedArray.getString(R.styleable.RippleItemView_rippleItemText);
    
    imageView.setImageResource(backroundResource);
    textView.setText(text);
    
    typedArray.recycle();
  }
  
  void setImage(int bgResource) {
    imageView.setImageResource(bgResource);
  }
  
  void setTextView(String textViewString) {
    textView.setText(textViewString);
  }
  
  
}
