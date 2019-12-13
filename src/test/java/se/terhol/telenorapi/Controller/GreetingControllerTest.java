package se.terhol.telenorapi.Controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GreetingControllerTest {
    private String path = "http://localhost:5000/greeting";

    @Test
    void correctPersonalAccount() throws IOException {
        String query = "?account=personal&id=";
        String randomNumeric = RandomStringUtils.randomNumeric(2, 10);
        HttpUriRequest request = new HttpGet(path + query + randomNumeric);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals(String.format("Hi, userID %d", Integer.valueOf(randomNumeric)), EntityUtils.toString(httpResponse.getEntity()));
    }

    @Test
    void correctBusinessAccountBig() throws Exception {
        String query = "?account=business&type=big";
        HttpUriRequest request = new HttpGet(path + query);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals("Welcome business user!", EntityUtils.toString(httpResponse.getEntity()));
    }

    @Test
    void correctBusinessAccountSmall() throws IOException {
        String query = "?account=business&type=small";
        HttpUriRequest request = new HttpGet(path + query);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(400, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    void correctConvertStringToAccount() throws Exception {
        String query = "?account=PERsonal&id=";
        String randomNumeric = RandomStringUtils.randomNumeric(1, 10);
        HttpUriRequest request = new HttpGet(path + query + randomNumeric);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals(String.format("Hi, userID %d", Integer.valueOf(randomNumeric)), EntityUtils.toString(httpResponse.getEntity()));
    }

    @Test
    void correctConvertBusinessType() throws Exception {
        String query = "?account=business&type=BiG";
        HttpUriRequest request = new HttpGet(path + query);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals("Welcome business user!", EntityUtils.toString(httpResponse.getEntity()));
    }


    @Test
    void invalidId() throws Exception {
        String query = "?account=personal&id=";
        String randomNumeric = RandomStringUtils.randomNumeric(1, 10);
        HttpUriRequest request = new HttpGet(path + query + "-" + randomNumeric);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(400, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    void invalidOrMissingBusinessType() throws Exception {
        String query = "?account=business";
        HttpUriRequest request = new HttpGet(path + query);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(400, httpResponse.getStatusLine().getStatusCode());
    }


}