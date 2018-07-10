/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dao;

import demo.utils.DBUtilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bui Quan
 */
public class proCateDAO implements Serializable {

    public void insertToDB(int productId, int categoryId)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblProduct_tblCategory(productId,categoryId) VALUES (?,?)";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, productId);
                ptm.setInt(2, categoryId);
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean checkExistence(int productId, int cateId) throws SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT * from tblProduct_tblCategory where productId =? and categoryId = ?";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, productId);
                ptm.setInt(2, cateId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<Integer> getCategory(int productId) throws SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<Integer> categoriesList = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryId FROM tblProduct_tblCategory where productId=?";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    categoriesList.add(rs.getInt("categoryId"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return categoriesList;
    }

    public List<Integer> getGamesId(int categoryId) throws SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<Integer> categoriesList = new ArrayList<Integer>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT productId FROM tblProduct_tblCategory where categoryId=?";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, categoryId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    categoriesList.add(rs.getInt("productId"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return categoriesList;
    }
}
