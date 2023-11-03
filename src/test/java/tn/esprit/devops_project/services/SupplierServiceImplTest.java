package tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
@Slf4j
public class SupplierServiceImplTest {

    @InjectMocks
    SupplierServiceImpl supplierService;

    @Mock
    SupplierRepository supplierRepository;

    @Test
    public void testAddSupplier() {
        // Create a Supplier instance and set its properties
        Supplier addedSupplier = new Supplier();
        addedSupplier.setLabel("Supplier Label");

        // Mock the behavior of your repository to return the addedSupplier when save is called
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(addedSupplier);

        // Call the service method you want to test
        Supplier savedSupplier = supplierService.addSupplier(addedSupplier);

        // Assertions
        assertNotNull(savedSupplier);
        // Add more specific assertions based on the behavior you expect
    }
/*
    @Test
    public void testUpdateSupplier() {
        // Create a Supplier instance and set its properties
        Supplier addedSupplier = new Supplier();
        addedSupplier.setIdSupplier(1L);
        addedSupplier.setLabel("Supplier Label");

        // Mock any required dependencies or services
        // For example, you can mock the behavior of your repository to return the addedSupplier

        // Call the service method you want to test
        Supplier updatedSupplier = supplierService.updateSupplier(addedSupplier);

        // Assertions
        assertNotNull(updatedSupplier);
        // Add more specific assertions based on the behavior you expect
    }

*/

    @Test
    public void testRetrieveSupplier() {
        // Create a Supplier instance and set its properties
        Supplier addedSupplier = new Supplier();
        addedSupplier.setIdSupplier(1L);
        addedSupplier.setLabel("Supplier Label");

        // Mock the behavior of your repository to return a Supplier when findById is called
        Mockito.when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(addedSupplier));

        // Call the service method you want to test
        Supplier retrievedSupplier = supplierService.retrieveSupplier(1L);

        // Assertions
        assertNotNull(retrievedSupplier);
        // Add more specific assertions based on the behavior you expect
    }

 /*   @Test
    public void testDeleteSupplier() {
        // Create a Supplier instance and set its properties
        Supplier addedSupplier = new Supplier();
        addedSupplier.setIdSupplier(1L);
        addedSupplier.setLabel("Supplier Label");

        // Mock the behavior of your repository to return a Supplier when findById is called
        Mockito.when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(addedSupplier));

        // Call the service method you want to test
        supplierService.deleteSupplier(1L);

        // Assertions
        // Verify that the delete method was called or add more specific assertions
    }*/
}
