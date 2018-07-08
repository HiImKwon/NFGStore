/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.divineshop;

import demo.crawl.Crawler;
import demo.dao.productDAO;
import demo.dao.productPriceDAO;
import demo.dto.productDTO;
import demo.utils.XMLUtilities;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.netbeans.divineproduct.Product;
import static demo.wongstore.getProduct.productList;
import java.sql.SQLException;

/**
 *
 * @author Bui Quan
 */
public class getDivineProduct {

    public void searchDivineProduct()
            throws ClassNotFoundException, SQLException, IOException, JAXBException {
        if (productList != null) {
            productDAO productDAO = new productDAO();
            productPriceDAO priceDAO = new productPriceDAO();
            for (int i = 0; i < productList.size(); i++) {
                int id = productDAO.getProductId(productList.get(i).getProductName());
                productDTO dto = addDivineProduct(productList.get(i).getProductName());
                if (dto != null) {
                    float price = convertPrice(dto.getPrice());
                    if (price != -99) {
                        priceDAO.insertProductPrice(price, id, dto.getCreditName());
                        System.out.println("insert divine price successful");
                    }
                }
            }
        }

    }

    private productDTO addDivineProduct(String productName)
            throws IOException, JAXBException {
        Crawler crawler = new Crawler();
        XMLUtilities xmlUtils = new XMLUtilities();
        InputStream is = null;
        try {
            crawler.parseHTML("http://divineshop.vn/index.php?route=product/search&search=" + productName.replace(" ", "%20"),
                    "<div class=\"row products-category\">",
                    "<div class=\"row\">", "");
            String divineProduct = xmlUtils.crawler(Crawler.inUseHTML, "./web/divineProduct.xsl");

            System.out.println(divineProduct);
            if (divineProduct.contains("<price/>") || divineProduct.contains("<productName/>") || divineProduct.contains("<href/>")) {
                return null;
            }
            is = new ByteArrayInputStream(divineProduct.toString().getBytes());
            Product diviProduct = (Product) xmlUtils.JAXBUnmarshalling(is, Product.class);
            productDTO proDTO = new productDTO();
            proDTO.setCreditName("DivineShop");
            proDTO.setHref(diviProduct.getHref());
            proDTO.setProductName(diviProduct.getProductName());
            proDTO.setPrice(diviProduct.getPrice());

            return proDTO;
        } catch (TransformerException e) {
            System.out.println("no match productName");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    private float convertPrice(String productPrice) {
        //eliminate . in vietnam currency
        String price = productPrice.replaceAll("\\.", "");

        //replace from vietnam standard to global standard in currency
        price = price.replaceAll(",", "\\.");

        //eliminate VND currency
        price = price.replace("VNĐ", "");
        price = price.trim();

        if (price.equals("")) {
            return -99;
        } else {
            return new Float(Float.parseFloat(price));

        }
    }
}
