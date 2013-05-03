package com.lifeng.stockResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: lifeng
 * Date: 4/24/13
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class stock {
    private String name;
    private float ask = 0.0f;
    private float bid = 0.0f;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public stock() {
    }

    public stock(String name, float ask, float bid) {
        this.name = name;
        this.ask = ask;
        this.bid = bid;
    }

    public static void main(String[] args) {
        try {
            InputStream message;
            URL stockApiURL = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=600036.SS&f=nab");
            HttpURLConnection connection = (HttpURLConnection) stockApiURL.openConnection();
            connection.connect();
            message = connection.getInputStream();
            BufferedReader requestMessage = new BufferedReader(new InputStreamReader(message));
            String outputMessage = "";
            String information = "";
            while ((outputMessage = requestMessage.readLine()) != null) {
                information += outputMessage;
            }
            System.out.println(information);

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
