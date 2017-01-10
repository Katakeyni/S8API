// Generated code from Butter Knife. Do not modify!
package com.szxb.s8api;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  private View view2131558522;

  private View view2131558523;

  private View view2131558525;

  private View view2131558524;

  private View view2131558526;

  private View view2131558527;

  private View view2131558528;

  private View view2131558530;

  private View view2131558529;

  @UiThread
  public MainActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mpb = Utils.findRequiredViewAsType(source, R.id.progressbar, "field 'mpb'", ProgressBar.class);
    target.showTV = Utils.findRequiredViewAsType(source, R.id.showtext, "field 'showTV'", EditText.class);
    view = Utils.findRequiredView(source, R.id.getversion, "field 'getversion' and method 'onClick'");
    target.getversion = Utils.castView(view, R.id.getversion, "field 'getversion'", Button.class);
    view2131558522 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.scanner, "field 'scanner' and method 'onClick'");
    target.scanner = Utils.castView(view, R.id.scanner, "field 'scanner'", Button.class);
    view2131558523 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rfid, "field 'rfid' and method 'onClick'");
    target.rfid = Utils.castView(view, R.id.rfid, "field 'rfid'", Button.class);
    view2131558525 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.Magneticcard, "field 'Magneticcard' and method 'onClick'");
    target.Magneticcard = Utils.castView(view, R.id.Magneticcard, "field 'Magneticcard'", Button.class);
    view2131558524 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.print, "field 'print' and method 'onClick'");
    target.print = Utils.castView(view, R.id.print, "field 'print'", Button.class);
    view2131558526 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.psam, "field 'psam' and method 'onClick'");
    target.psam = Utils.castView(view, R.id.psam, "field 'psam'", Button.class);
    view2131558527 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ICcard, "field 'ICcard' and method 'onClick'");
    target.ICcard = Utils.castView(view, R.id.ICcard, "field 'ICcard'", Button.class);
    view2131558528 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.clean, "field 'clean' and method 'onClick'");
    target.clean = Utils.castView(view, R.id.clean, "field 'clean'", Button.class);
    view2131558530 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.webview, "field 'webview' and method 'onClick'");
    target.webview = Utils.castView(view, R.id.webview, "field 'webview'", Button.class);
    view2131558529 = view;
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

    view2131558522.setOnClickListener(null);
    view2131558522 = null;
    view2131558523.setOnClickListener(null);
    view2131558523 = null;
    view2131558525.setOnClickListener(null);
    view2131558525 = null;
    view2131558524.setOnClickListener(null);
    view2131558524 = null;
    view2131558526.setOnClickListener(null);
    view2131558526 = null;
    view2131558527.setOnClickListener(null);
    view2131558527 = null;
    view2131558528.setOnClickListener(null);
    view2131558528 = null;
    view2131558530.setOnClickListener(null);
    view2131558530 = null;
    view2131558529.setOnClickListener(null);
    view2131558529 = null;

    this.target = null;
  }
}
