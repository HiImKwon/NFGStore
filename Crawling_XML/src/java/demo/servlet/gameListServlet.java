/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.servlet;

import demo.dao.categoryDAO;
import demo.dao.proCateDAO;
import demo.dao.productDAO;
import demo.dao.productPriceDAO;
import demo.dto.productDisplay;
import demo.dto.productDisplays;
import demo.dto.productPriceDTO;
import demo.utils.XMLUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Bui Quan
 */
public class gameListServlet extends HttpServlet {

    private String url = "gameList.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, JAXBException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int category = Integer.parseInt(request.getParameter("category"));
        int pagination = 0;
        try {
            pagination = Integer.parseInt(request.getParameter("txtPagination"));

        } catch (Exception e) {
            pagination = 0;
        }
        List<productDisplay> productList = new ArrayList<productDisplay>();
        proCateDAO productCategoryDAO = new proCateDAO();
        productPriceDAO priceDAO = new productPriceDAO();
        productDAO proDAO = new productDAO();
        categoryDAO cateDAO = new categoryDAO();
        String cateNameVN = cateDAO.getNameCategoryVN(category).toUpperCase();
        try {

            if (pagination == 0) {
                pagination = 1;
            } else {
                pagination = pagination + 1;
            }
            List<Integer> productsIdList = productCategoryDAO.getGamesId(category);
            for (int i = ((pagination - 1) * 12 + 1); i < ((pagination - 1) * 12 + 1 + 12); i++) {
                productDisplay tmpProduct = proDAO.getProductDisplay(productsIdList.get(i));
                List<productPriceDTO> priceList = priceDAO.getProductPrice(productsIdList.get(i));
                tmpProduct.setPrices(priceList);
                if (priceList.size() == 1) {
                    tmpProduct.setCheapestPrice(priceList.get(0).getPrice());
                } else {
                    float cheapestPrice = 0;
                    for (int j = 0; j < priceList.size(); j++) {
                        if (cheapestPrice == 0) {
                            cheapestPrice = priceList.get(j).getPrice();
                        }
                        if (cheapestPrice > priceList.get(j).getPrice()) {
                            cheapestPrice = priceList.get(j).getPrice();
                        }
                    }
                    tmpProduct.setCheapestPrice(cheapestPrice);
                }
                productList.add(tmpProduct);
            }

            //wrapper
            productDisplays wrapper = new productDisplays(productList);
            String xmlProducts = XMLUtilities.JAXBMarshalling(wrapper);
            request.setAttribute("category", category);
            request.setAttribute("PRODUCTS", xmlProducts);
            request.setAttribute("CATENAME", cateNameVN);
            request.setAttribute("PAGINATION", pagination);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(gameListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(gameListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(gameListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(gameListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(gameListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(gameListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
