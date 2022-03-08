package com.example.demo.common;

public enum Config {
    IMAGE_WIDTH(100),
    IMAGE_HEIGHT(100),
    CHANNELS(2);

    private final int value;

    Config(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
