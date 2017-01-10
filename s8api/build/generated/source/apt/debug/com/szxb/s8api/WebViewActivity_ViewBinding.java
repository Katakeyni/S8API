// Generated code from Butter Knife. Do not modify!
package com.szxb.s8api;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebViewActivity_ViewBinding<T extends WebViewActivity> implements Unbinder {
  protected T target;

  @UiThread
  public WebViewActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mtoolbar = Utils.findRequiredViewAsType(source, R.id.mtoolbar, "field 'mtoolbar'", Toolbar.class);
    target.webview = Utils.findRequiredViewAsType(source, R.id.webview, "field 'webview'", WebView.class);
    target.activityWebView = Utils.findRequiredViewAsType(source, R.id.activity_web_view, "field 'activityWebView'", LinearLayout.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mtoolbar = null;
    target.webview = null;
    target.activityWebView = null;
    target.progressBar = null;

    this.target = null;
  }
}
