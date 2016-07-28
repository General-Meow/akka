package com.paulhoang.messages;

import java.io.Serializable;

/**
 * Created by paul on 25/07/2016.
 */
public class GetRequest implements Serializable {

    private final String key;

    public GetRequest(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
