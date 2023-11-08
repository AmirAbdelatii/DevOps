package tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class InvoiceServiceImpTest {

    @Mock
    InvoiceRepository invoiceRepository;
    @Mock
    OperatorRepository operatorRepository;
    @Mock
    InvoiceDetailRepository invoiceDetailRepository;
    @Mock
    SupplierRepository supplierRepository;
    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Test
    public void testRetrieveAllInvoices() {
        Invoice invoice1 = new Invoice();
        invoice1.setArchived(Boolean.FALSE);

        Invoice invoice2 = new Invoice();
        invoice2.setArchived(Boolean.FALSE);

        List<Invoice> expectedInvoices = Arrays.asList(invoice1, invoice2);

        Mockito.when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        List<Invoice> result = invoiceService.retrieveAllInvoices();
        System.out.println(expectedInvoices.size());
        System.out.println(result.size());
        assertNotNull(result);
        assertEquals(expectedInvoices.size(), result.size());
        assertTrue(result.containsAll(expectedInvoices));
    }

    @Test
    public void testCancelInvoice() {
        Long invoiceId = 1L;
        invoiceService.cancelInvoice(invoiceId);
        verify(invoiceRepository).updateInvoice(invoiceId);
    }

    @Test
    public void testRetrieveInvoice() {
        Long invoiceId = 1L;
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setIdInvoice(invoiceId);

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Verify that the repository's findById method was called with the correct argument
        verify(invoiceRepository).findById(invoiceId);

        assertEquals(expectedInvoice, retrievedInvoice);
    }

    /*@Test
    public void testGetInvoicesBySupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(supplierId);

        Invoice invoice1 = new Invoice();
        invoice1.setIdInvoice(1L);

        Invoice invoice2 = new Invoice();
        invoice2.setIdInvoice(2L);

        Set<Invoice> invoices = new HashSet<>();
        invoices.add(invoice1);
        invoices.add(invoice2);

        supplier.setInvoices(invoices);

        Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        List<Invoice> result = invoiceService.getInvoicesBySupplier(supplierId);

        assertEquals(new ArrayList<>(invoices), result);
    }*/

    /*@Test
    public void testAssignOperatorToInvoice() {
        Long idOperator = 1L;
        Long idInvoice = 2L;

        Operator operator = new Operator();
        operator.setIdOperateur(idOperator);

        Invoice invoice = new Invoice();
        invoice.setIdInvoice(idInvoice);

        Mockito.when(operatorRepository.findById(idOperator)).thenReturn(Optional.of(operator));
        Mockito.when(invoiceRepository.findById(idInvoice)).thenReturn(Optional.of(invoice));

        invoiceService.assignOperatorToInvoice(idOperator, idInvoice);

        assertEquals(1, operator.getInvoices().size());
        assertTrue(operator.getInvoices().contains(invoice));

        verify(operatorRepository).save(operator);
    }*/

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        Date startDate = Date.from(Instant.parse("2023-01-01T00:00:00.00Z"));
        Date endDate = Date.from(Instant.parse("2023-01-31T23:59:59.99Z"));
        float expectedTotalAmount = 1000.0f;

        Mockito.when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedTotalAmount);
        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        verify(invoiceRepository).getTotalAmountInvoiceBetweenDates(startDate, endDate);

        assertEquals(expectedTotalAmount, totalAmount, 0.01f); // You can adjust the delta value as needed
    }
}
