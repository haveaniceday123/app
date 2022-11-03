package org.android.wanolza.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import org.android.wanolza.R;
import org.android.wanolza.base.BaseActivity;
import org.android.wanolza.login.email.EmailLoginFragment;
import org.android.wanolza.signup.SignUpAcitivty;

import java.util.*;
import java.util.zip.Inflater;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
  
  private ImageView imageView1;
  
  private ImageView imageView2;
  
  FragmentManager fm = getSupportFragmentManager();
  
  FragmentTransaction ts;
  
  CompositeDisposable compositeDisposable = new CompositeDisposable();
  
  private final EmailLoginFragment emailLoginFragment = new EmailLoginFragment();
  
  BottomSheetDialog bottomSheetDialog;
  
  List<Boolean> checkList = Arrays.asList(false, false, false, false, false);
  List<Integer> drawableList = Arrays.asList(R.id.check1, R.id.check2, R.id.check3, R.id.check4, R.id.check4);
  ArrayList<ImageView> imageViewList = new ArrayList<>();
  
  boolean open = false;
  
  
  List<Boolean> mCheckList = Arrays.asList(false, false, false);
  
  ArrayList<ImageView> miniViewList = new ArrayList<>();
  
  LinearLayout layout;
  
  Handler handler;
  
  //all checkbox
  CheckBox checkBox;
  
  Button next;
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setTheme(R.style.SplashTheme);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    
    FragmentManager fm = getSupportFragmentManager();
    
    initView();
  }
  
  public void initView() {
    ImageView imageView = findViewById(R.id.naver_logo);
    ImageView imageView1 = findViewById(R.id.kakao_logo);
    
    handler = new Handler(Looper.getMainLooper());
    
    Button button = findViewById(R.id.loginWithEmail);
    button.setOnClickListener(this);
    
    TextView signUpButton = findViewById(R.id.signUp);
    signUpButton.setOnClickListener(this);
    
    imageView.setClipToOutline(true);
    imageView1.setClipToOutline(true);
  }
  
  public void setMiniImageViewToggle(int index) {
    toggleList(index, mCheckList, miniViewList, 1);
  }
  
  private void toggleList(int index, List<Boolean> miniCheckList, ArrayList<ImageView> mViewList, int type) {
    handler.post(new Runnable() {
      @Override
      public void run() {
        boolean isChecked = miniCheckList.get(index);
        
        Log.d("<<<<<<<<<", isChecked + ":" + index);
        
        if (isChecked) {
          mViewList.get(index).setImageResource(R.drawable.ic_baseline_check_24);
          miniCheckList.set(index, false);
        } else {
          mViewList.get(index).setImageResource(R.drawable.check_active);
          miniCheckList.set(index, true);
        }
        
        if (isAllChecked(miniCheckList) && type == 0) {
          checkBox.setChecked(true);
        } else if (!isAllChecked(miniCheckList) && type == 0) {
          checkBox.setChecked(false);
        } else if (isAllChecked(miniCheckList) && type == 1) {
          imageViewList.get(0).setImageResource(R.drawable.check_active);
          checkList.set(0, true);
        } else if (!isAllChecked(miniCheckList) && type == 1) {
          imageViewList.get(0).setImageResource(R.drawable.ic_baseline_check_24);
          checkList.set(0, false);
        }
//
        
        if (index == 0 && type == 0) {
          ListIterator<Boolean> it = mCheckList.listIterator();
          boolean checked = checkList.get(0);
          
          while (it.hasNext()) {
            mCheckList.set(it.nextIndex(), checked);
            miniViewList.forEach(t -> {
              if (checked) {
                t.setImageResource(R.drawable.check_active);
              } else {
                t.setImageResource(R.drawable.ic_baseline_check_24);
              }
            });
            it.next();
          }
        }
        
        nextButtonEnabled(checkList.get(0));
//
//        if (isAllChecked(miniCheckList)) {
//
//          if (type == 0) {
//            checkBox.setChecked(true);
//          }
//
//        } else {
//          if (type == 0) {
//            checkBox.setChecked(false);
//          }
//        }
        
      
      }
    });
  }
  
  public void setImageViewToggle(int index) {
    toggleList(index, checkList, imageViewList, 0);
    
  }
  
  
  public <T> boolean isAllChecked(List<T> list) {
    return list
      .stream()
      .allMatch(s -> {
        int result = Boolean.compare((boolean) s, true);
        return result == 0;
      });
  }
  
  public void nextButtonEnabled(boolean enable) {
    if (enable) {
      next.setEnabled(true);
      next.setBackgroundResource(R.drawable.enable_button);
    } else {
      next.setEnabled(false);
      next.setBackgroundResource(R.drawable.disabled_button);
    }
    
  }
  
  public void setAllImageViewChecking(boolean checked) {
    ListIterator<Boolean> it = checkList.listIterator();
    ListIterator<Boolean> it2 = mCheckList.listIterator();
    
    
    handler.post(new Runnable() {
      @Override
      public void run() {
        if (!checked) {
          layout.setBackgroundResource(R.drawable.login_button);
          
          while (it.hasNext()) {
            checkList.set(it.nextIndex(), false);
            it.next();
          }
          
          while (it2.hasNext()) {
            mCheckList.set(it2.nextIndex(), false);
            it2.next();
          }
          
          imageViewList.forEach(s -> {
            s.setImageResource(R.drawable.ic_baseline_check_24);
          });
          
          miniViewList.forEach(s -> {
            s.setImageResource(R.drawable.ic_baseline_check_24);
          });
          
          nextButtonEnabled(false);
          
        } else {
          layout.setBackgroundResource(R.drawable.active_button);
          
          imageViewList.forEach(s -> {
            s.setImageResource(R.drawable.check_active);
          });
          
          miniViewList.forEach(s -> {
            s.setImageResource(R.drawable.check_active);
          });
          
          
          while (it.hasNext()) {
            checkList.set(it.nextIndex(), true);
            it.next();
          }
          
          while (it2.hasNext()) {
            mCheckList.set(it2.nextIndex(), true);
            it2.next();
            
          }
          
          nextButtonEnabled(true);
          
        }
      }
    });
    
  }
  
  @Override
  public void onClick(View v) {
    
    switch (v.getId()) {
      case R.id.loginWithEmail:
        ts = fm.beginTransaction().add(R.id.login_container, emailLoginFragment);
        ts.addToBackStack(null);
        ts.commit();
        break;
      case R.id.signUp:
        if (bottomSheetDialog != null) {
          bottomSheetDialog.show();
          return;
        }
        ;
        bottomSheetDialog = new BottomSheetDialog(
          LoginActivity.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
          .inflate(
            R.layout.layout_bottom_sheet,
            (LinearLayout) findViewById(R.id.bottomSheetContainer)
          );
        
        ImageView imageView = bottomSheetView.findViewById(R.id.closeButton);
        imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            bottomSheetDialog.dismiss();
          }
        });
        
        checkBox = bottomSheetView.findViewById(R.id.allSelectCheckbox);
        
        imageViewList.add(bottomSheetView.findViewById(R.id.check1));
        imageViewList.add(bottomSheetView.findViewById(R.id.check2));
        imageViewList.add(bottomSheetView.findViewById(R.id.check3));
        imageViewList.add(bottomSheetView.findViewById(R.id.check4));
        imageViewList.add(bottomSheetView.findViewById(R.id.check5));
        miniViewList.add(bottomSheetView.findViewById(R.id.miniCheck1));
        miniViewList.add(bottomSheetView.findViewById(R.id.miniCheck2));
        miniViewList.add(bottomSheetView.findViewById(R.id.miniCheck3));
        
        imageViewList.forEach(t -> {
          t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int index = imageViewList.indexOf((ImageView) v);
              setImageViewToggle(index);
            }
          });
        });
        
        miniViewList.forEach(t -> {
          t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int index = miniViewList.indexOf((ImageView) v);
              setMiniImageViewToggle(index);
            }
          });
        });
        
        layout = (LinearLayout) bottomSheetView.findViewById(R.id.allSelectButton);
        
        layout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "HERE " + checkBox.isChecked(), Toast.LENGTH_SHORT).show();
            checkBox.setChecked(!checkBox.isChecked());
            setAllImageViewChecking(checkBox.isChecked());
          }
        });
        
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
              layout.setBackgroundResource(R.drawable.active_button);
            } else {
              layout.setBackgroundResource(R.drawable.login_button);
            }
          }
        });
        
        next = bottomSheetView.findViewById(R.id.next);
        
        ImageView plust = bottomSheetView.findViewById(R.id.plust);
        LinearLayout layout1 = bottomSheetView.findViewById(R.id.visible_linear);
        
        TextView t = bottomSheetView.findViewById(R.id.must_agree_text);
        
        SpannableStringBuilder spannable
          = new SpannableStringBuilder(t.getText());
        
        int start = t.getText().length() - 5;
        int end = t.length();
        
        spannable.setSpan(
          new ForegroundColorSpan(Color.RED),
          start,
          end,
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        
        
        t.setText(spannable);

//        handler.post(new Runnable() {
//          @Override
//          public void run() {
//          }
//        });
        
        
        plust.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            open = !open;
            if (open) {
              plust.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
              layout1.setVisibility(View.GONE);
            } else {
              plust.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
              layout1.setVisibility(View.VISIBLE);
            }
          }
        });
        
        next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, SignUpAcitivty.class);
            startActivity(intent);
          }
        });
        
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
      
    }
  }
  
  @Override
  protected void onStop() {
    super.onStop();
    if (bottomSheetDialog != null) {
      bottomSheetDialog.dismiss();
    }
  }
  
  public void toast(String s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }
  
  public void onFragmentBack() {
    Log.d("BACK", "BACK");
    onBackPressed();
  }
  
  @Override
  public void onBackPressed() {
    
    
    if (isShowing()) {
      hideProgressDialog();
    } else {
      super.onBackPressed();
    }
  }
}