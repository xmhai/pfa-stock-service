package com.lin.pfa.stock.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.pfa.common.enums.Exchange;
import com.lin.pfa.stock.entity.StockEntity;

@Service
public class StockPriceRetrieverChain {
	@Autowired
	private AlphavantageRetriever alphavantageRetriever;
	@Autowired
	private SiHtmlStockPriceRetriever siHtmlStockPriceRetriever;
	
	List<StockPriceRetriever> retrievers = new ArrayList<StockPriceRetriever>();;
	
	@PostConstruct
	public StockPriceRetrieverChain build() {
		retrievers = new ArrayList<StockPriceRetriever>();;
		retrievers.add(alphavantageRetriever);
		retrievers.add(siHtmlStockPriceRetriever);
		return this;
	}
	
	public double getPrice(StockEntity stock) {
		if (stock.getExchange()==Exchange.HKEX || stock.getExchange()==Exchange.SGX) {
			return siHtmlStockPriceRetriever.getPrice(stock);
		} else {
			return alphavantageRetriever.getPrice(stock);
		}

		/*
		for (StockPriceRetriever retriever : retrievers) {
			System.out.println("Stock:" + stock.getCode());
			double price = retriever.getPrice(stock);
			if (price > 0) {
				System.out.println("Last Price:" + price);
				return price;
			}
		}
		return 0;
		*/
	}
}
