package org.android.wanolza.util;


import androidx.viewpager2.widget.ViewPager2;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class Util {
  
  public static Observable<Long> intervalObserver(int interval) {
    return Observable.interval(interval, TimeUnit.SECONDS);
  }
  
  public static String makeBannerText(int currentPage, int totalPage) {
    StringBuilder stringBuilder
      = new StringBuilder();
    
    return stringBuilder.toString();
  }
  
  public static String intFormatting(int number) {
    return String.format(Locale.KOREA, "%02d", number);
  }
  
  public static String threeComma(int number) {
    DecimalFormat format = new DecimalFormat("###,###");
    return format.format(number);
  }
}
