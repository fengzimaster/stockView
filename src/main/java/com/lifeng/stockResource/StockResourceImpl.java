package com.lifeng.stockResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
 * Date: 4/25/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/stockView")
public class StockResourceImpl implements StockResource {

    @GET
    @Path("{stockName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Stock getStock(@PathParam("stockName")String stockName) {
        Stock stock=new Stock();
        String stockInformation = getStockInformation(stockName);
        String[] stockResponse = stockInformation.split(",");

        if (!(stockInformation .equals("")) && stockResponse.length == 3) {
            stock.setName(stockResponse[0]);
            stock.setAsk(Float.parseFloat(stockResponse[1]));
            stock.setBid(Float.parseFloat(stockResponse[2]));
        }
        return stock;
    }

    private String getStockInformation(String stockName) {            //get stock Information from yahoo API
        String outputMessage = "";
        HttpURLConnection connection ;
        InputStream messageInputstream;

        try {
            URL stockApiURL = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=" + stockName + "&f=nab");
            connection = (HttpURLConnection) stockApiURL.openConnection();
            connection.connect();
            messageInputstream = connection.getInputStream();
            BufferedReader requestMessage = new BufferedReader(new InputStreamReader(messageInputstream));
            outputMessage=requestMessage.readLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return outputMessage;
    }
}