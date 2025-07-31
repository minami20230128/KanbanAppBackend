package com.example.demo.entity;

public enum Status {
    TODO(0),
    IN_PROGRESS(1),
    DONE(2);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status fromCode(int code) {
        for (Status s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("Unknown Status code: " + code);
    }
}
