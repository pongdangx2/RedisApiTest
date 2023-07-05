package me.lkh.redisapitest.redis.vo;

public enum ResponseCode {
    SUCCESS(200, "SUCCESS"),
    INVALID_PARAMETER(990, "INVALID PARAMETER"),
    FAILED(999, "FAILED");

    private int statusCode;
    private String statusMessage;

    ResponseCode(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
