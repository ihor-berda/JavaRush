package com.javarush.test.level40.lesson04.task02;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Опять POST, а не GET
Исправь ошибки в методе sendPost, чтобы он отправлял POST-запрос с переданными параметрами.
Реализация должна использовать библиотеки Apache HttpClient версии 4.5.1 и Apache HttpCore 4.4.3.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("http://requestb.in/1h4qhvv1", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        request.addHeader("User-Agent", "Mozilla/5.0");

        List<NameValuePair> valuePairs = new ArrayList<>();
        String[] array = urlParameters.split("&");
        for (String string : array) {
            valuePairs.add(new BasicNameValuePair(string.substring(0, string.indexOf("=")), string.substring(string.indexOf("=") + 1)));
        }

        request.setEntity(new UrlEncodedFormEntity(valuePairs));

        HttpResponse response = client.execute(request);

        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }
}
