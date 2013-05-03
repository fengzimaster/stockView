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
import java.util.ArrayList;
import java.util.List;

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
    public List<stock> getStock(@PathParam("stockName")String stockName) {
        List<stock> stocks = new ArrayList<stock>();

        String stockInformation = getStockInformation(stockName);
        String[] stockResponse = stockInformation.split(",");

        if (stockInformation == "" && stockResponse.length != 3) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        } else {
            stock stockObject = new stock();
            stockObject.setName(stockResponse[0]);
            stockObject.setAsk(Float.parseFloat(stockResponse[1]));
            stockObject.setBid(Float.parseFloat(stockResponse[2]));
            stocks.add(stockObject);
            return stocks;
        }
    }

    private String getStockInformation(String stockName) {            //get stock Information from yahoo API
        String outputMessage = "";
        String information = "";
        try {
            InputStream message;
            URL stockApiURL = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=" + stockName + "&f=nab");
            HttpURLConnection connection = (HttpURLConnection) stockApiURL.openConnection();
            connection.connect();
            message = connection.getInputStream();
            BufferedReader requestMessage = new BufferedReader(new InputStreamReader(message));

            while ((outputMessage = requestMessage.readLine()) != null) {
                information += outputMessage;
            }
            //System.out.println(information);

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return information;
        }

    }
}