package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    int count = 60;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (count > 0) {
                count--;
                mChongxinfasong.setText("重新发送(" + count-- + ")");
            } else {
                timer.cancel();
            }
        }
    };
    /**
     * 重新发送验证码(56s)
     */
    private TextView mChongxinfasong;
    private EditText mYan;
    private TextView mTvLoding;
    private Timer timer;
    private EditText mEt1;
    private EditText mEt2;
    private EditText mEt3;
    private ImageView mMain2Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuruyanzheng_layout);
        initView();


    }

    private void initView() {
        mChongxinfasong = (TextView) findViewById(R.id.chongxinfasong);
        mYan = (EditText) findViewById(R.id.yan);
        mTvLoding = (TextView) findViewById(R.id.tv_loding);
        mEt1 = (EditText) findViewById(R.id.et1);
        mEt2 = (EditText) findViewById(R.id.et2);
        mEt3 = (EditText) findViewById(R.id.et3);
        mMain2Image = (ImageView) findViewById(R.id.main2_image);
        mYan.addTextChangedListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(66);
            }
        }, 1000, 1000);


        mMain2Image.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() == 1) {
            if (mEt1.isFocused()) {
                mEt1.clearFocus();
                mEt2.requestFocus();
            } else if (mEt2.isFocused()) {
                mEt2.clearFocus();
                mEt3.requestFocus();
            } else if (mEt3.isFocused()) {
                mEt3.clearFocus();
                mYan.requestFocus();
            } else if (mYan.isFocused()) {
                if (mEt1.getText().toString().length() > 0 && mEt2.getText().toString().length() > 0 && mEt3.getText().toString().length() > 0) {
                    Toast.makeText(this, "验证码输入完成", Toast.LENGTH_SHORT).show();
                    mEt1.setEnabled(false);
                    mEt2.setEnabled(false);
                    mEt3.setEnabled(false);
                    mYan.setEnabled(false);
                    mTvLoding.setText("请等待" + "...");
                }
            }
        }

        if (s.toString().length() == 0) {
            if (mYan.isFocused()) {
                mYan.clearFocus();
                mEt3.requestFocus();
            } else if (mEt2.isFocused()) {
                mEt2.clearFocus();
                mEt1.requestFocus();
            } else if (mEt3.isFocused()) {
                mEt3.clearFocus();
                mEt2.requestFocus();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.main2_image:
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
