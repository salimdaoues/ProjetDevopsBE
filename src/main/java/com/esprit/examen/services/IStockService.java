package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.entitiesdto.StockDTO;

public interface IStockService {

	List<Stock> retrieveAllStocks();

	Stock addStock(Stock s);

	void deleteStock(Long id);

	Stock updateStock(Stock u);

	Stock retrieveStock(Long id);

	String retrieveStatusStock();

	Stock mapping(StockDTO st);
}
