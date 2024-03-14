package com.n11.userservice.util.enums;

public enum Score {
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);

    private final int value;

    Score(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
