package org.android.wanolza.main.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.android.wanolza.R;
import org.android.wanolza.login.LoginActivity;
import org.android.wanolza.main.view.QuickButton;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class QuickItemAdapter extends RecyclerView.Adapter<QuickItemAdapter.QuickItemHolder> {
  ArrayList<QuickButton> list = new ArrayList<>();
  
  @NonNull
  @NotNull
  @Override
  public QuickItemHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View itemView = inflater.inflate(R.layout.quick_item, parent, false);
    
//    itemView.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        Intent intent = new Intent(v.getContext(), LoginActivity.class);
//        v.getContext().startActivity(intent);
//      }
//    });
    
    return new QuickItemHolder(itemView);
  }
  
  @Override
  public void onBindViewHolder(@NonNull @NotNull QuickItemHolder holder, int position) {
    QuickButton quickButton = list.get(position);
    holder.setItemHolder(quickButton);
  }
  
  @Override
  public int getItemCount() {
    return list.size();
  }
  
  public void setList(ArrayList<QuickButton> list) {
    this.list = list;
  }
  
  static class QuickItemHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;
  
    public QuickItemHolder(@NonNull @NotNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.quick_item_text);
      imageView = itemView.findViewById(R.id.quick_item_image);
    }
    
    public void setItemHolder(QuickButton quickButton) {
      textView.setText(quickButton.getImageName());
      imageView.setImageResource(quickButton.getImageId());
    }
  }
  
}
