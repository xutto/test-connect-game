package com.darwinex.connectn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum LineType {

    DIAGONAL_UL,
    DIAGONAL_UR,
    DIAGONAL_DL,
    DIAGONAL_DR,
    VERTICAL_U,
    VERTICAL_D,
    HORIZONTAL_L,
    HORIZONTAL_R;

    public Vector2Game checkWinInlineByType(final int timesToWin, final List<Vector2> gameVectorList, final Vector2 vector2Root) {

        final ArrayList<Vector2> vectorsWin = new ArrayList<>();
        vectorsWin.add(vector2Root);

        gameVectorList.forEach(vector2 -> {

            for (int i = 1; i < timesToWin; i++) {

                final Vector2 vectorNext = getVector2Next(vector2Root, i);
                if (vector2.equals(vectorNext)) {
                    vectorsWin.add(vector2);
                }
            }

        });

//        gameVectorList.stream()
//            .filter(vector2 -> vector2.getChip() == vector2Root.getChip())
//            .forEach(vector2 -> {
//                for (int i = 1; i < timesToWin; i++) {
//                    final Vector2 vectorNext = getVector2Next(vector2, i);
//                    if (gameVectorList.contains(vectorNext)) {
//                        vectorsWin.add(vectorNext);
//                    }
//                }
//            });

        Vector2Game vector2Game = new Vector2Game();
        if (vectorsWin.size() == timesToWin) {
            vector2Game.setResultGame(true);
            vector2Game.setVector2WinList(vectorsWin.stream().distinct().collect(Collectors.toList()));
        }

        return vector2Game;
    }

    //final Vector2 vectorNext = getVector2Next(vector2, i);
    private Vector2 getVector2Next(final Vector2 vector2, final int i/* nextPosition*/) {
        final Vector2 vector2Next;
        switch (this) {

            case DIAGONAL_UL:
                vector2Next = new Vector2(vector2.getHorizontal() - i, vector2.getVertical() + i, vector2.getChip());
                break;
            case DIAGONAL_UR:
                vector2Next = new Vector2(vector2.getHorizontal() + i, vector2.getVertical() + i, vector2.getChip());
                break;
            case DIAGONAL_DL:
                vector2Next = new Vector2(vector2.getHorizontal() - i, vector2.getVertical() - i, vector2.getChip());
                break;
            case DIAGONAL_DR:
                vector2Next = new Vector2(vector2.getHorizontal() + i, vector2.getVertical() - i, vector2.getChip());
                break;
            case VERTICAL_U:
                vector2Next = new Vector2(vector2.getHorizontal(), vector2.getVertical() + i, vector2.getChip());
                break;
            case VERTICAL_D:
                vector2Next = new Vector2(vector2.getHorizontal(), vector2.getVertical() - i, vector2.getChip());
                break;
            case HORIZONTAL_L:
                vector2Next = new Vector2(vector2.getHorizontal() - i, vector2.getVertical(), vector2.getChip());
                break;
            case HORIZONTAL_R:
                vector2Next = new Vector2(vector2.getHorizontal() + i, vector2.getVertical(), vector2.getChip());
                break;
            default:
                vector2Next = null;
                break;

        }

        return vector2Next;
    }
}
