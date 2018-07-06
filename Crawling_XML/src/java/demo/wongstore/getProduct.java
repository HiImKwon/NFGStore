/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.wongstore;

import demo.crawl.Crawler;
import demo.dao.categoryDAO;
import demo.dto.productDTO;
import demo.utils.XMLUtilities;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.netbeans.product.Products;

/**
 *
 * @author Bui Quan
 */
public class getProduct {

    public static List<productDTO> productList = null;

    public void getProduct()
            throws IOException, FileNotFoundException, TransformerException, JAXBException, ClassNotFoundException, SQLException {
        if (getCategory.cateList.size() != 0 || getCategory.cateList != null) {
            Crawler crawler = new Crawler();
            XMLUtilities xmlUtils = new XMLUtilities();
            productList = new ArrayList<productDTO>();
            categoryDAO cateDAO = new categoryDAO();
            int[] cateID = new int[getCategory.cateList.size()];
            for (int i = 0; i < cateID.length; i++) {
                cateID[i] = cateDAO.getIndexCategory(getCategory.cateList.get(i));
            }

            //for each category i: category index
            for (int i = 0; i < 1; i++) {
                String cateString = getCategory.cateList.get(i);

                //replace "%20" if cateString contain space
                //get pageCount
                if (cateString.contains(" ")) {
                    crawler.parseHTML("http://wongstore.com/theloai/" + cateString.replace(" ", "%20"),
                            "<div class=\"container product\">",
                            "</section>",
                            "href=\"http://wongstore.com/theloai/" + cateString.replace(" ", "%20") + "?page=");
                } else {
                    crawler.parseHTML("http://wongstore.com/theloai/" + cateString,
                            "<div class=\"container product\">",
                            "</section>",
                            "href=\"http://wongstore.com/theloai/" + cateString + "?page=");
                }

                //after receiving pageCount, get product for each page in each category
                //for each page j: page index
                for (int j = 1; j <= 1; j++) {
                    crawler.parseHTML("http://wongstore.com/theloai/" + cateString + "?page=" + j,
                            "<div class=\"container product\">",
                            "</section>",
                            "");
                    String product = xmlUtils.crawler(crawler.inUseHTML, "./web/Product.xsl");

                    InputStream inputStream = new ByteArrayInputStream(product.toString().getBytes());
                    Products products = (Products) xmlUtils.JAXBUnmarshalling(inputStream, Products.class);

                    //add each product from products to productList
                    //k:list of product in one page (products)
                    for (int k = 0; k < products.getProduct().size(); k++) {

                        //get avaUrl, creditName, price, href of dto
                        productDTO productDTO = new productDTO();
                        productDTO.setAvaUrl(products.getProduct().get(k).getAvaUrl());
                        productDTO.setCreditName("Wongstore");
                        productDTO.setPrice(products.getProduct().get(k).getPrice());
                        productDTO.setHref(products.getProduct().get(k).getHref());
                        productDTO.setCategory(String.valueOf(cateID[i]) + ",");

                        //check if the game is already in productList or not (same game but different category)
                        productDTO tmpDTO = checkProduct(productDTO.getHref());
                        //if alreay inside list (!=null) -> add new category else (==null)-> add new product
                        if (tmpDTO == null) {
                            productList.add(productDTO);
                            System.out.println(productDTO.getCategory());
                        } else {
                            int index = productList.indexOf(tmpDTO);
                            String category = productList.get(index).getCategory();
                            productList.get(index).setCategory(category + productDTO.getCategory());
                            System.out.println(productList.get(index).getCategory());
                        }

                    }//end of list products in one page
                    inputStream.close();
                }//end of all pages in one category
            }//end of category for

            //get productName from href
            for (int i = 0; i < productList.size(); i++) {
                String productName = getProductName(productList.get(i).getHref());
                productName = toUpperProductName(productName);
                System.out.println(productName);
                productList.get(i).setProductName(productName);
            }
        }//end of if category is not empty
    }

    private productDTO checkProduct(String href) {
        if (productList != null || productList.size() != 0) {
            for (int i = 0; i < productList.size(); i++) {
                if (href.equals(productList.get(i).getHref())) {
                    return productList.get(i);
                }
            }
        }
        return null;
    }

    private String getProductName(String href) {
        String productName = href.replaceAll("-", " ");

        for (int i = productName.length() - 1; i > 0; i--) {
            if (productName.charAt(i) == '/') {
                productName = productName.substring(i + 1, productName.length());
                return productName;
            }
        }
        return "";
    }

    private String toUpperProductName(String productName) {

        char[] array = productName.toCharArray();
        //Uppercase first letter.
        array[0] = Character.toUpperCase(array[0]);

        //Uppercase all letters that follow a whitespace character.
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }

        //Result
        return new String(array);
    }
    
    
    
}
