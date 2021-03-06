package io.github.wuxinxi.zxingcamare;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.szxb.zxing.activity.CaptureFragment;
import com.szxb.zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者：Tangren_ on 2016/12/27 0027.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class Main extends AppCompatActivity {
    @BindView(R.id.fl_my_container)
    FrameLayout flMyContainer;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.jianyi)
    TextView jianyi;
    @BindView(R.id.mainsweep)
    Button mainsweep;
    @BindView(R.id.checkOrder)
    Button checkOrder;
    @BindView(R.id.activity_second)
    FrameLayout activitySecond;

    private CaptureFragment captureFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        ButterKnife.bind(this);

        captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        captureFragment.setContinuScann(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//            image.setVisibility(View.VISIBLE);
//            image.setImageBitmap(mBitmap);
            Toast.makeText(Main.this, result, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnalyzeFailed() {
            Toast.makeText(Main.this, "扫码失败！", Toast.LENGTH_SHORT).show();
        }
    };
}
