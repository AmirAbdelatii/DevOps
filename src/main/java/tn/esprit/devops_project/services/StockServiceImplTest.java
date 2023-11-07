package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void retrieveAllStockTest() {
        List<Stock> expectedStockList = new ArrayList<>();
        expectedStockList.add(new Stock(1L, "S1"));
        expectedStockList.add(new Stock(2L, "S2"));
        expectedStockList.add(new Stock(3L, "S3"));

        when(stockRepository.findAll()).thenReturn(expectedStockList);
        List<Stock> resultStockList = stockRepository.findAll();
        assertEquals(3, resultStockList.size(), "Size mismatch");

    }

    @Test
    public void testRetrieveStockWhenStockExists() {
        Stock stock1 = new Stock(99L, "S");

        StockRepository stockRepository = mock(StockRepository.class);

        when(stockRepository.findById(99L)).thenReturn(Optional.of(stock1));

        StockServiceImpl stockService = new StockServiceImpl(stockRepository);

        Stock resultStock = stockService.retrieveStock(99L);
        assertEquals(stock1, resultStock);
    }
}
