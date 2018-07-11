/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.servlet;

import demo.dao.productDAO;
import demo.dao.productPriceDAO;
import demo.dto.productDisplay;
import demo.dto.productDisplays;
import demo.dto.productPriceDTO;
import demo.utils.ConvertFloatToStringPrice;
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

/**
 *
 * @author Bui Quan
 */
public class gameDetailServlet extends HttpServlet {
    
    private String url = "detail.jsp";

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String rawProductName = (String) request.getParameter("productName");
        String productName = rawProductName.replace("%20", " ");
        //product
        productDisplay product = new productDisplay();
        productDAO proDAO = new productDAO();
        productPriceDAO priceDAO = new productPriceDAO();
        int productId = proDAO.getProductId(productName);
        List<productPriceDTO> priceList = new ArrayList<productPriceDTO>();
        try {
            priceList = priceDAO.getProductPrice(productId);
            //convert price to String 
            ConvertFloatToStringPrice floatToStringPrice = new ConvertFloatToStringPrice();
            for (int k = 0; k < priceList.size(); k++) {
                String priceString = floatToStringPrice.convertToStringCheapestPrice(priceList.get(k).getPrice());
                priceList.get(k).setPriceString(priceString);
            }
            product = proDAO.getProductDisplay(productId);
            product.setPrices(priceList);
            float cheapestPrice = 0;
            if (priceList.size() == 1) {
                cheapestPrice = priceList.get(0).getPrice();
            } else {
                for (int i = 0; i < priceList.size(); i++) {
                    if (cheapestPrice == 0) {
                        cheapestPrice = priceList.get(i).getPrice();
                    }
                    if (cheapestPrice > priceList.get(i).getPrice()) {
                        cheapestPrice = priceList.get(i).getPrice();
                        
                    }
                }
            }
            product.setCheapestPrice(cheapestPrice);
            //convert cheapestPrice to String
            ConvertFloatToStringPrice convertFloatString = new ConvertFloatToStringPrice();
            product.setCheapestPriceString(convertFloatString.convertToStringCheapestPrice(product.getCheapestPrice()));
            List<productDisplay> tmp = new ArrayList<productDisplay>();
            tmp.add(product);
            
            productDisplays wrapper = new productDisplays(tmp);
            String xmlContent = XMLUtilities.JAXBMarshalling(wrapper);
            request.setAttribute("PRODUCT", xmlContent);
            request.setAttribute("NAME", productName);
            
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(gameDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(gameDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(gameDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(gameDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
