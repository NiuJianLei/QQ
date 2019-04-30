package com.example.genknews.activity.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 *
 */

public class BaseModel {
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public void onDestory() {
        //切换所有的Disposable对象
        mCompositeDisposable.clear();
    }
}
