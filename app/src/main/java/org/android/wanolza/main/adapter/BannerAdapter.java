package org.android.wanolza.main.adapter;

import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import org.android.wanolza.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ImageViewHolder> {
  private List<Banner> imageList;
  
  public BannerAdapter(List<Banner> imageList) {
    this.imageList = imageList;
  }
  
  @NonNull
  @NotNull
  @Override
  public ImageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false);
    
    return new ImageViewHolder(view);
  }
  
  @Override
  public void onBindViewHolder(@NonNull @NotNull ImageViewHolder holder, int position) {
//    holder.imageView.setImageResource(imageList.get(position).getImage());
    holder.imageView.setImageResource(imageList.get(position % imageList.size()).getImage());
    
  }
  
  @Override
  public int getItemCount() {
    return Integer.MAX_VALUE;
  }
 
  
  public static class ImageViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
  
    public ImageViewHolder(@NonNull @NotNull View itemView) {
      super(itemView);
      
      imageView = itemView.findViewById(R.id.banner_contents);
    }
  }
  
}
