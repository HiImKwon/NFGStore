/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.wongstore;

import demo.crawl.Crawler;
import demo.dao.categoryDAO;
import demo.utils.XMLUtilities;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.category.Categories;

/**
 *
 * @author Bui Quan
 */
public class getCategory {

    public static List<String> cateList = null;

    public void getCategory() throws IOException {
        InputStream is = null;
        try {
            Crawler crawler = new Crawler();
            crawler.parseHTML("http://wongstore.com", "<header>", "<title>Wong's Store - Shop Game Bản Quyền</title>", "");
            XMLUtilities xmlUtils = new XMLUtilities();

            String result = xmlUtils.crawler(crawler.inUseHTML, "./web/Category.xsl");
            is = new ByteArrayInputStream(result.toString().getBytes());
            Categories cate = (Categories) xmlUtils.JAXBUnmarshalling(is, Categories.class);
            if (cateList == null) {
                cateList = new ArrayList<String>();

            }
            //put category to DB
            for (int i = 0; i < cate.getCategory().size(); i++) {
                String dto = cate.getCategory().get(i);
                cateList.add(dto);
            }
            categoryDAO dao = new categoryDAO();
            for (int i = 0; i < cateList.size(); i++) {
                dao.insertToDB(cateList.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is!=null) {
                is.close();
            }
        }

    }
}
