// Generated code from Butter Knife. Do not modify!
package com.szxb.s8api;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScannActivity_ViewBinding<T extends ScannActivity> implements Unbinder {
  protected T target;

  private View view2131558549;

  private View view2131558550;

  @UiThread
  public ScannActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.flMyContainer = Utils.findRequiredViewAsType(source, R.id.fl_my_container, "field 'flMyContainer'", FrameLayout.class);
    target.jianyi = Utils.findRequiredViewAsType(source, R.id.jianyi, "field 'jianyi'", TextView.class);
    view = Utils.findRequiredView(source, R.id.light, "field 'light' and method 'onClick'");
    target.light = Utils.castView(view, R.id.light, "field 'light'", Button.class);
    view2131558549 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.close, "field 'close' and method 'onClick'");
    target.close = Utils.castView(view, R.id.close, "field 'close'", Button.class);
    view2131558550 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.activitySecond = Utils.findRequiredViewAsType(source, R.id.activity_second, "field 'activitySecond'", FrameLayout.class);
    target.mtoolbar = Utils.findRequiredViewAsType(source, R.id.mtoolbar, "field 'mtoolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.flMyContainer = null;
    target.jianyi = null;
    target.light = null;
    target.close = null;
    target.activitySecond = null;
    target.mtoolbar = null;

    view2131558549.setOnClickListener(null);
    view2131558549 = null;
    view2131558550.setOnClickListener(null);
    view2131558550 = null;

    this.target = null;
  }
}
