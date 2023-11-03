package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.StockServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StockServiceImplTest {
    @Autowired
    public StockServiceImpl stockServiceImpl;
    @Test
    public void testaddStock() {
        Stock stock = new Stock();
        stock.setTitle("Test");
        Stock result = stockServiceImpl.addStock(stock);
        assertEquals("Test", result.getTitle());
    }
}