package com.darwinex.connectn;

import java.util.List;

public class Vector2Game {

    private List<Vector2> vector2WinList;
    private boolean resultGame;

    public List<Vector2> getVector2WinList() {
        return vector2WinList;
    }

    public void setVector2WinList(final List<Vector2> vector2WinList) {
        this.vector2WinList = vector2WinList;
    }

    public boolean isResultGame() {
        return resultGame;
    }

    public void setResultGame(final boolean resultGame) {
        this.resultGame = resultGame;
    }
}
