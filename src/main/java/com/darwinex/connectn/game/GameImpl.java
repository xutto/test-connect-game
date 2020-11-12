package com.darwinex.connectn.game;

import com.darwinex.connectn.ChipPosition;
import com.darwinex.connectn.GameResult;
import com.darwinex.connectn.Chip;
import com.darwinex.connectn.LineType;
import com.darwinex.connectn.Vector2;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameImpl implements Game {


    private final int rows;
    private final int columns;
    private final int numberOfConnectedByWin;
    private Vector2 vector2Reference;
    private Chip chip;
    private ArrayList<Vector2> vectors;


    /**
     * @param rows    the number of rows in the board
     * @param columns the number of columns in the board
     * @param n       the number of connected chips required to win the game
     */
    public GameImpl(final int rows, final int columns, final int n) {
        this.rows = rows;
        this.columns = columns;
        this.numberOfConnectedByWin = n;
        this.vector2Reference = new Vector2();
        this.vectors = new ArrayList<>();
    }

    @Override
    public void putChip(final Chip chip, final int column) {
        this.chip = chip;
        vector2Reference.setHorizontal(column);
        vector2Reference.setChip(chip);
        vectors.stream().filter(vector2 -> vector2.getHorizontal() == vector2Reference.getHorizontal()).findFirst()
            .ifPresent(vector21 -> vector2Reference.setVertical(vector2Reference.getVertical() + 1));

        vectors.add(cloneVector(vector2Reference));
    }


    @Override
    public GameResult getGameResult() {
//        ArrayList<ChipPosition> chipPositions = new ArrayList<>();
        ChipPosition[] chipPositionsArray = calculateConcurrence()
            .stream()
            .map(vector2 -> new ChipPosition(vector2.getHorizontal(), vector2.getHorizontal())).toArray(ChipPosition[]::new);
        if (chipPositionsArray.length > 0) {
            return new GameResult(chip, chipPositionsArray);
        } else {
            return null;
        }
    }

    private List<Vector2> calculateConcurrence() {
//
//        int i = 1;
//        for (Vector2 vector2 : vectors) {
//            final Vector2 concurrenceVector = new Vector2(vector2.getHorizontal() + i, vector2.getVertical(), vector2.getChip());
//            if (vectors.contains(concurrenceVector)) {
//                calculatePlayerWin(concurrenceVector);
//            } else {
//                calculateConcurrence();
//            }
//        }
        final List<Vector2> collect = vectors.stream().filter(this::calculatePlayerWin).collect(Collectors.toList());
        return collect;

    }

    private boolean calculatePlayerWin(final Vector2 vectorRoot) {
        final ArrayList<Vector2> vectorsPossibleList = concurrenceList(vectorRoot);

        final Vector2 vectorPosibleFileteredAndMatched = vectorsPossibleList.stream().filter(
            vectorPossible -> vectors.stream().anyMatch(vector2 -> matchVectors(vectorPossible, vector2))
        ).findFirst().orElse(null);

        if (vectorPosibleFileteredAndMatched != null) {
            return findWin(vectorRoot, vectorPosibleFileteredAndMatched);
        } else {
            return false;
        }

//        final Vector2 vector2PosibleMatchedInListVector = vectors.stream().map(
//            vector2 -> vectorsPossibleList.stream().map(
//                vector2Posible -> matchVectors(vector2Posible, vector2)
//            ).findFirst().get()
//        ).findFirst().get();

//        return vectors.stream()
//            //dice de las posiciones de las fichas hay coincidencia con las posibles posiciones para iniciar la comprobacion de si son en linea
//            .map(vector2 -> getMatchVector2PosibleToActual(vectorRoot))
//            .anyMatch(vector2Posible -> findWin(vector2Posible, vectorRoot));
    }

//    private Vector2 getMatchVector2PosibleToActual(final Vector2 vectorRoot) {
//
//        final Vector2 vectorMatched = vectors.stream()
//            .findFirst(
//                vector2 -> vectorsPossible.stream().map(vector2Posible -> {
//                    return matchVectors(vector2Posible, vector2);
//                })
//            ).get();
//        return null;
//    }

    private boolean matchVectors(final Vector2 vector2Posible, final Vector2 vector2) {
        return vector2Posible.getHorizontal() == vector2.getHorizontal() &&
            vector2Posible.getVertical() == vector2.getVertical() &&
            vector2Posible.getChip() == vector2.getChip();
    }

    private boolean findWin(final Vector2 vectorRoot, final Vector2 vector2PosibleMatched) {
        final LineType lineType = checkLineType(vectorRoot, vector2PosibleMatched);
        return lineType.checkWinInlineByType(numberOfConnectedByWin, vectors, vectorRoot.getChip());
    }

    private LineType checkLineType(final Vector2 prePosition, final Vector2 postPosition) {
        final LineType lineType;
        if (postPosition.getHorizontal() > prePosition.getHorizontal() && postPosition.getVertical() == prePosition.getVertical()) {
            lineType = LineType.HORIZONTAL_R; //x+1
        } else if (postPosition.getHorizontal() > prePosition.getHorizontal() && postPosition.getVertical() > prePosition.getVertical()) {
            lineType = LineType.DIAGONAL_UR; //x+1 , y+1
        } else if (postPosition.getHorizontal() == prePosition.getHorizontal() && postPosition.getVertical() > prePosition.getVertical()) {
            lineType = LineType.VERTICAL_U; //y+1
        } else if (postPosition.getHorizontal() > prePosition.getHorizontal() && postPosition.getVertical() < prePosition.getVertical()) {
            lineType = LineType.DIAGONAL_DR; //x+1 , y-1
        } else if (postPosition.getHorizontal() < prePosition.getHorizontal() && postPosition.getVertical() == prePosition.getVertical()) {
            lineType = LineType.HORIZONTAL_L; //x-1
        } else if (postPosition.getHorizontal() < prePosition.getHorizontal() && postPosition.getVertical() > prePosition.getVertical()) {
            lineType = LineType.DIAGONAL_UL; // x-1 , y+1
        } else if (postPosition.getHorizontal() == prePosition.getHorizontal() && postPosition.getVertical() < prePosition.getVertical()) {
            lineType = LineType.VERTICAL_D; // y-1
        } else if (postPosition.getHorizontal() < prePosition.getHorizontal() && postPosition.getVertical() < prePosition.getVertical()) {
            lineType = LineType.DIAGONAL_DL; // x-1, y-1
        } else {
            lineType = null;
        }

        return lineType;
    }

    private static Vector2 cloneVector(final Vector2 vector2Reference) {
        Vector2 vector2Clone = new Vector2();
        vector2Clone.setVertical(vector2Reference.getVertical());
        vector2Clone.setHorizontal(vector2Reference.getHorizontal());
        vector2Clone.setChip(vector2Reference.getChip());
        return vector2Clone;
    }


    private ArrayList<Vector2> concurrenceList(final Vector2 concurrenceVector) {
        final ArrayList<Vector2> concurrenceList = new ArrayList<>();

        concurrenceList.add(new Vector2(concurrenceVector.getHorizontal() + 1, concurrenceVector.getVertical(), concurrenceVector.getChip()));
        concurrenceList.add(new Vector2(concurrenceVector.getHorizontal() + 1, concurrenceVector.getVertical() + 1, concurrenceVector.getChip()));
        concurrenceList.add(new Vector2(concurrenceVector.getHorizontal() + 1, concurrenceVector.getVertical() - 1, concurrenceVector.getChip()));
        concurrenceList.add(new Vector2(concurrenceVector.getHorizontal(), concurrenceVector.getVertical() + 1, concurrenceVector.getChip()));

        concurrenceList.add(new Vector2(concurrenceVector.getHorizontal() - 1, concurrenceVector.getVertical(), concurrenceVector.getChip()));
        concurrenceList.add(new Vector2(concurrenceVector.getHorizontal() - 1, concurrenceVector.getVertical() + 1, concurrenceVector.getChip()));
        concurrenceList.add(new Vector2(concurrenceVector.getHorizontal() - 1, concurrenceVector.getVertical() - 1, concurrenceVector.getChip()));
        concurrenceList.add(new Vector2(concurrenceVector.getHorizontal(), concurrenceVector.getVertical() - 1, concurrenceVector.getChip()));

        return concurrenceList.stream().filter(vector2 -> vector2.getVertical() >= 0 && vector2.getHorizontal() >= 0)
            .collect(Collectors.toCollection(ArrayList::new));
    }

}
