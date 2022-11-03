package org.android.wanolza.main.view;

import android.widget.ImageView;
import android.widget.TextView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecentlyItem {
  
  String recentlyItemName;
  
  String recentlyItemScore;
  
  int recentlyCommentCount;
  
  int recentlyItemPrice;
  
  int recentlyItemImage;
  
  float rating;
  
}
