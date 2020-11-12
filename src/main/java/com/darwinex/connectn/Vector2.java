package com.darwinex.connectn;

public class Vector2 {

    private int horizontal;
    private int vertical;
    private Chip chip;

    public Vector2(final int horizontal, final int vertical, final Chip chip) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.chip = chip;
    }

    public Vector2() {
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(final int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(final int vertical) {
        this.vertical = vertical;
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(final Chip chip) {
        this.chip = chip;
    }
}
