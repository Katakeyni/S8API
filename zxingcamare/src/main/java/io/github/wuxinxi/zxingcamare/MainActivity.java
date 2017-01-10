package io.github.wuxinxi.zxingcamare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_scan_barcode)
    Button btnScanBarcode;
    @BindView(R.id.tv_scan_result)
    TextView tvScanResult;
    @BindView(R.id.et_qr_string)
    EditText etQrString;
    @BindView(R.id.btn_add_qrcode)
    Button btnAddQrcode;
    @BindView(R.id.iv_qr_image)
    ImageView ivQrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_scan_barcode, R.id.btn_add_qrcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_barcode:
                //打开扫描界面扫描条形码或二维码
//                Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
//                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.btn_add_qrcode:
                break;
        }
    }
}
