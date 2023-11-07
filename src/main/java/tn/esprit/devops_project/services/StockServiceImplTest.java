package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    public Stock stock;
    @Mock
    StockRepository stockRepository= mock(StockRepository.class);

    @InjectMocks
    public StockServiceImpl stockServiceImpl;

    Stock stock1 = new Stock(99L, "S");

    List<Stock> stocklist = new ArrayList<Stock>() {

        {
            add(new Stock(1L, "S1"));
            add(new Stock(2L, "S2"));
            add(new Stock(3L, "S3"));

        }};

    
    @Test
    void retrieveAllStocktest() {
        when(stockServiceImpl.retrieveAllStock()).thenReturn(stocklist);
        List<Stock> stockList1 = stockServiceImpl.retrieveAllStock();
        Assertions.assertEquals(3, stockList1.size());
        System.out.println("***** retrieveAllStocktest Mockito : success *****");
    }

    @Test
    public void testRetrieveStock_WhenStockExists() {
        Stock stock1 = new Stock(99L, "S");

        StockRepository stockRepository = mock(StockRepository.class);

        when(stockRepository.findById(99L)).thenReturn(Optional.of(stock1));

        StockServiceImpl stockService = new StockServiceImpl(stockRepository);

        Stock resultStock = stockService.retrieveStock(99L);
        assertEquals(stock1, resultStock);
    }
}