package org.android.wanolza.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.android.wanolza.MainActivity;
import org.android.wanolza.R;
import org.android.wanolza.main.fragment.InternalFragment;
import org.android.wanolza.main.listener.ICallback;
import org.android.wanolza.main.view.RankingButton;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingButtonHolder> {
  List<RankingButton> list;
  
  ICallback callback;
  
  Context context;
  
  public RankingAdapter(List<RankingButton> list) {
    this.list = list;
  }
  
  public void setCallback(ICallback callback) {
    this.callback = callback;
  }
  
  @NonNull
  @NotNull
  @Override
  public RankingButtonHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.ranking_tab_button, parent, false);
    return new RankingButtonHolder(view);
  }
  
  @Override
  public void onBindViewHolder(@NonNull @NotNull RankingButtonHolder holder, int position) {
    RankingButton rankingButton = list.get(position);
    holder.setItem(rankingButton);
  
//    holder.textView.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        callback.myCallback();
//        ListIterator<RankingButton> iterator = list.listIterator();
//        int index = holder.getAdapterPosition();
//        Log.d("index", "<<<<<<<<<<<<" + index);
//
//        while (iterator.hasNext()) {
//          RankingButton rankingButton = list.get(iterator.nextIndex());
//          rankingButton.setBlocked(
//            index == iterator.nextIndex()
//          );
//          iterator.next();
//        }
//
//        notifyDataSetChanged();
//      }
//    });
    
    
    
  }
  
  @Override
  public int getItemCount() {
    return list.size();
  }
  
  public void setIndexClicked(int index) {
    ListIterator<RankingButton> iterator = list.listIterator();
  
    while (iterator.hasNext()) {
      RankingButton rankingButton = list.get(iterator.nextIndex());
      rankingButton.setBlocked(
        index % (list.size()) == iterator.nextIndex()
      );
      iterator.next();
    }
//      int a[] = new int[2];
//      v.getLocationInWindow(a);
//      callback.myCallback(a, v.getWidth() / 2);
    notifyDataSetChanged();
  }
  
  class RankingButtonHolder extends RecyclerView.ViewHolder {
    TextView textView;
    
    public RankingButtonHolder(@NonNull @NotNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.ranking_tab_button_text);
  
      textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ListIterator<RankingButton> iterator = list.listIterator();
        int index = getAdapterPosition();

        while (iterator.hasNext()) {
          RankingButton rankingButton = list.get(iterator.nextIndex());
          rankingButton.setBlocked(
            index == iterator.nextIndex()
          );
          iterator.next();
        }
        int a[] = new int[2];
        v.getLocationInWindow(a);
        callback.myCallback(a, v.getWidth() / 2);
        notifyDataSetChanged();
      }
    });
      
    }
    
    public void setIndexClicked(int index) {
      ListIterator<RankingButton> iterator = list.listIterator();
      Log.d("<<<<<<<", index % (list.size()) + "::::::::::::::");
  
      while (iterator.hasNext()) {
        RankingButton rankingButton = list.get(iterator.nextIndex());
        Log.d("<<<<<<<", index % (list.size()) + "::::::::::::::");
        rankingButton.setBlocked(
          index % (list.size()) == iterator.nextIndex()
        );
        iterator.next();
      }
//      int a[] = new int[2];
//      v.getLocationInWindow(a);
//      callback.myCallback(a, v.getWidth() / 2);
      notifyDataSetChanged();
    }
    
    public void setItem(RankingButton rankingButton) {
      Log.d("<<<<", rankingButton.toString());
      textView.setText(rankingButton.getButtonText());
      if (rankingButton.isBlocked()) {
        textView.setTextColor(context.getResources().getColor(R.color.selectedButton, null));
        textView.setBackgroundResource(R.drawable.ranking_tab_button);
      } else {
        textView.setTextColor(context.getResources().getColor(R.color.noneSelectedButton, null));
        textView.setBackgroundResource(R.drawable.ranking_tab_button_none);
      }
    }
  }
  
  
}
