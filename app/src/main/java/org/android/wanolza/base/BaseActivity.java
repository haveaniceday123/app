package org.android.wanolza.base;

import android.app.ProgressDialog;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.android.wanolza.ui.MyDialog;

public class BaseActivity extends AppCompatActivity {
  
  public MyDialog loadingDialog;
  
  public void toastMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
  
  public void showProgressDialog() {
    if (loadingDialog == null) {
      loadingDialog = new MyDialog(this);
    }
    loadingDialog.showDialog();
  }
  
  public void hideProgressDialog() {
    if (loadingDialog != null && loadingDialog.isShowing()) {
      loadingDialog.hideDialog();
    }
  }
  
  public boolean isShowing() {
    if (loadingDialog == null) {
      return false;
    }
    
    if (loadingDialog.isShowing()) {
      return true;
    }
    return false;
  }
  
  @Override
  protected void onStop() {
    super.onStop();
    hideProgressDialog();
  }
  
}
