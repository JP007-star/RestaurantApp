/*
 * @project ResturantApp
 * @fileName BillGenerator
 * @author Jaya Prasad.M --> jaya_muthukrishnan
 * @email jaya_muthukrishnan@thbs.com
 * @date 07 01 2022 04:22 PM
 */
package com.restaurant.app.model;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class BillGenerator {
    public void generateBill(HttpServletResponse response,Order order,User user) throws IOException {
        response.setContentType("application/pdf");
        String headerKey= "Content-Disposition";
        String headerValue ="attachment; filename="+111+".pdf";
        response.setHeader(headerKey,headerValue);
        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();

        PdfPTable table1=new PdfPTable(2);
        table1.setWidthPercentage(100);
        table1.getDefaultCell().setBorderColor(Color.WHITE);
        table1.addCell("Order Id");
        table1.addCell(":"+111);
        table1.addCell("UserName");
        table1.addCell(String.valueOf(":"+user.getFirstName()));
        table1.addCell("Shipping Address");
        table1.addCell(":"+"Address of Place");
        table1.addCell("Travel Date");
        table1.addCell(":"+"12-03-2022");
        table1.setSpacingAfter(10);
        document.add(table1);

        document.add(new Paragraph("Products:"));

        PdfPTable table2=new PdfPTable(5);
        table2.setWidthPercentage(100);
        table2.setSpacingBefore(5);
        table2.getDefaultCell().setBorderColor(Color.BLACK);
        table2.addCell("Product Id");
        table2.addCell("Product Name");
        table2.addCell("Product Quantity");
        table2.addCell("Product Price");
        table2.addCell("Product Total");
        table2.addCell(order.getProductIds());
        table2.addCell(String.valueOf(order.getQuantities()));
        table2.addCell("order.getQuantities()");
        table2.addCell(String.valueOf(order.getPrices()));
        table2.addCell(String.valueOf(order.getTotal()));
        table2.setSpacingAfter(10);
        document.add(table2);
        document.add(new Paragraph("Total Price:"+order.getGrandTotal()));
        document.close();

    }
}
