package com.ywb.tuyue.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.GameList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JuZhongJoy on 2018/6/28.
 * 游戏列表适配器
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.HomeViewHoder> {

    Context context;
    GameItem recOnitem;
    List<GameList> list = new ArrayList<>();
    private HashMap<String, Boolean> states = new HashMap<String, Boolean>();//记录所有radiobutton被点击的状态

    public void setdata(List<GameList> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemListener(GameItem recOnitem) {
        this.recOnitem = recOnitem;

    }

    public GameListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeViewHoder hoder = new HomeViewHoder(LayoutInflater.from(context).inflate(R.layout.framgent_game_item, parent, false));
        return hoder;
    }

    @Override
    public void onBindViewHolder(final HomeViewHoder holder, final int position) {

//        holder.iv_gamePic.setImageResource();
//        holder.tv_gameName.setText();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recOnitem.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class HomeViewHoder extends RecyclerView.ViewHolder {

        ImageView iv_gamePic;
        TextView tv_gameName;


        public HomeViewHoder(View view) {
            super(view);
            iv_gamePic = view.findViewById(R.id.iv_gamePic);
            tv_gameName = view.findViewById(R.id.tv_gameName);
        }
    }

    public interface GameItem {
        void onItemClick(int posotion);
    }
}
