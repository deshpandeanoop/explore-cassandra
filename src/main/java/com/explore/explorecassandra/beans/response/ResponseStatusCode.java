package com.explore.explorecassandra.beans.response;


import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseStatusCode {
    SUCCESS(12000), FAILED(13000), RESOURCE_NOT_FOUND(14000);

    private int val;

    ResponseStatusCode(int val) {
        this.val = val;
    }

    @JsonValue
    public int getVal() {
        return val;
    }
}