package com.example.lenovo.daochulvxing.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.daochulvxing.R;
import com.example.lenovo.daochulvxing.base.BaseActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import presenter.MainPresenter;
import view.MainView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements View.OnClickListener {
    /**
     * 请输入手机号
     */
    private EditText mEtPhone;
    private ImageView mPhone;
    /**
     * +86
     */
    private TextView mBaliu;
    /**
     * 发送验证码
     */
    private Button mYanzheng;
    private ImageView mUmengQq;

    //5cce581f0cafb238380003b2
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int Layoutid() {
        return R.layout.activity_main;
    }

    //友盟qq授权
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initView() {
        super.initView();
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mPhone = (ImageView) findViewById(R.id.phone);
        mBaliu = (TextView) findViewById(R.id.baliu);
        mYanzheng = (Button) findViewById(R.id.yanzheng);
        mYanzheng.setOnClickListener(this);
        mUmengQq = (ImageView) findViewById(R.id.umeng_qq);
        mUmengQq.setOnClickListener(this);

        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.toString().length();
                if (length==11){
                        mYanzheng.setBackground(getResources().getDrawable(R.mipmap.button_highlight));
                } else{
                    mYanzheng.setBackground(getResources().getDrawable(R.mipmap.button_unavailable));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.yanzheng:
                String s = mEtPhone.getText().toString();
                String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
                if (s.matches(REGEX_MOBILE)) {
                    Intent intent = new Intent(MainActivity.this, VirifyActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "手机号有误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.umeng_qq:
                UMShareAPI.get(this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
    }
    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }
        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
