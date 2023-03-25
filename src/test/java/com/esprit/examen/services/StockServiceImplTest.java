package com.esprit.examen.services;

import com.esprit.examen.entities.Stock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
 class StockServiceImplTest {
    @Autowired
    IStockService stockService;

    @Test
    void testAddStock() {
        Stock s = new Stock("test",5,98);
        Stock savedStock= stockService.addStock(s);
        assertSame(98, savedStock.getQteMin());
        assertSame( 5, savedStock.getQte());
        assertNotNull(savedStock.getLibelleStock());
        stockService.deleteStock(savedStock.getIdStock());
    }

    @Test
    void testUpdatestock() {

        Stock s = new Stock("test2",78,98);
        Stock s2= stockService.addStock(s);
        assertNotNull(s2.getIdStock());
        Stock stock= stockService.retrieveStock(s2.getIdStock());
        assertTrue(stock.getQteMin()>10);
        stockService.deleteStock(s2.getIdStock());
    }
    @Test
    void testDeleteStock() {
        Stock s = new Stock("test3",44,511);
        Stock savedStock= stockService.addStock(s);
        stockService.deleteStock(savedStock.getIdStock());
        assertNull(stockService.retrieveStock(savedStock.getIdStock()));
    }
    @Test
    void testretrieveallstocks() {
        int expected = stockService.retrieveAllStocks().size();
        Stock s = new Stock("test4",68,695);
        Stock savedStock= stockService.addStock(s);
        assertEquals(expected + 1, stockService.retrieveAllStocks().size());
        stockService.deleteStock(savedStock.getIdStock());
    }

}

