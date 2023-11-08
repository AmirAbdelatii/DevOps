package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() {
        Long stockId = 1L;
        Stock stock = new Stock();
        Product product = new Product();
        product.setTitle("Test Product");

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        Product addedProduct = productService.addProduct(product, stockId);

        assertEquals(stock, addedProduct.getStock());
    }

    @Test
    public void testRetrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);
        product.setTitle("Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(productId);

        assertEquals(product, retrievedProduct);
    }

    @Test
    public void testRetrieveAllProduct() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setTitle("Product 2");

        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveAllProduct();

        assertEquals(2, retrievedProducts.size());
    }

    @Test
    public void testRetrieveProductByCategory() {
        ProductCategory category = ProductCategory.CLOTHING; // Replace with an actual category
        Product product1 = new Product();
        product1.setTitle("Product 1");
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        product2.setCategory(category);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productRepository.findByCategory(category)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retrieveProductByCategory(category);

        assertEquals(2, retrievedProducts.size());
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        productService.deleteProduct(productId);

        // Verify that the delete method was called with the correct ID
        Mockito.verify(productRepository).deleteById(productId);
    }

    @Test
    public void testRetrieveProductStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);

        Product product1 = new Product();
        product1.setTitle("Product 1");
        product1.setStock(stock);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        product2.setStock(stock);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productRepository.findByStockIdStock(stockId)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveProductStock(stockId);

        assertEquals(2, retrievedProducts.size());
    }
}
