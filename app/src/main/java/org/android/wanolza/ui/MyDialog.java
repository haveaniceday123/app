package org.android.wanolza.ui;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import org.android.wanolza.R;


public class MyDialog implements Animator.AnimatorListener{
  
  Context context;
  Dialog dialog;
  
  LottieAnimationView animationView;
  
  Handler handler;
  
  
  public MyDialog(Context context) {
    this.context = context;
    handler = new Handler(context.getMainLooper());
  }
  
  public void showDialog() {
    
    dialog = new Dialog(context);
    dialog.setContentView(R.layout.activity_dialog);
    
    animationView = dialog.findViewById(R.id.loading_lottie);
    animationView.addAnimatorListener(this);
    
    
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.create();
    dialog.show();
  }
  
  
  public void hideDialog() {
    dialog.dismiss();
  }
  
  public boolean isShowing() {
    return dialog.isShowing();
  }
  
  @Override
  public void onAnimationStart(Animator animation) {
  
  }
  
  @Override
  public void onAnimationEnd(Animator animation) {
    Log.d("THIS", "ANIMATION CLEAR");
//    animationView.playAnimation();
//    animationView.delay
    
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        animationView.playAnimation();
      }
    }, 1000);
  }
  
  @Override
  public void onAnimationCancel(Animator animation) {
  
  }
  
  @Override
  public void onAnimationRepeat(Animator animation) {
  
  }
}
