package com.ywb.tuyue.ui.game;

import com.ywb.tuyue.entity.GameList;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;

/**
 * Created by JuZhongJoy on 2018/6/29.
 */

public class GameListPresenter {

    interface GameListSuccess extends BasePView {
        void getGameListSuccess(List<GameList> gameLists);
    }

    interface GameLists {
        void getGameList();
    }
}
