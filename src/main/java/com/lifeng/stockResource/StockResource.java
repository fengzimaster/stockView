package com.lifeng.stockResource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lifeng
 * Date: 4/25/13
 * Time: 11:53 AM
 * To change this template use File | Settings | File Templates.
 */
public interface StockResource {


    public List<stock> getStock(String stockName);
}
