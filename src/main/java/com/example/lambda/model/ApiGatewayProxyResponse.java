package com.example.lambda.model;

import java.util.Map;

public class ApiGatewayProxyResponse {

    private int statusCode;
    private Map<String, String> headers;
    private String body;
    private boolean isBase64Encoded;


    public ApiGatewayProxyResponse() {
    }

    public ApiGatewayProxyResponse(int statusCode, Map<String, String> headers, String body, boolean isBase64Encoded) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
        this.isBase64Encoded = isBase64Encoded;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }

    @Override
    public String toString() {
        return "ApiGatewayProxyResponse{" +
                "statusCode=" + statusCode +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", isBase64Encoded=" + isBase64Encoded +
                '}';
    }
}
