package org.android.wanolza.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.android.wanolza.R;
import org.android.wanolza.main.view.RankingData;
import org.android.wanolza.main.view.RankingItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RankingFragment extends Fragment {
  
  ArrayList<RankingData> list = new ArrayList<>();
  int index1;
  int index2;
  int index3;
  
  public RankingFragment(ArrayList<RankingData> list, int i, int i1, int i2) {
    this.list = list;
    this.index1 = i;
    this.index2 = i1;
    this.index3 = i2;
  }
  
  
  @Nullable
  @org.jetbrains.annotations.Nullable
  @Override
  public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(
      R.layout.ranking_fragment, container, false
    );
    
    RankingItem rankingItem = rootView.findViewById(R.id.ranking_item_1);
    RankingItem rankingItem1 = rootView.findViewById(R.id.ranking_item_2);
    RankingItem rankingItem2 = rootView.findViewById(R.id.ranking_item_3);
    rankingItem.setItem(list.get(index1));
    rankingItem1.setItem(list.get(index2));
    rankingItem2.setItem(list.get(index3));
   
    
    
    
    return rootView;
  }
}
