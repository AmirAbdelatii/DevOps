package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.Iservices.ISupplierService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// im just testing the webhook , please work
@SpringBootTest
@Transactional
public class SupplierServiceImplTest {

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        // You can initialize test data or perform any setup if needed.
    }

    @Test
    public void testRetrieveAllSuppliers() {
        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();
        assertEquals(0, suppliers.size()); // Assuming no data is inserted initially.
    }

    @Test
    public void testAddSupplier() {
        Supplier supplier = new Supplier();
        supplier.setLabel("NewSupplier");
        supplier.setCode("NS001");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE); // Replace with the actual category enum value

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        Optional<Supplier> retrievedSupplier = supplierRepository.findById(addedSupplier.getIdSupplier());

        assertEquals("NewSupplier", retrievedSupplier.get().getLabel());
    }

    @Test
    public void testUpdateSupplier() {
        Supplier supplier = new Supplier();
        supplier.setLabel("OldSupplier");
        supplier.setCode("OS001");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        addedSupplier.setLabel("UpdatedSupplier");
        Supplier updatedSupplier = supplierService.updateSupplier(addedSupplier);

        assertEquals("UpdatedSupplier", updatedSupplier.getLabel());
    }

    @Test
    public void testDeleteSupplier() {
        Supplier supplier = new Supplier();
        supplier.setLabel("SupplierToDelete");
        supplier.setCode("SD001");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        supplierService.deleteSupplier(addedSupplier.getIdSupplier());

        Optional<Supplier> deletedSupplier = supplierRepository.findById(addedSupplier.getIdSupplier());
        assertEquals(Optional.empty(), deletedSupplier);
    }

    @Test
    public void testRetrieveSupplier() {
        Supplier supplier = new Supplier();
        supplier.setLabel("TestSupplier");
        supplier.setCode("TS001");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        Supplier retrievedSupplier = supplierService.retrieveSupplier(addedSupplier.getIdSupplier());

        assertEquals("TestSupplier", retrievedSupplier.getLabel());
    }

    @Test
    public void testRetrieveSupplier_InvalidId() {
        assertThrows(IllegalArgumentException.class, () -> supplierService.retrieveSupplier(999L));
    }
}
