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
public class productHomePageServlet extends HttpServlet {

    private String homePage = "homePage.jsp";

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
        productDAO proDAO = new productDAO();
        productPriceDAO priceDAO = new productPriceDAO();
        List<productDisplay> firstProducts = new ArrayList<productDisplay>();
        List<productDisplay> secondProducts = new ArrayList<productDisplay>();

//        List<productDTO> firstTop8 = new ArrayList<productDTO>();
//        List<productDTO> secondTop8 = new ArrayList<productDTO>();
//        List<productPriceDTO> firstTop8PriceList = new ArrayList<productPriceDTO>();
//        List<productPriceDTO> secondTop8PriceList = new ArrayList<productPriceDTO>();
        String url = "";
        try {

            //getting first 8 products
            //get from DB
            firstProducts = proDAO.paginationProducts(8, 1);
            //look for prices from different pages
            for (int i = 0; i < firstProducts.size(); i++) {
                //get productId
                int productId = proDAO.getProductId(firstProducts.get(i).getProductName());
                //get prices of that product from different pages
                List<productPriceDTO> firstPrices = priceDAO.getProductPrice(productId);
                //add prices list to that product
                firstProducts.get(i).setPrices(firstPrices);
                //look for cheapest price
                //if the price list size is only 1 -> direct add to cheapest price of that product
                if (firstPrices.size() == 1) {
                    firstProducts.get(i).setCheapestPrice(firstPrices.get(0).getPrice());
                } else {
                    //check for the cheapest price
                    float cheapestPrice = 0;
                    //check in the price list
                    for (int j = 0; j < firstPrices.size(); j++) {
                        if (cheapestPrice == 0) {
                            cheapestPrice = firstPrices.get(j).getPrice();

                        }
                        if (cheapestPrice > firstPrices.get(j).getPrice()) {
                            cheapestPrice = firstPrices.get(j).getPrice();
                        }
                    }
                    //add to that product
                    firstProducts.get(i).setCheapestPrice(cheapestPrice);
                }

                //convert cheapestPrice to String
                ConvertFloatToStringPrice convertFloatString = new ConvertFloatToStringPrice();
                firstProducts.get(i).setCheapestPriceString(convertFloatString.convertToStringCheapestPrice(firstProducts.get(i).getCheapestPrice()));

            }
            //add to a wrapper
            productDisplays firstWrapper = new productDisplays(firstProducts);
            String firstXMLProducts = XMLUtilities.JAXBMarshalling(firstWrapper);

            //getting next 8 products
            //get from DB
            secondProducts = proDAO.paginationProducts(8, 2);
            //look for prices from different pages
            for (int i = 0; i < secondProducts.size(); i++) {
                //get productId
                int productId = proDAO.getProductId(secondProducts.get(i).getProductName());
                //get prices of that product from different pages
                List<productPriceDTO> secondPrices = priceDAO.getProductPrice(productId);
                //add prices list to that product
                secondProducts.get(i).setPrices(secondPrices);
                //look for cheapest price
                //if the price list size is only 1 -> direct add to cheapest price of that product
                if (secondPrices.size() == 1) {
                    secondProducts.get(i).setCheapestPrice(secondPrices.get(0).getPrice());
                } else {
                    //check for the cheapest price
                    float cheapestPrice = 0;
                    //check in the price list
                    for (int j = 0; j < secondPrices.size(); j++) {
                        if (cheapestPrice == 0) {
                            cheapestPrice = secondPrices.get(j).getPrice();

                        }
                        if (cheapestPrice > secondPrices.get(j).getPrice()) {
                            cheapestPrice = secondPrices.get(j).getPrice();
                        }
                    }
                    //add to that product
                    secondProducts.get(i).setCheapestPrice(cheapestPrice);
                }

                //convert cheapestPrice to String
                ConvertFloatToStringPrice convertFloatString = new ConvertFloatToStringPrice();
                secondProducts.get(i).setCheapestPriceString(convertFloatString.convertToStringCheapestPrice(secondProducts.get(i).getCheapestPrice()));
            }
            //add to a wrapper
            productDisplays secondWrapper = new productDisplays(secondProducts);
            String secondXMLProducts = XMLUtilities.JAXBMarshalling(secondWrapper);

            request.setAttribute("FIRSTPRODUCTS", firstXMLProducts);
            request.setAttribute("SECONDPRODUCTS", secondXMLProducts);

//            //get first 8 games
//            firstTop8 = proDAO.paginationProducts(8, 1);
//            //add to a list (wrapper)
//            productDTOs firstTop8List = new productDTOs(firstTop8);
//            //marshall to become a string xml
//            String first8Pro = XMLUtilities.JAXBMarshalling(firstTop8List);
//            //get the first 8 top price
//            for (int i = 0; i < firstTop8.size(); i++) {
//                int id = proDAO.getProductId(firstTop8.get(i).getProductName());
//                List<productPriceDTO> tmp = priceDAO.getProductPrice(id);
//                for (int j = 0; j < tmp.size(); j++) {
//                    firstTop8PriceList.add(tmp.get(j));
//                }
//            }
//            //add to a wrapper
//            productPriceDTOs firstTop8Price = new productPriceDTOs(firstTop8PriceList);
//            //marshall to become a string xml
//            String first8Price = XMLUtilities.JAXBMarshalling(firstTop8Price);
//
//            //get next 8 games
//            secondTop8 = proDAO.paginationProducts(8, 2);
//            //add to wrapper
//            productDTOs nextTop8List = new productDTOs(secondTop8);
//            //marshall to become a string xml
//            String second8Pro = XMLUtilities.JAXBMarshalling(nextTop8List);
//            //get the price
//            for (int i = 0; i < secondTop8.size(); i++) {
//                int id = proDAO.getProductId(secondTop8.get(i).getProductName());
//                List<productPriceDTO> tmp = priceDAO.getProductPrice(id);
//                for (int j = 0; j < tmp.size(); j++) {
//                    secondTop8PriceList.add(tmp.get(j));
//                }
//            }
//            //add to wrapper
//            productPriceDTOs nextTop8Price = new productPriceDTOs(secondTop8PriceList);
//            String second8Price = XMLUtilities.JAXBMarshalling(nextTop8Price);
//
//            //firstTop8, secondTop8 is a list of productDTO
//            //firstTop8List, nextTop8List (productDTOs) is a wrapper for the firstTop8, secondTop8 list -> to marshall to xml
//            //firstTop8PriceList, secondTop8PriceList is a list of productPriceDTO
//            //firstTop8Price, nextTop8Price (productPriceDTOs) is a wrapper for the firstTop8, secondTop8 list -> to marshall to xml
//            //first8Pro, second8Pro string xml for product list
//            //first8Price, second8Price string xml for product price list
//            request.setAttribute("FIRST8PRO", first8Pro);
//            request.setAttribute("SECOND8PRO", second8Pro);
//            request.setAttribute("FIRST8PRICE", first8Price);
//            request.setAttribute("SECOND8PRICE", second8Price);
            url = homePage;
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
