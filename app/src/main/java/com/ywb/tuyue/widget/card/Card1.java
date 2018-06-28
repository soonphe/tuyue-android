package com.ywb.tuyue.widget.card;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ywb.tuyue.R;
import com.ywb.tuyue.utils.GlideUtils;
/**
 * Description:
 * Created by wcystart on 2018/6/21.
 */

public class Card1 extends CustomerCard {
   private ImageView mBgCard; //图片
    TextView mEnName; //英文名
    TextView mZnName; //中文名
    private Context mContext;

    public Card1(@NonNull Context context) {

        this(context, null);
    }

    public Card1(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Card1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_1, this, true);
          mBgCard=view.findViewById(R.id.card_bg);
          mEnName=view.findViewById(R.id.card_en_name);
          mZnName=view.findViewById(R.id.card_zn_name);

    }
    @Override
    void setCardBg(String url) {
        GlideUtils.loadImageView(mContext,url,mBgCard);
    }

    @Override
    void setCardBg(int resId) {
      mBgCard.setImageResource(resId);
    }

    @Override
    void setCardIcon(int iconId) {

    }

    @Override
    void setENText(String ENText) {
      mEnName.setText(ENText);
    }

    @Override
    void setZNText(String ZNText) {
        mZnName.setText(ZNText);
    }
}
