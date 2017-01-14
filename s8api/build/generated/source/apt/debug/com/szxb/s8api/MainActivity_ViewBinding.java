// Generated code from Butter Knife. Do not modify!
package com.szxb.s8api;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  private View view2131558536;

  private View view2131558537;

  private View view2131558539;

  private View view2131558538;

  private View view2131558540;

  private View view2131558541;

  private View view2131558542;

  private View view2131558545;

  private View view2131558543;

  private View view2131558544;

  @UiThread
  public MainActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mpb = Utils.findRequiredViewAsType(source, R.id.progressbar, "field 'mpb'", ProgressBar.class);
    target.showTV = Utils.findRequiredViewAsType(source, R.id.showtext, "field 'showTV'", EditText.class);
    view = Utils.findRequiredView(source, R.id.getversion, "field 'getversion' and method 'onClick'");
    target.getversion = Utils.castView(view, R.id.getversion, "field 'getversion'", Button.class);
    view2131558536 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.scanner, "field 'scanner' and method 'onClick'");
    target.scanner = Utils.castView(view, R.id.scanner, "field 'scanner'", Button.class);
    view2131558537 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rfid, "field 'rfid' and method 'onClick'");
    target.rfid = Utils.castView(view, R.id.rfid, "field 'rfid'", Button.class);
    view2131558539 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.Magneticcard, "field 'Magneticcard' and method 'onClick'");
    target.Magneticcard = Utils.castView(view, R.id.Magneticcard, "field 'Magneticcard'", Button.class);
    view2131558538 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.print, "field 'print' and method 'onClick'");
    target.print = Utils.castView(view, R.id.print, "field 'print'", Button.class);
    view2131558540 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.psam, "field 'psam' and method 'onClick'");
    target.psam = Utils.castView(view, R.id.psam, "field 'psam'", Button.class);
    view2131558541 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ICcard, "field 'ICcard' and method 'onClick'");
    target.ICcard = Utils.castView(view, R.id.ICcard, "field 'ICcard'", Button.class);
    view2131558542 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.clean, "field 'clean' and method 'onClick'");
    target.clean = Utils.castView(view, R.id.clean, "field 'clean'", Button.class);
    view2131558545 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.webview, "field 'webview' and method 'onClick'");
    target.webview = Utils.castView(view, R.id.webview, "field 'webview'", Button.class);
    view2131558543 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.textViewHide = Utils.findRequiredViewAsType(source, R.id.textViewHide, "field 'textViewHide'", TextView.class);
    view = Utils.findRequiredView(source, R.id.zhiwen, "field 'zhiwen' and method 'onClick'");
    target.zhiwen = Utils.castView(view, R.id.zhiwen, "field 'zhiwen'", Button.class);
    view2131558544 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mpb = null;
    target.showTV = null;
    target.getversion = null;
    target.scanner = null;
    target.rfid = null;
    target.Magneticcard = null;
    target.print = null;
    target.psam = null;
    target.ICcard = null;
    target.clean = null;
    target.webview = null;
    target.textViewHide = null;
    target.zhiwen = null;

    view2131558536.setOnClickListener(null);
    view2131558536 = null;
    view2131558537.setOnClickListener(null);
    view2131558537 = null;
    view2131558539.setOnClickListener(null);
    view2131558539 = null;
    view2131558538.setOnClickListener(null);
    view2131558538 = null;
    view2131558540.setOnClickListener(null);
    view2131558540 = null;
    view2131558541.setOnClickListener(null);
    view2131558541 = null;
    view2131558542.setOnClickListener(null);
    view2131558542 = null;
    view2131558545.setOnClickListener(null);
    view2131558545 = null;
    view2131558543.setOnClickListener(null);
    view2131558543 = null;
    view2131558544.setOnClickListener(null);
    view2131558544 = null;

    this.target = null;
  }
}
