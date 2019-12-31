package com.bwie.wangyanmin.contract;

import com.bwie.wangyanmin.model.bean.Bean;

/*
 *@auther:王彦敏
 *@Date: 2019/12/31
 *@Time:9:05
 *@Description:
 * */
public interface IHomeContract {
    interface IView{
        void onHomeSuccess(Bean bean);
        void onHomeFailure(Throwable throwable);
    }

    interface IPresenter{
        void getHomeData();
    }

    interface IModel{
        void getHomeData(IHomeCallback iHomeCallback);
        interface IHomeCallback{
            void onHomeSuccess(Bean bean);
            void onHomeFailure(Throwable throwable);
        }
    }

}
