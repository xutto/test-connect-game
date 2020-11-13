package com.darwinex.connectn;

import java.util.Objects;

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


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vector2)) {
            return false;
        }
        Vector2 vector2 = (Vector2) o;
        return horizontal == vector2.horizontal &&
            vertical == vector2.vertical &&
            chip == vector2.chip;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical, chip);
    }
}
