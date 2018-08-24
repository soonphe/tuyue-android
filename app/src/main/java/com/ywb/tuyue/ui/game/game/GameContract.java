package com.ywb.tuyue.ui.game.game;

import com.ywb.tuyue.entity.TAdvert;
import com.ywb.tuyue.entity.TGame;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;


public class GameContract {
    interface View extends BasePView {
        void getAdvertListSuccess(List<TAdvert> list );
        void getGameListSuccess( List<TGame> list);
    }

    interface  Presenter {
        void getAdvertList( );
        void getGameList( );
    }
}
