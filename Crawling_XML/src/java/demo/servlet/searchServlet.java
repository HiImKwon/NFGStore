/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.servlet;

import demo.dao.proCateDAO;
import demo.dao.productDAO;
import demo.dao.productPriceDAO;
import demo.dto.productDisplay;
import demo.dto.productDisplays;
import demo.dto.productPriceDTO;
import demo.utils.ConvertFloatToStringPrice;
import demo.utils.XMLUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bui Quan
 */
public class searchServlet extends HttpServlet {

    private String url = "search.jsp";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int filter = 0;
        try {
            filter = Integer.parseInt(request.getParameter("txtFilter"));

        } catch (Exception e) {
            filter = 0;
        }
        String txtSearch = request.getParameter("txtSearch").trim();
        try {
            if (!txtSearch.equals("") && !txtSearch.isEmpty()) {
                List<productDisplay> resultList = new ArrayList<productDisplay>();
                productDAO proDAO = new productDAO();
                proCateDAO cateDAO = new proCateDAO();
                productPriceDAO priceDAO = new productPriceDAO();
                //search by category filter
                if (filter != 0) {
                    List<Integer> productIdList = cateDAO.getGamesId(filter);
                    for (int i = 0; i < productIdList.size(); i++) {
                        productDisplay tmp = proDAO.searchProductsWithId(txtSearch, productIdList.get(i));
                        if (tmp != null) {
                            resultList.add(tmp);
                        }
                    }
                } else {
                    resultList = proDAO.searchProducts(txtSearch);
                }
                if (!resultList.isEmpty()) {
                    for (int i = 0; i < resultList.size(); i++) {
                        int id = proDAO.getProductId(resultList.get(i).getProductName());
                        List<productPriceDTO> priceList = priceDAO.getProductPrice(id);
                        resultList.get(i).setPrices(priceList);
                        if (priceList.size() == 1) {
                            resultList.get(i).setCheapestPrice(priceList.get(0).getPrice());
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
                            resultList.get(i).setCheapestPrice(cheapestPrice);
                        }

                        //convert cheapestPrice to String
                        ConvertFloatToStringPrice convertFloatString = new ConvertFloatToStringPrice();
                        resultList.get(i).setCheapestPriceString(convertFloatString.convertToStringCheapestPrice(resultList.get(i).getCheapestPrice()));
                    }
                    //add wrapper 
                    productDisplays wrapper = new productDisplays(resultList);
                    //marshalling
                    String xmlSearch = XMLUtilities.JAXBMarshalling(wrapper);
                    request.setAttribute("SEARCHRESULT", xmlSearch);

                } else {
                    String empty = "Xin lỗi, chúng tôi không tìm thấy sản phẩm này";
                    request.setAttribute("EMPTY", empty);
                }
            } else {
                String empty = "Xin lỗi, chúng tôi không tìm thấy sản phẩm này";
                request.setAttribute("EMPTY", empty);
            }
            request.setAttribute("KEYWORD", txtSearch);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
