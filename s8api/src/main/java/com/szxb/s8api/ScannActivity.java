package com.szxb.s8api;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.szxb.zxing.activity.CaptureFragment;
import com.szxb.zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScannActivity extends AppCompatActivity {

    @BindView(R.id.fl_my_container)
    FrameLayout flMyContainer;
    @BindView(R.id.jianyi)
    TextView jianyi;
    @BindView(R.id.light)
    Button light;
    @BindView(R.id.close)
    Button close;
    @BindView(R.id.activity_second)
    FrameLayout activitySecond;
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    private CaptureFragment captureFragment;
    private boolean iscontinu = false;
    private boolean islight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scann);
        ButterKnife.bind(this);

        captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        captureFragment.setContinuScann(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        mtoolbar.setTitle("");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            TSnackbar.make(findViewById(android.R.id.content), result, TSnackbar.LENGTH_LONG).show();
        }

        @Override
        public void onAnalyzeFailed() {
            TSnackbar.make(findViewById(android.R.id.content), "扫码失败", TSnackbar.LENGTH_LONG).show();
        }
    };

    @OnClick({R.id.light, R.id.close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.light:
                if (islight) {
                    light.setText(this.getResources().getString(
                            R.string.openLight));
                    CodeUtils.isLightEnable(false);
                    islight = false;

                } else {
                    light.setText(this.getResources().getString(
                            R.string.closeLight));
                    CodeUtils.isLightEnable(true);
                    islight = true;
                }
                break;
            case R.id.close:
                finish();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
