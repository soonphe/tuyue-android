package com.ywb.tuyue.ui.game.game;

import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.entity.TGameType;
import com.ywb.tuyue.base.mvp.BasePView;

import java.util.List;


public class GameContract {
    interface View extends BasePView {
        void getAdvertListSuccess(List<TAdvert> list );
        void getTypeListSuccess(List<TGameType> list );
        void getGameListSuccess( List<TGame> list);
    }

    interface  Presenter {
        void getAdvertList( );
        void getTypeList( );
        void getGameList( int gameid);
    }
}
