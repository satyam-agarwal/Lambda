package com.example.lambda.model;

import com.amazonaws.services.lambda.runtime.Context;

import java.util.Map;

public class ApiGatewayProxyRequest {

    private String resource;
    private String path;
    private String httpMethod;
    private Map<String, String> headers;
    private Map<String, String> queryStringParameters;
    private Map<String, String> pathParameters;
    private Map<String, String> stageVariables;
    private Context context;
    private String body;
    private Boolean isBase64Encoded;


    public ApiGatewayProxyRequest() {
    }

    public ApiGatewayProxyRequest(String resource, String path, String httpMethod, Map<String, String> headers, Map<String, String> queryStringParameters, Map<String, String> pathParameters, Map<String, String> stageVariables, Context context, String body, Boolean isBase64Encoded) {
        this.resource = resource;
        this.path = path;
        this.httpMethod = httpMethod;
        this.headers = headers;
        this.queryStringParameters = queryStringParameters;
        this.pathParameters = pathParameters;
        this.stageVariables = stageVariables;
        this.context = context;
        this.body = body;
        this.isBase64Encoded = isBase64Encoded;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }

    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public Map<String, String> getStageVariables() {
        return stageVariables;
    }

    public void setStageVariables(Map<String, String> stageVariables) {
        this.stageVariables = stageVariables;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(Boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }

    @Override
    public String toString() {
        return "ApiGatewayProxyRequest{" +
                "resource='" + resource + '\'' +
                ", path='" + path + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", headers=" + headers +
                ", queryStringParameters=" + queryStringParameters +
                ", pathParameters=" + pathParameters +
                ", stageVariables=" + stageVariables +
                ", context=" + context +
                ", body='" + body + '\'' +
                ", isBase64Encoded=" + isBase64Encoded +
                '}';
    }
}