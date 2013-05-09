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
public class Stock {
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

    public Stock() {
    }

    public Stock(String name, float ask, float bid) {
        this.name = name;
        this.ask = ask;
        this.bid = bid;
    }
}
