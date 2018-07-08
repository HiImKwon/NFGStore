/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.wongstore;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import demo.divineshop.getDivineProduct;

/**
 *
 * @author Bui Quan
 */
public class test {

    public void runCrawl()
            throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, TransformerException {
        try {
//            test testMePls = new test();
//            testMePls.start();

            getCategory gCate = new getCategory();
            gCate.getCategory();

            getProduct gProduct = new getProduct();
            Thread productThread = new Thread(gProduct);
            productThread.start();
//            gProduct.getProduct();

            productToDB proToDB = new productToDB();
            proToDB.productToDB();

            productPriceToDB priceToDB = new productPriceToDB();
            priceToDB.productPriceToDB();

            categoryToDB cateToDB = new categoryToDB();
            cateToDB.cateToDB();
            
            getDivineProduct divineProduct = new getDivineProduct();
            divineProduct.searchDivineProduct();

//            getCategory gCate = new getCategory();
//            gCate.getCategory();
//            getDivineProduct gProduct = new getDivineProduct();
//            gProduct.getDivineProduct();
//            gProduct.putProductListToDB();
//            crawlerBoi.parseHTML("http://wongstore.com", "<header>", "<title>Wong's Store - Shop Game Bản Quyền</title>", "");
//            XMLUtilities XMLUtil = new XMLUtilities();
//
//            String result = XMLUtil.crawler(crawlerBoi.inUseHTML, "./web/Category.xsl");
//            System.out.println(result);
//            InputStream is = new ByteArrayInputStream(result.toString().getBytes());
//            Categories cate = (Categories) XMLUtil.JAXBUnmarshalling(is, Categories.class);
//            List<Products.Product> productList = new ArrayList<Products.Product>();
//            for (int i = 0; i < 5; i++) {
//                String cateString = cate.getCategory().get(i);
//                if (cateString.contains(" ")) {
//                    cateString = cateString.replace(" ", "%20");
//                }
//                crawlerBoi.parseHTML("http://wongstore.com/theloai/" + cateString,
//                        "<div class=\"container product\">",
//                        "</section>",
//                        "href=\"http://wongstore.com/theloai/" + cateString + "?page=");
////                String product = XMLUtil.crawler(crawlerBoi.inUseHTML, "./web/Product.xsl");
////                System.out.println(product);
////                System.out.println(cate.getCategory().get(i));
////                System.out.println(Crawler.pageCount);
//                for (int j = 1; j <= 5; j++) {
//                    crawlerBoi.parseHTML("http://wongstore.com/theloai/" + cateString + "?page=" + j,
//                            "<div class=\"container product\">",
//                            "</section>",
//                            "");
//                    String product = XMLUtil.crawler(crawlerBoi.inUseHTML, "./web/Product.xsl");
//                    System.out.println(product);
//                    InputStream inputStream = new ByteArrayInputStream(product.toString().getBytes());
//                    Products products = (Products) XMLUtil.JAXBUnmarshalling(inputStream, Products.class);
//                    
//
//                    for (int k = 0; k < products.getDivineProduct().size(); k++) {
//                        if (productList.size() != 0) {
//                            for (int l = 0; l < productList.size(); l++) {
//                                //check if this game already in the list
//                                //if yes -> add new category of this product
//                                //if no -> add new product
//                                if (products.getDivineProduct().get(k).getHref().equals(productList.get(l).getHref())) {
//                                    String cateInteger = String.valueOf((cate.getCategory().indexOf(cateString.replace("%20", " ")) + 1));
//                                    productList.get(l).setCategory(productList.get(l).getCategory() + cateInteger);
//                                } else {
//                                    String cateInteger = String.valueOf((cate.getCategory().indexOf(cateString.replace("%20", " ")) + 1));
//                                    productList.add(products.getDivineProduct().get(k));
//                                    productList.get(l).setCategory(cateInteger);
//                                }
//                            }
//                        } else {
//                            String cateInteger = String.valueOf((cate.getCategory().indexOf(cateString.replace("%20", " ")) + 1));
//                            productList.add(products.getDivineProduct().get(k));
//                            productList.get(productList.size() - 1).setCategory(cateInteger);
//                        }
//                    }
//                }
//                System.out.println("size: " + productList.size());
//                Crawler.pageCount = 0;
//            }
//            for (int i = 0; i < productList.size(); i++) {
//                crawlerBoi.parseHTML(productList.get(i).getHref(), "<section class=\"detail-product\" >", "<section class=\"info-product\" >", "");
//                result = XMLUtil.crawler(crawlerBoi.inUseHTML, "./web/wongstoreProductDetail.xsl");
//                System.out.println(result);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
