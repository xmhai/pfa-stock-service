package com.lin.pfa.stock.util;

import org.springframework.stereotype.Service;

import com.lin.pfa.common.enums.Exchange;
import com.lin.pfa.stock.entity.StockEntity;

@Service
public class SiHtmlStockPriceRetriever extends HtmlStockPriceRetriever {
	SiHtmlStockPriceRetriever () {
		String u = "https://www.shareinvestor.com/fundamental/factsheet.html?counter=%s";
		String p = "Last \\((SGD|HKD)\\): \\<strong\\>(.*?)\\<\\/strong\\>"; 
		setUrl(u, p);
	}
	
	@Override
    public String getStockCode(StockEntity stock) {
		String code = stock.getCode(); 
        // Format stock code 
		if (stock.getExchange()==Exchange.HKEX) {
				code = code + ".HK";
		}
		
        return code;
    }
	
	public static void main (String[] args) {
		SiHtmlStockPriceRetriever r = new SiHtmlStockPriceRetriever();
		StockEntity stock = new StockEntity();
		stock.setCode("1810.HK");
		System.out.println(r.getPrice(stock));
		stock.setCode("D05.SI");
		System.out.println(r.getPrice(stock));
	}
}
