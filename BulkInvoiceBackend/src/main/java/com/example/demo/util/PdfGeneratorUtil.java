package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;

public class PdfGeneratorUtil {

    public static byte[] generate(Invoice invoice) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("INVOICE"));
        document.add(new Paragraph("-----------------------------"));

        document.add(new Paragraph("Invoice ID: " + invoice.getInvoiceId()));
        document.add(new Paragraph("Invoice Date: " + invoice.getInvoiceDate()));
        document.add(new Paragraph("Customer: " + invoice.getCustomer()));
        document.add(new Paragraph("Item: " + invoice.getItem()));
        document.add(new Paragraph("Quantity: " + invoice.getQuantity()));
        document.add(new Paragraph("Unit Price: " + invoice.getUnitPrice()));
        document.add(new Paragraph("Total Amount: " + invoice.getTotalAmount()));
        document.add(new Paragraph("GST: " + invoice.getGst()));
        document.add(new Paragraph("Grand Total: " + invoice.getGrandTotal()));

        document.close();

        return baos.toByteArray();
    }
}