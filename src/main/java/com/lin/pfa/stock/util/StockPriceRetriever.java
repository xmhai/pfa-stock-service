package com.lin.pfa.stock.util;

import com.lin.pfa.stock.entity.StockEntity;

public interface StockPriceRetriever {
	double getPrice(StockEntity stock);
}
