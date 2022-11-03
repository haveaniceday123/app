package org.android.wanolza.main.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import org.android.wanolza.R;

public class BannerPageButton extends AppCompatTextView {
  public BannerPageButton(Context context) {
    super(context);
  }
  
  public BannerPageButton(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }
  
  private void initView(Context context) {
    setBackgroundColor(getResources().getColor(R.color.blackBackground, null));
    setGravity(Gravity.BOTTOM|Gravity.END);
    setTextSize(getResources().getDimension(R.dimen.banner_button_text_size));
    setTextColor(Color.WHITE);
    setText("01/16 | 전체보기");
    setPadding(10, 10, 10, 10);
    
  }
  
  
}
