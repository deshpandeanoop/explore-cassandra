package com.explore.explorecassandra.beans.response;


public enum ResponseStatusMessage {
    SUCCESS("Request successful"), FAILED("Request failed"), RESOURCE_NOT_FOUND("Requested resource not found");

    private String val;

    ResponseStatusMessage(String val) {
        this.val = val;
    }
}
