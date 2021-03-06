package com.szxb.s8api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.aratek.fp.Bione;
import cn.com.aratek.fp.FingerprintImage;
import cn.com.aratek.fp.FingerprintScanner;
import cn.com.aratek.fp.OnUsbPermissionGrantedListener;
import cn.com.aratek.util.Result;

/**
 * 作者：Tangren_ on 2017/1/14 0014.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class Fingerprint extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FingerprintDemo";
    private static final String FP_DB_PATH = "/sdcard/fp.db";

    private TextView mSN;
    private TextView mFwVersion;
    private Button mBtnOpenOrCloseDevice;
    private Button mBtnEnroll;
    private Button mBtnVerify;
    private Button mBtnIdentify;
    private Button mBtnClear;
    private Button mBtnShow;
    private EditText mCaptureTime;
    private EditText mExtractTime;
    private EditText mGeneralizeTime;
    private EditText mVerifyTime;
    private ImageView mFingerprintImage;
    private FingerprintScanner mScanner;
    private int mId;
    private boolean mDeviceOpened = false;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint2);

        mScanner = new FingerprintScanner(this);
        mScanner.setOnUsbPermissionGrantedListener(new OnUsbPermissionGrantedListener() {
            @Override
            public void onUsbPermissionGranted(boolean isGranted) {
                if (isGranted) {
                    mSN.setText(getString(R.string.fps_sn, (String) mScanner.getSN().data));
                    mFwVersion.setText(getString(R.string.fps_fw, (String) mScanner.getFirmwareVersion().data));
                    enableControl(true);
                } else {
                    mSN.setText(getString(R.string.fps_sn, "null"));
                    mFwVersion.setText(getString(R.string.fps_fw, "null"));
                    enableControl(false);
                }
            }
        });

        mSN = (TextView) findViewById(R.id.tv_fps_sn);
        mFwVersion = (TextView) findViewById(R.id.tv_fps_fw);
        mCaptureTime = (EditText) findViewById(R.id.captureTime);
        mExtractTime = (EditText) findViewById(R.id.extractTime);
        mGeneralizeTime = (EditText) findViewById(R.id.generalizeTime);
        mVerifyTime = (EditText) findViewById(R.id.verifyTime);
        mFingerprintImage = (ImageView) findViewById(R.id.fingerimage);

        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);

        mBtnOpenOrCloseDevice = (Button) findViewById(R.id.bt_open_close);
        mBtnEnroll = (Button) findViewById(R.id.bt_enroll);
        mBtnVerify = (Button) findViewById(R.id.bt_verify);
        mBtnIdentify = (Button) findViewById(R.id.bt_identify);
        mBtnClear = (Button) findViewById(R.id.bt_clear);
        mBtnShow = (Button) findViewById(R.id.bt_show);

        enableControl(false);

        updateSingerTestText(-1, -1, -1, -1);

        mToolbar.setTitle("指纹模块");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        api_interface.usbctrl((byte) 2);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        api_interface.usbctrl((byte) 3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_open_close:
                if (!mDeviceOpened) {
                    openDevice();
                } else {
                    closeDevice();
                }
                break;
            case R.id.bt_enroll:
                enroll();
                break;
            case R.id.bt_verify:
                verify();
                break;
            case R.id.bt_identify:
                identify();
                break;
            case R.id.bt_clear:
                clearFingerprintDatabase();
                break;
            case R.id.bt_show:
                showFingerprintImage();
                break;
        }
    }

    private void updateFingerprintImage(FingerprintImage fi) {
        byte[] fpBmp = null;
        Bitmap bitmap = null;
        if (fi != null && (fpBmp = fi.convert2Bmp()) != null && (bitmap = BitmapFactory.decodeByteArray(fpBmp, 0, fpBmp.length)) != null) {
            mFingerprintImage.setImageBitmap(bitmap);
        } else {
            mFingerprintImage.setImageResource(R.mipmap.nofinger);
        }
    }

    private void updateSingerTestText(long captureTime, long extractTime, long generalizeTime, long verifyTime) {
        if (captureTime < 0) {
            mCaptureTime.setText(getString(R.string.not_done));
        } else if (captureTime < 1) {
            mCaptureTime.setText("< 1ms");
        } else {
            mCaptureTime.setText(captureTime + "ms");
        }

        if (extractTime < 0) {
            mExtractTime.setText(getString(R.string.not_done));
        } else if (extractTime < 1) {
            mExtractTime.setText("< 1ms");
        } else {
            mExtractTime.setText(extractTime + "ms");
        }

        if (generalizeTime < 0) {
            mGeneralizeTime.setText(getString(R.string.not_done));
        } else if (generalizeTime < 1) {
            mGeneralizeTime.setText("< 1ms");
        } else {
            mGeneralizeTime.setText(generalizeTime + "ms");
        }

        if (verifyTime < 0) {
            mVerifyTime.setText(getString(R.string.not_done));
        } else if (verifyTime < 1) {
            mVerifyTime.setText("< 1ms");
        } else {
            mVerifyTime.setText(verifyTime + "ms");
        }
    }

    private void enableControl(boolean enable) {
        if (mScanner == null) {
            mBtnOpenOrCloseDevice.setEnabled(false);
            mBtnEnroll.setEnabled(false);
            mBtnVerify.setEnabled(false);
            mBtnIdentify.setEnabled(false);
            mBtnClear.setEnabled(false);
            mBtnShow.setEnabled(false);
        } else {
            mBtnOpenOrCloseDevice.setEnabled(true);
            mBtnEnroll.setEnabled(enable);
            mBtnVerify.setEnabled(enable);
            mBtnIdentify.setEnabled(enable);
            mBtnClear.setEnabled(enable);
            mBtnShow.setEnabled(enable);
        }
    }

    private void openDevice() {
        int error;
        if ((error = mScanner.open()) != FingerprintScanner.RESULT_OK) {
            Toast.makeText(this, getString(R.string.fingerprint_device_open_failed) + error, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.fingerprint_device_open_success), Toast.LENGTH_SHORT).show();
        }
        if ((error = Bione.initialize(this, FP_DB_PATH)) != Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.algorithm_initialization_failed) + error, Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, "Fingerprint algorithm version: " + Bione.getVersion());
        mDeviceOpened = true;
        mBtnOpenOrCloseDevice.setText(getString(R.string.close_device));
    }

    private void closeDevice() {
        enableControl(false);
        int error;
        if ((error = mScanner.close()) != FingerprintScanner.RESULT_OK) {
            Toast.makeText(this, getString(R.string.fingerprint_device_close_failed) + error, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.fingerprint_device_close_success), Toast.LENGTH_SHORT).show();
        }
        if ((error = Bione.exit()) != Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.algorithm_cleanup_failed) + error, Toast.LENGTH_SHORT).show();
        }
        mDeviceOpened = false;
        mBtnOpenOrCloseDevice.setText(getString(R.string.open_device));
    }

    private void enroll() {
        mScanner.prepare();
        long startTime = System.currentTimeMillis();
        Result res = mScanner.capture();
        long captureTime = System.currentTimeMillis() - startTime;
        mScanner.finish();
        if (res.error != FingerprintScanner.RESULT_OK) {
            Toast.makeText(this, getString(R.string.capture_image_failed) + res.error, Toast.LENGTH_SHORT).show();
            return;
        }
        FingerprintImage fi = (FingerprintImage) res.data;
        Log.i(TAG, "Fingerprint image quality is " + Bione.getFingerprintQuality(fi));
        updateFingerprintImage(fi);
        startTime = System.currentTimeMillis();
        res = Bione.extractFeature(fi);
        long extractTime = System.currentTimeMillis() - startTime;
        if (res.error != Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.enroll_failed_because_of_extract_feature) + res.error, Toast.LENGTH_SHORT).show();
            updateSingerTestText(captureTime, extractTime, -1, -1);
            return;
        }
        byte[] fpFeat = (byte[]) res.data;
        startTime = System.currentTimeMillis();
        res = Bione.makeTemplate(fpFeat, fpFeat, fpFeat);
        long generalizeTime = System.currentTimeMillis() - startTime;
        if (res.error != Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.enroll_failed_because_of_make_template) + res.error, Toast.LENGTH_SHORT).show();
            updateSingerTestText(captureTime, extractTime, generalizeTime, -1);
            return;
        }
        byte[] fpTemp = (byte[]) res.data;
        int id = Bione.getFreeID();
        if (id < 0) {
            Toast.makeText(this, getString(R.string.enroll_failed_because_of_get_id) + id, Toast.LENGTH_SHORT).show();
            updateSingerTestText(captureTime, extractTime, generalizeTime, -1);
            return;
        }
        int ret = Bione.enroll(id, fpTemp);
        if (ret != Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.enroll_failed_because_of_error) + ret, Toast.LENGTH_SHORT).show();
            updateSingerTestText(captureTime, extractTime, generalizeTime, -1);
            return;
        }
        mId = id;
        Toast.makeText(this, getString(R.string.enroll_success) + id, Toast.LENGTH_SHORT).show();
        updateSingerTestText(captureTime, extractTime, generalizeTime, -1);
    }

    private void verify() {
        mScanner.prepare();
        long startTime = System.currentTimeMillis();
        Result res = mScanner.capture();
        long captureTime = System.currentTimeMillis() - startTime;
        mScanner.finish();
        if (res.error != FingerprintScanner.RESULT_OK) {
            Toast.makeText(this, getString(R.string.capture_image_failed) + res.error, Toast.LENGTH_SHORT).show();
            return;
        }
        FingerprintImage fi = (FingerprintImage) res.data;
        Log.i(TAG, "Fingerprint image quality is " + Bione.getFingerprintQuality(fi));
        updateFingerprintImage(fi);
        startTime = System.currentTimeMillis();
        res = Bione.extractFeature(fi);
        long extractTime = System.currentTimeMillis() - startTime;
        if (res.error != Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.verify_failed_because_of_extract_feature) + res.error, Toast.LENGTH_SHORT).show();
            updateSingerTestText(captureTime, extractTime, -1, -1);
            return;
        }
        byte[] fpFeat = (byte[]) res.data;
        startTime = System.currentTimeMillis();
        res = Bione.verify(mId, fpFeat);
        long verifyTime = System.currentTimeMillis() - startTime;
        if (res.error != Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.verify_failed_because_of_error) + res.error, Toast.LENGTH_SHORT).show();
            updateSingerTestText(captureTime, extractTime, -1, verifyTime);
            return;
        }
        if ((Boolean) res.data) {
            Toast.makeText(this, getString(R.string.fingerprint_match), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.fingerprint_not_match), Toast.LENGTH_SHORT).show();
        }
        updateSingerTestText(captureTime, extractTime, -1, verifyTime);
    }

    private void identify() {
        mScanner.prepare();
        long startTime = System.currentTimeMillis();
        Result res = mScanner.capture();
        long captureTime = System.currentTimeMillis() - startTime;
        mScanner.finish();
        if (res.error != FingerprintScanner.RESULT_OK) {
            Toast.makeText(this, getString(R.string.capture_image_failed) + res.error, Toast.LENGTH_SHORT).show();
            return;
        }
        FingerprintImage fi = (FingerprintImage) res.data;
        Log.i(TAG, "Fingerprint image quality is " + Bione.getFingerprintQuality(fi));
        updateFingerprintImage(fi);
        startTime = System.currentTimeMillis();
        res = Bione.extractFeature(fi);
        long extractTime = System.currentTimeMillis() - startTime;
        if (res.error != Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.identify_failed_because_of_extract_feature) + res.error, Toast.LENGTH_SHORT).show();
            updateSingerTestText(captureTime, extractTime, -1, -1);
            return;
        }
        byte[] fpFeat = (byte[]) res.data;
        startTime = System.currentTimeMillis();
        int id = Bione.identify(fpFeat);
        long verifyTime = System.currentTimeMillis() - startTime;
        if (id < 0) {
            Toast.makeText(this, getString(R.string.identify_failed_because_of_error) + id, Toast.LENGTH_SHORT).show();
            updateSingerTestText(captureTime, extractTime, -1, verifyTime);
            return;
        }
        Toast.makeText(this, getString(R.string.identify_match) + id, Toast.LENGTH_SHORT).show();
        updateSingerTestText(captureTime, extractTime, -1, verifyTime);
    }

    private void clearFingerprintDatabase() {
        int error = Bione.clear();
        if (error == Bione.RESULT_OK) {
            Toast.makeText(this, getString(R.string.clear_fingerprint_database_success), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.clear_fingerprint_database_failed) + error, Toast.LENGTH_SHORT).show();
        }
    }

    private void showFingerprintImage() {
        mScanner.prepare();
        long startTime = System.currentTimeMillis();
        Result res = mScanner.capture();
        long captureTime = System.currentTimeMillis() - startTime;
        mScanner.finish();
        if (res.error != FingerprintScanner.RESULT_OK) {
            Toast.makeText(this, getString(R.string.capture_image_failed) + res.error, Toast.LENGTH_SHORT).show();
            return;
        }
        FingerprintImage fi = (FingerprintImage) res.data;
        Log.i(TAG, "Fingerprint image quality is " + Bione.getFingerprintQuality(fi));
        updateFingerprintImage(fi);
        updateSingerTestText(captureTime, -1, -1, -1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

