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
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

}
