package org.android.wanolza.util;

import io.reactivex.rxjava3.subjects.PublishSubject;
import org.android.wanolza.R;

public final class RxBus {
  
  private static final String TAG = RxBus.class.getSimpleName();
  private static final RxBus Instance = new RxBus();
  
  public static RxBus getInstance() {
    return Instance;
  }
  
  public RxBus() {}
  
  private final PublishSubject<Object> publishSubject
     = PublishSubject.create();
  
}
