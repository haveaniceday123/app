package org.android.wanolza.main.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RankingButton {
  
  String buttonText;
  
  boolean isBlocked = false;
  
  public RankingButton(String buttonText) {
    this.buttonText = buttonText;
  }
  
  
}
