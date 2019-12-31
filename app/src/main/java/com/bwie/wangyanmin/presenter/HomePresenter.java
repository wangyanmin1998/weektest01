package com.bwie.wangyanmin.presenter;

import com.bwie.wangyanmin.base.BasePresenter;
import com.bwie.wangyanmin.contract.IHomeContract;
import com.bwie.wangyanmin.model.HomeModel;
import com.bwie.wangyanmin.model.bean.Bean;

/*
 *@auther:王彦敏
 *@Date: 2019/12/31
 *@Time:9:13
 *@Description:
 * */
public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        homeModel.getHomeData(new IHomeContract.IModel.IHomeCallback() {
            @Override
            public void onHomeSuccess(Bean bean) {
                view.onHomeSuccess(bean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);
            }
        });
    }
}
