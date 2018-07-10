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
import javax.servlet.ServletContext;
import org.netbeans.category.Categories;

/**
 *
 * @author Bui Quan
 */
public class getCategory {

    public static List<String> cateList = null;
    public static List<String> cateVNList = null;

    public void getCategory(ServletContext context) throws IOException {
        InputStream is = null;
        try {
            Crawler crawler = new Crawler();
            crawler.parseHTML("http://wongstore.com", "<header>", "<title>Wong's Store - Shop Game Bản Quyền</title>", "");
            XMLUtilities xmlUtils = new XMLUtilities();
            String realPath = context.getRealPath("/");
            String result = xmlUtils.crawler(Crawler.inUseHTML, realPath + "Category.xsl");
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
            cateVNList = new ArrayList<String>();
            //to vietnames
            for (int i = 0; i < cateList.size(); i++) {
                if (cateList.get(i).equals("Adventure")) {
                    cateVNList.add("Thám hiểm");
                }
                if (cateList.get(i).equals("Indie")) {
                    cateVNList.add("Game nhỏ");
                }
                if (cateList.get(i).equals("Sexual Content")) {
                    cateVNList.add("Nhạy cảm");
                }
                if (cateList.get(i).equals("Massively Multiplayer")) {
                    cateVNList.add("Nhiều người");
                }
                if (cateList.get(i).equals("Early Access")) {
                    cateVNList.add("Mới mở");
                }
                if (cateList.get(i).equals("Strategy")) {
                    cateVNList.add("Chiến thuật");
                }
                if (cateList.get(i).equals("Strategy")) {
                    cateVNList.add("Giản dị");
                }
                if (cateList.get(i).equals("Action")) {
                    cateVNList.add("Hành động");
                }
                if (cateList.get(i).equals("Simulation")) {
                    cateVNList.add("Mô phỏng");
                }
                if (cateList.get(i).equals("RPG")) {
                    cateVNList.add("Nhập vai");
                }
                if (cateList.get(i).equals("Sports")) {
                    cateVNList.add("Thể thao");
                }
                if (cateList.get(i).equals("Racing")) {
                    cateVNList.add("Đua xe");
                }
                if (cateList.get(i).equals("Utilities")) {
                    cateVNList.add("Tiện ích");
                }
                if (cateList.get(i).equals("Violent")) {
                    cateVNList.add("Bạo lực");
                }
                if (cateList.get(i).equals("Steam wallet")) {
                    cateVNList.add("Ví Steam");
                }
                if (cateList.get(i).equals("steamcode")) {
                    cateVNList.add("Mã Steam");
                }
            }
            categoryDAO cateDAO = new categoryDAO();

            //check if already exist in DB
            for (int i = 0; i < cateList.size(); i++) {
                int id = cateDAO.getIndexCategory(cateList.get(i));
                // != -99 means id has already been set in DB -> only update that category
                if (id != -99) {
                    cateDAO.updateCategory(cateList.get(i), cateVNList.get(i), id);
                    System.out.println("category has been update!");
                } else {
                    cateDAO.insertToDB(cateList.get(i), cateVNList.get(i));
                    System.out.println("Just add new category!");

                }
            }

//            for (int i = 0; i < cateList.size(); i++) {
//                dao.insertToDB(cateList.get(i), cateVNList.get(i));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }
}
