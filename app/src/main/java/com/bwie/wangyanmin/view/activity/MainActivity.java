package com.bwie.wangyanmin.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.wangyanmin.R;
import com.bwie.wangyanmin.base.BaseActivity;
import com.bwie.wangyanmin.contract.IHomeContract;
import com.bwie.wangyanmin.model.bean.Bean;
import com.bwie.wangyanmin.presenter.HomePresenter;
import com.bwie.wangyanmin.util.NetUtil;
import com.bwie.wangyanmin.view.adapter.MyAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    @BindView(R.id.tv_click)
    TextView tvClick;
    @BindView(R.id.recy)
    RecyclerView recy;

    @Override
    protected void initData() {
        if (NetUtil.getNet(this)){
            mPresenter.getHomeData();
        }else {
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
        }
        
        
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    public void onHomeSuccess(Bean bean) {
        List<Bean.RankingBean> ranking = bean.getRanking();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recy.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(ranking);
        recy.setAdapter(myAdapter);
        myAdapter.setOnTagClickListner(new MyAdapter.onTagClickListner() {
            @Override
            public void onTagClick(int position) {
                Toast.makeText(MainActivity.this, "名字是:"+ranking.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_click)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this,SecondeActivity.class);
        startActivity(intent);
    }
}
