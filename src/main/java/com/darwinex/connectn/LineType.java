package com.darwinex.connectn;

import java.util.ArrayList;
import java.util.List;

public enum LineType {

    DIAGONAL_UL,
    DIAGONAL_UR,
    DIAGONAL_DL,
    DIAGONAL_DR,
    VERTICAL_U,
    VERTICAL_D,
    HORIZONTAL_L,
    HORIZONTAL_R;

    public boolean checkWinInlineByType(final int timesToWin, final List<Vector2> gameVectorList, final Chip chip) {

        final ArrayList<Vector2> vectorsWin = new ArrayList<>();
        switch (this) {
            case DIAGONAL_UL:

                gameVectorList.stream()
                    .filter(vector2 -> vector2.getChip() == chip)
                    .forEach(vector2 -> {
                        vectorsWin.add(vector2);
                        for (int i = 0; i < timesToWin; i++) {
                            final Vector2 vectorNext = new Vector2(vector2.getHorizontal() - i, vector2.getVertical() + i, vector2.getChip());
                            if (gameVectorList.contains(vectorNext)) {
                                vectorsWin.add(vectorNext);
                            }
                        }
                    });

                return vectorsWin.size() == timesToWin;

            case DIAGONAL_UR:

                gameVectorList.stream()
                    .filter(vector2 -> vector2.getChip() == chip)
                    .forEach(vector2 -> {
                        vectorsWin.add(vector2);
                        for (int i = 0; i < timesToWin; i++) {
                            final Vector2 vectorNext = new Vector2(vector2.getHorizontal() + i, vector2.getVertical() + i, vector2.getChip());
                            if (gameVectorList.contains(vectorNext)) {
                                vectorsWin.add(vectorNext);
                            }
                        }
                    });

                return vectorsWin.size() == timesToWin;

            case DIAGONAL_DL:
                gameVectorList.stream()
                    .filter(vector2 -> vector2.getChip() == chip)
                    .forEach(vector2 -> {
                        vectorsWin.add(vector2);
                        for (int i = 0; i < timesToWin; i++) {
                            final Vector2 vectorNext = new Vector2(vector2.getHorizontal() - i, vector2.getVertical() - i, vector2.getChip());
                            if (gameVectorList.contains(vectorNext)) {
                                vectorsWin.add(vectorNext);
                            }
                        }
                    });

                return vectorsWin.size() == timesToWin;
            case DIAGONAL_DR:
                gameVectorList.stream()
                    .filter(vector2 -> vector2.getChip() == chip)
                    .forEach(vector2 -> {
                        vectorsWin.add(vector2);
                        for (int i = 0; i < timesToWin; i++) {
                            final Vector2 vectorNext = new Vector2(vector2.getHorizontal() + i, vector2.getVertical() - i, vector2.getChip());
                            if (gameVectorList.contains(vectorNext)) {
                                vectorsWin.add(vectorNext);
                            }
                        }
                    });

                return vectorsWin.size() == timesToWin;
            case VERTICAL_U:
                gameVectorList.stream()
                    .filter(vector2 -> vector2.getChip() == chip) //todo arreglar el algoritm de comprobacion de trazada y agregar solo las fichas que lo componen
                    .forEach(vector2 -> {
                        vectorsWin.add(vector2);
                        for (int i = 0; i < timesToWin; i++) {
                            final Vector2 vectorNext = new Vector2(vector2.getHorizontal(), vector2.getVertical() + i, vector2.getChip());
                            if (gameVectorList.contains(vectorNext)) {
                                vectorsWin.add(vectorNext);
                            }
                        }
                    });

                return vectorsWin.size() == timesToWin;

            case VERTICAL_D:

                gameVectorList.stream()
                    .filter(vector2 -> vector2.getChip() == chip)
                    .forEach(vector2 -> {
                        vectorsWin.add(vector2);
                        for (int i = 0; i < timesToWin; i++) {
                            final Vector2 vectorNext = new Vector2(vector2.getHorizontal(), vector2.getVertical() - i, vector2.getChip());
                            if (gameVectorList.contains(vectorNext)) {
                                vectorsWin.add(vectorNext);
                            }
                        }
                    });

                return vectorsWin.size() == timesToWin;
            case HORIZONTAL_L:

                gameVectorList.stream()
                    .filter(vector2 -> vector2.getChip() == chip)
                    .forEach(vector2 -> {
                        vectorsWin.add(vector2);
                        for (int i = 0; i < timesToWin; i++) {
                            final Vector2 vectorNext = new Vector2(vector2.getHorizontal() - i, vector2.getVertical(), vector2.getChip());
                            if (gameVectorList.contains(vectorNext)) {
                                vectorsWin.add(vectorNext);
                            }
                        }
                    });

                return vectorsWin.size() == timesToWin;
            case HORIZONTAL_R:
                gameVectorList.stream()
                    .filter(vector2 -> vector2.getChip() == chip)
                    .forEach(vector2 -> {
                        vectorsWin.add(vector2);
                        for (int i = 0; i < timesToWin; i++) {
                            final Vector2 vectorNext = new Vector2(vector2.getHorizontal() + i, vector2.getVertical(), vector2.getChip());
                            if (gameVectorList.contains(vectorNext)) {
                                vectorsWin.add(vectorNext);
                            }
                        }
                    });

                return vectorsWin.size() == timesToWin;
        }

        return false;
    }
}
