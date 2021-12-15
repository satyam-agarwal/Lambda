package com.example.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.lambda.model.ApiGatewayProxyRequest;
import com.example.lambda.model.ApiGatewayProxyResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Get;

import java.io.IOException;

// Handler which accepts API Gateway request and Response
public class ApiGatewayHandler implements RequestHandler<ApiGatewayProxyRequest, ApiGatewayProxyResponse> {


    //Common Static varibles
    private static final String url = "https://search-my-test-wtkt7u7aw42yfx53gle5673mxq.us-east-2.es.amazonaws.com/";
    private static final JestClient jestClient;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // initializing jest when class is initialized
    static {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder(url)
                        .multiThreaded(true)
                        .defaultMaxTotalConnectionPerRoute(2)
                        .maxTotalConnection(10)
                        .build());
        jestClient =  factory.getObject();
    }

    @Override
    public ApiGatewayProxyResponse handleRequest(ApiGatewayProxyRequest apiGatewayProxyRequest, Context context) {
        LambdaLogger logger = context.getLogger();

        // request body
        logger.log("got request payload: " + apiGatewayProxyRequest.toString());
        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));

        String employeesJson = null;
        // attempting jestClient get request
        try {

            employeesJson = jestClient.execute(new Get.Builder("employees", "1").build()).getJsonString();
            logger.log("got employees Json: " + employeesJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // preparing response
        ApiGatewayProxyResponse apiGatewayProxyResponse = new ApiGatewayProxyResponse();
        apiGatewayProxyResponse.setStatusCode(200);
        apiGatewayProxyResponse.setBody("response : " + employeesJson);
        return apiGatewayProxyResponse;
    }
}