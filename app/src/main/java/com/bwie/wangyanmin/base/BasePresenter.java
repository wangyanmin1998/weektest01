package com.bwie.wangyanmin.base;

/*
 *@auther:王彦敏
 *@Date: 2019/12/31
 *@Time:9:07
 *@Description:
 * */
public abstract class BasePresenter<V> {
    protected V view;

    public BasePresenter(){
        initModel();
    }

    protected abstract void initModel();

    public void attach(V view){
        this.view=view;
    }

    public void detach(){
        view=null;
    }
}
