package com.company.async;

/**
 * Created by John on 2017/7/13.
 */
public enum EventType {
    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3),
    FOLLOW(4),
    UNFOLLOW(5);

    private int value;
    EventType(int value) { this.value = value; }
    public int getValue() { return value; }

}
