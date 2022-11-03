package org.android.wanolza.main.adapter;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import org.android.wanolza.R;
import org.android.wanolza.main.view.RecentlyItem;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Handler;

public class RecentlyItemAdapteer extends RecyclerView.Adapter<RecentlyItemAdapteer.RecentlyItemHolder> {
  Context context;
  ArrayList<RecentlyItem> list = new ArrayList<>();
  
  @NonNull
  @NotNull
  @Override
  public RecentlyItemHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View item = inflater.inflate(R.layout.recently_item, parent, false);
    
    return new RecentlyItemHolder(item);
  }
  
  @Override
  public void onBindViewHolder(@NonNull @NotNull RecentlyItemHolder holder, int position) {
    RecentlyItem recentlyItem = list.get(position);
    holder.setItem(recentlyItem);
  }
  
  @Override
  public int getItemCount() {
    return list.size();
  }
  
  public void setList(ArrayList<RecentlyItem> list) {
    this.list = list;
  }
  
  
  public class RecentlyItemHolder extends RecyclerView.ViewHolder {
    
    ImageView itemImage;
    TextView itemName;
    SimpleRatingBar itemScore;
    TextView itemScoreText;
    TextView itemCommentCount;
    TextView itemPrice;
    
    public RecentlyItemHolder(@NonNull @NotNull View itemView) {
      super(itemView);
      
      itemImage = itemView.findViewById(R.id.recently_item_image);
      itemName = itemView.findViewById(R.id.recently_item_name);
      itemScore = itemView.findViewById(R.id.recently_item_rating);
      itemScoreText = itemView.findViewById(R.id.recently_item_score);
      itemCommentCount = itemView.findViewById(R.id.recently_item_count);
      itemPrice = itemView.findViewById(R.id.recently_item_price);
      
      itemImage.setClipToOutline(true);
      
    }
    
    public void setItem(RecentlyItem item) {
      itemImage.setImageResource(item.getRecentlyItemImage());
      itemName.setText(item.getRecentlyItemName());
      itemScore.setRating(item.getRating());
      itemScoreText.setText(String.valueOf(item.getRating()));
      itemCommentCount.setText(
        context.getString(
          R.string.recently_comment,
          String.valueOf(item.getRecentlyCommentCount())
        ));
      itemPrice.setText(
        context.getString(
          R.string.recently_price,
          numberFormatting(item.getRecentlyItemPrice())
        )
      );
    }
  }
  
  String numberFormatting(int number) {
    DecimalFormat format = new DecimalFormat("###,###");
    return format.format(number);
  }
}
