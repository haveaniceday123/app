package org.android.wanolza.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.material.imageview.ShapeableImageView;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import org.android.wanolza.R;
import org.android.wanolza.util.Util;

import java.util.Optional;


public class RankingItem extends LinearLayout {
  ShapeableImageView imageView;
  TextView numbering;
  TextView name;
  TextView average;
  TextView total;
  TextView price;
  SimpleRatingBar rating;
  
  public RankingItem(Context context) {
    super(context);
    initView();
  }
  
  public RankingItem(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    initView();
  }
  
  public RankingItem(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView();
  }
  
  private void initView() {
    String infService = Context.LAYOUT_INFLATER_SERVICE;
    LayoutInflater li= (LayoutInflater) getContext().getSystemService(infService);
    View rootView = li.inflate(R.layout.ranking_item, this, false);
    addView(rootView);
//
    imageView = rootView.findViewById(R.id.ranking_item_image);
    numbering = rootView.findViewById(R.id.ranking_item_number);
    name = rootView.findViewById(R.id.ranking_item_name);
    average = rootView.findViewById(R.id.ranking_item_average);
    total = rootView.findViewById(R.id.ranking_item_total);
    price = rootView.findViewById(R.id.ranking_item_price);
    rating = rootView.findViewById(R.id.recently_item_rating);
  }
  
  public void setImageView(int resId) {
    imageView.setImageResource(resId);
  }
  
  public void setNumbering(int number) {
    numbering.setText(String.valueOf(number));
  }
  
  public void setName(String itemName) {
    name.setText(itemName);
  }
  
  public void setAverage(String aver) {
    
//    String s = Optional.ofNullable(getContext())
//      .map(c -> c.getString(R.string.recently_comment, aver))
//      .orElse("정보없음");
    
    average.setText(aver);
  }
  
  
  public void setTotal(int count) {
    total.setText(getContext().getString(R.string.recently_comment,  Util.threeComma(count)));
    
  }
  
  public void setPrice(int prices) {
    String result = Util.threeComma(prices);
    price.setText(getContext().getString(R.string.recently_price, result));
  }
  
  public void setRating(float ra) {
    rating.setRating(ra);
  }
  
  public void setItem(RankingData rankingData) {
    setName(rankingData.getName());
    setAverage(String.valueOf(rankingData.getAverage()));
    setImageView(rankingData.getImageResource());
    setRating(rankingData.getRating());
    setPrice(rankingData.getPrice());
    setTotal(rankingData.getTotal());
    setNumbering(rankingData.getNumbering());
  }
}
