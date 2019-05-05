package com.example.lenovo.daochulvxing.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.daochulvxing.R;
import com.example.lenovo.daochulvxing.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import presenter.VirifyPresenter;
import view.VirifyView;

public class VirifyActivity extends BaseActivity<VirifyView, VirifyPresenter> implements View.OnClickListener {
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
    private ImageView mMain2Image;
    private TextView mChongxinfasong;
    private EditText mEt1;
    private EditText mEt2;
    private EditText mEt3;
    private EditText mYan;
    private TextView mTvLoding;
    private Timer timer;
    @Override
    protected VirifyPresenter initPresenter() {
        return new VirifyPresenter();
    }
    @Override
    protected int Layoutid() {
        return R.layout.activity_virify;
    }
    
    public void initView() {
        mMain2Image = (ImageView) findViewById(R.id.main2_image);
        mChongxinfasong = (TextView) findViewById(R.id.chongxinfasong);
        mEt1 = (EditText) findViewById(R.id.et1);
        mEt2 = (EditText) findViewById(R.id.et2);
        mEt3 = (EditText) findViewById(R.id.et3);
        mYan = (EditText) findViewById(R.id.yan);
        mTvLoding = (TextView) findViewById(R.id.tv_loding);
        mMain2Image.setOnClickListener(this);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(66);
            }
        }, 1000, 1000);

        mYan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mTvLoding.setText("请稍后...");
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.main2_image:
                finish();
                break;
        }
    }
}