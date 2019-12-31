package com.bwie.wangyanmin.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.wangyanmin.R;
import com.bwie.wangyanmin.model.bean.Bean;
import com.bwie.wangyanmin.util.NetUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2019/12/31
 *@Time:10:05
 *@Description:
 * */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private List<Bean.RankingBean> list;

    public MyAdapter(List<Bean.RankingBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Bean.RankingBean rankingBean = list.get(position);
        holder.pai.setText(rankingBean.getRank()+"");
        holder.tvRen.setText(rankingBean.getName());
        NetUtil.getInstance().getPhoto(rankingBean.getAvatar(),holder.imgTu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTagClickListner.onTagClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pai)
        TextView pai;
        @BindView(R.id.img_tu)
        ImageView imgTu;
        @BindView(R.id.tv_ren)
        TextView tvRen;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnTagClickListner(MyAdapter.onTagClickListner onTagClickListner) {
        this.onTagClickListner = onTagClickListner;
    }

    onTagClickListner onTagClickListner;
    public interface onTagClickListner{
        void onTagClick(int position);
    }


}
