package com.szxb.s8api;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.szxb.api.jni_interface.api_interface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.progressbar)
    ProgressBar mpb;
    @BindView(R.id.showtext)
    EditText showTV;
    @BindView(R.id.getversion)
    Button getversion;
    @BindView(R.id.scanner)
    Button scanner;
    @BindView(R.id.rfid)
    Button rfid;
    @BindView(R.id.Magneticcard)
    Button Magneticcard;
    @BindView(R.id.print)
    Button print;
    @BindView(R.id.psam)
    Button psam;
    @BindView(R.id.ICcard)
    Button ICcard;
    @BindView(R.id.clean)
    Button clean;
    @BindView(R.id.webview)
    Button webview;
    @BindView(R.id.textViewHide)
    TextView textViewHide;

    public static final int MSG = 1;
    private MenuItem menuItem = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG:
                    // 设置滚动条和text的值
                    showTV.append(".");
                    mpb.setProgress(msg.arg1);
                    if (9999 == msg.arg1) {
                        showTV.append("\r\n");
                        String showstr = (String) MainActivity.this
                                .getResources().getText(R.string.showmsg);
                        showTV.append(showstr);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.getversion, R.id.scanner, R.id.Magneticcard, R.id.print, R.id.psam,
            R.id.ICcard, R.id.clean, R.id.webview, R.id.rfid})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getversion:
                getversion.setEnabled(false);
                showTV.setText("");

                String msg = api_interface.deviceGetVersion();
                if (null != msg) {
                    showTV.append(msg);
                    showTV.append("\r\n");
                }
                getversion.setEnabled(true);
                get();
                break;
            case R.id.scanner:
                startActivity(new Intent(MainActivity.this, ScannActivity.class));
                break;
            case R.id.Magneticcard:
                Magneticcard.setEnabled(false);

                String[] ret = api_interface.msr_getData();
                for (int i = 0; i < 3; i++) {
                    if (ret[i] != null) {
                        showTV.append("track" + i + ":");
                        showTV.append("\r\n");
                        showTV.append(ret[i]);
                        showTV.append("\r\n");
                    }
                }

                Magneticcard.setEnabled(true);
                get();

                break;
            case R.id.rfid:
                byte[] recv = new byte[5];

                String str = api_interface.MifareGetSNR(recv);
                if (null != str) {
                    showTV.append(str);
                    showTV.append("\r\n");

                    if ((recv[2] & 0x20) != 0) {
                        showTV.append("cpu card!");
                        showTV.append("\r\n");

                        String rats = api_interface.TypeA_RATS();
                        if (rats != null) {
                            showTV.append(rats);
                            showTV.append("\r\n");
                        }

                        String[] sapdu = api_interface.RFID_APDU(new String("0084000004"));

                        if (sapdu != null) {
                            if (sapdu[0] != null) {
                                showTV.append(sapdu[0]);
                                showTV.append("\r\n");
                            }
                            if (sapdu[1] != null) {
                                showTV.append(sapdu[1]);
                                showTV.append("\r\n");
                            }
                        }
                    } else {
                        showTV.append("m1 card!");
                    }
                    showTV.append("\r\n");

                }
                break;
            case R.id.print:
                print.setEnabled(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        textViewHide.setDrawingCacheEnabled(true);
                        textViewHide.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                                , View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                        textViewHide.layout(0, 0, textViewHide.getMeasuredWidth(), textViewHide.getMeasuredHeight());
                        Bitmap bitmap = textViewHide.getDrawingCache();
//
//                        Bitmap bm = BitmapFactory.decodeResource(
//                                MainActivity.this.getResources(),
//                                R.mipmap.test2dbarcode);
//
                        api_interface.printBitmap(bitmap, 0, 0);
                        api_interface.printertest();
                        textViewHide.destroyDrawingCache();
                        //                    api_interface.printerStr(test.getBytes());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // 更新UI
                                print.setEnabled(true);
                            }

                        });
                    }
                });
                thread.start();
                get();
                break;
            case R.id.psam:
                String sret;
                int i;
                for (i = 0; i < 5; i++)
                    if ((sret = api_interface.psamCardReset(9600, i)) != null) {
                        showTV.append("slot" + i + ":");
                        showTV.append("\r\n");
                        showTV.append(sret);
                        showTV.append("\r\n");
                        String[] sapdu = api_interface.psamCardSendAPDUT0(i,
                                new String("0084000008"));
                        showTV.append(sapdu[0]);
                        showTV.append("\r\n");
                        showTV.append(sapdu[1]);
                        showTV.append("\r\n");
                    }
                get();
                break;
            case R.id.ICcard:
                String sret1;
                sret1 = api_interface.ICCardPowerOn();
                if (null != sret1) {
                    showTV.append(sret1);
                    showTV.append("\r\n");
                    String[] sapdu = api_interface.ICCardSendAPDU(new String(
                            "0084000004"));
                    if (sapdu != null) {
                        if (sapdu[0] != null) {
                            showTV.append(sapdu[0]);
                            showTV.append("\r\n");
                        }
                        if (sapdu[1] != null) {
                            showTV.append(sapdu[1]);
                            showTV.append("\r\n");
                        }
                    }
                    api_interface.ICCardPowerOff();
                }
                get();
                break;
            case R.id.clean:
                showTV.setText("");
                break;
            case R.id.webview:
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:

                showTV.append("开始更新...\n");
                Thread thread1 = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        AssetManager ass = MainActivity.this.getAssets();
                        api_interface.ymodemUpdate(ass, "POS_Board.bin",
                                MainActivity.this);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // 更新UI

                            }

                        });
                    }
                });

                thread1.start();
                break;
            case R.id.host:
                int nHandle;
                if (menuItem.getTitle().equals("HOST")) {
                    menuItem.setTitle("DEVICE");

                    nHandle = api_interface.usbctrl((byte) 2);
                    nHandle = api_interface.usbctrl((byte) 0);

                } else {
                    menuItem.setTitle("HOST");
                    nHandle = api_interface.usbctrl((byte) 3);

                    nHandle = api_interface.usbctrl((byte) 1);
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menuItem = menu.findItem(R.id.host);
        return true;
    }

    private void get() {
        showTV.setSelection(showTV.getText().length());
    }


    public void show(int pressure) {
        Message msg = new Message();
        msg.what = MSG;
        msg.arg1 = pressure;
        handler.sendMessage(msg);
    }
}
