package com.example.lenovo.daochulvxing.base;

public class BasePresenter<V extends BaseView> {
   protected V mMvpView;
   public void bind(V view){
       mMvpView=view;
   }
}
