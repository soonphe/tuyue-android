package com.ywb.tuyue.ui.game;

import com.ywb.tuyue.entity.GameType;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;

/**
 * Created by JuZhongJoy on 2018/6/28.
 */

public interface GamePresenter {

    interface GamePresenterSuccess extends BasePView {
        void getGameTypeSuccess(List<GameType> gameType);

    }

    interface GameTypes {
        void getGameTypes();
    }
}
