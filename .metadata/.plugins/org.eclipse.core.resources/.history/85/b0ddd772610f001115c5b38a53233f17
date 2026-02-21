package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;

public class PdfGeneratorUtil {

    public static byte[] generate(Invoice invoice) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("INVOICE"));
        document.add(new Paragraph("Invoice No: " + invoice.getInvoiceNumber()));
        document.add(new Paragraph("Customer: " + invoice.getCustomerName()));
        document.add(new Paragraph("Email: " + invoice.getEmail()));
        document.add(new Paragraph("Amount: ₹" + invoice.getAmount()));

        document.close();

        return baos.toByteArray();
    }
}