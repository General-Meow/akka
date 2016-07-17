package com.paulhoang.messages;

/**
 * Created by paul on 18/07/2016.
 */
public class SetRequest {

    private String key;
    private Object value;

    public SetRequest(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
