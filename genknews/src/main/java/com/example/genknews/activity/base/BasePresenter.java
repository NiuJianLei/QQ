package com.example.genknews.activity.base;

import java.util.ArrayList;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public abstract class BasePresenter<V extends BaseMvpView> {
    protected V mMvpView;
    protected ArrayList<BaseModel> mModels = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(V view) {
        this.mMvpView = view;
    }

    public void onDestory() {
        //打断P层和V层的联系,
        mMvpView = null;
        //掐断网络请求
        if (mModels.size()>0){
            for (BaseModel model :mModels) {
                model.onDestory();
            }
            mModels.clear();
        }
    }
}
