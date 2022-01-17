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
import java.util.ArrayList;
import java.util.List;

public class BillGenerator {
    public void generateBill(HttpServletResponse response,Order order,User user) throws IOException {
        response.setContentType("application/pdf");
        String headerKey= "Content-Disposition";
        String headerValue ="inline; filename="+order.getOrderId()+".pdf";
        response.setHeader(headerKey,headerValue);
        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();

        PdfPTable table1=new PdfPTable(2);
        table1.setWidthPercentage(100);
        table1.setSpacingBefore(5);
        table1.getDefaultCell().setBorderColor(Color.BLACK);
        table1.addCell("Order Id");
        table1.addCell(""+order.getOrderId());
        table1.addCell("UserName");
        table1.addCell(String.valueOf(""+user.getFirstName()));
        table1.addCell("Shipping Address");
        table1.addCell(""+order.getShippingAddress()+","+order.getState()+","+order.getCountry()+","+order.getZip());
        table1.addCell("Order Date");
        table1.addCell(""+order.getOrderDate());
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
        String[] productIdArray=order.getProductIds().substring(1,order.getProductIds().length() -1).replaceAll("\\s","").split(",");
        String[] productNameArray=order.getProductNames().substring(1,order.getProductNames().length() -1).split(",");
        String[] productQuantitiesArray=order.getQuantities().substring(1,order.getQuantities().length() -1).split(",");
        String[] productPricesArray=order.getPrices().substring(1,order.getPrices().length() -1).split(",");
        String[] productGetTotalArray=order.getTotal().substring(1,order.getTotal().length() -1).split(",");
        List<String> productList =new ArrayList<>();
        for(int i=0;i<productIdArray.length;i++) {
            productList.add(productIdArray[i]);
            productList.add(productNameArray[i]);
            productList.add(productQuantitiesArray[i]);
            productList.add(productPricesArray[i]);
            productList.add(productGetTotalArray[i]);
        }
        System.out.println(productList);
        for (String product:
             productList) {
            table2.addCell(product);
        }
        document.add(table2);
        document.add(new Paragraph("Total Price:"+order.getGrandTotal()));
        

        document.close();

    }
}
