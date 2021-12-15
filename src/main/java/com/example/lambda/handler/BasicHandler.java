package com.example.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Get;

import java.io.IOException;
import java.util.Map;

// Simple Handler
public class BasicHandler implements RequestHandler<Map<String, String>, String> {

    private static final String url = "https://search-my-test-wtkt7u7aw42yfx53gle5673mxq.us-east-2.es.amazonaws.com/";
    private static final JestClient jestClient;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
    public String handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();

        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass());

        //attempting jestClient get request
        try {
            String employees = jestClient.execute(new Get.Builder("employees", "1").build()).getJsonString();
            logger.log("employees: " + employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log("jestClient -> " + jestClient.toString());
        return "200 OK";
    }
}