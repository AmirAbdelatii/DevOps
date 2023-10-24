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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() {
        // Create a mock Stock
        Stock stock = new Stock();
        Long idStock = 1L; // Replace with a valid stock ID

        // Create a mock Product
        Product product = new Product();
        product.setTitle("Test Product");
        product.setPrice(100.0f);
        product.setQuantity(10);

        // Mock the behavior of stockRepository
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));

        // Mock the behavior of productRepository
        when(productRepository.save(product)).thenReturn(product);

        // Call the addProduct method
        Product addedProduct = productService.addProduct(product, idStock);

        // Verify that the product was associated with the stock
        assertEquals(stock, addedProduct.getStock());

        // You can add more assertions to validate other aspects of the method's behavior
    }
}
