package dev.joshjh.givenergyjava;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import org.apache.hc.client5.http.fluent.*;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.message.BasicHeader;

public abstract class GivEnergyAPI {
    protected final static String apiKeyFile = "givEnergyAPIKey.txt";
    
    public final static String apiKey = loadFileValue(apiKeyFile);
    public static final String baseURL = "https://api.givenergy.cloud/v1/";
    public static final Header headerContentType = new BasicHeader("Content-Type", "application/json");
    public static final Header headerAccept = new BasicHeader("Accept", "application/json");
    public static final Header headerAuthorization = new BasicHeader("Authorization", String.format("Bearer %s", apiKey));
    protected static URI baseUri = URI.create(baseURL);

    protected static String loadFileValue(String apiKeyFile) {
    try (FileReader fn = new FileReader(apiKeyFile); BufferedReader br = new BufferedReader(fn)) {
        String readline = br.readLine();
        System.out.println(String.format("Read API Key: %s", readline));
        return readline;
        
    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException(String.format("APIKey file not found %s", GivEnergyAPI.apiKeyFile));
    }
    }

    protected static Request createGetRequest(URI uri) {
        Method method = Method.GET;
        Request request = Request.create(method, uri).addHeader(GivEnergyAPI.headerAuthorization).addHeader(GivEnergyAPI.headerAccept).addHeader(GivEnergyAPI.headerContentType);
        return request;

    }
    protected static Request createPostRequest(URI uri) {
        Method method = Method.POST;
        Request request = Request.create(method, uri).addHeader(GivEnergyAPI.headerAuthorization).addHeader(GivEnergyAPI.headerAccept).addHeader(GivEnergyAPI.headerContentType);
        return request;
    }
}

