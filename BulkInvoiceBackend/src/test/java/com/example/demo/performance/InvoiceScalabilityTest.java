package com.example.demo.performance;

import com.example.demo.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@SpringBootTest
public class InvoiceScalabilityTest {

    @Autowired
    private InvoiceService invoiceService;

    @Test
    void compare_1000_vs_10000_invoices() throws Exception {

        long time1000 = runTest(1000);
        long time10000 = runTest(10000);

        System.out.println("Time for 1,000 invoices  : " + time1000 + " ms");
        System.out.println("Time for 10,000 invoices : " + time10000 + " ms");

        double ratio = (double) time10000 / time1000;
        System.out.println("Scalability ratio: " + ratio);
    }

    private long runTest(int count) throws Exception {

        String csv = generateCsv(count);

        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8));

        long start = System.currentTimeMillis();

        invoiceService.processInvoices(inputStream);

        long end = System.currentTimeMillis();

        return end - start;
    }

    private String generateCsv(int count) {

        StringBuilder sb = new StringBuilder();

        sb.append("InvoiceID,InvoiceDate,Customer,Item,Quantity,UnitPrice,TotalAmount,GST,GrandTotal\n");

        for (int i = 1; i <= count; i++) {

            int quantity = 2;
            double unitPrice = 100 + i;
            double totalAmount = quantity * unitPrice;
            double gst = totalAmount * 0.18;
            double grandTotal = totalAmount + gst;

            sb.append("INV")
              .append(i).append(",")
              .append(LocalDate.now()).append(",")
              .append("Customer").append(i).append(",")
              .append("Product").append(i).append(",")
              .append(quantity).append(",")
              .append(unitPrice).append(",")
              .append(totalAmount).append(",")
              .append(gst).append(",")
              .append(grandTotal)
              .append("\n");
        }

        return sb.toString();
    }
}