/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dao;

import demo.dto.categoryDTO;
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
public class categoryDAO implements Serializable {

    public void insertToDB(String cateString)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ptm = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblCategory(categoryName)"
                        + " VALUES (?)";
                ptm = con.prepareStatement(sql);
                ptm.setString(1, cateString);
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

    public int getIndexCategory(String cateName)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT id FROM tblCategory WHERE categoryName=?";
                ptm = con.prepareStatement(sql);
                ptm.setString(1, cateName);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int cateId = rs.getInt("id");
                    return cateId;
                }
            }
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
        return -99;
    }
    
    public String getNameCategory(int cateId)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryName FROM tblCategory WHERE id=?";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, cateId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String cateName = rs.getString("categoryName");
                    return cateName;
                }
            }
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
        return "";
    }

    public List<categoryDTO> getCategoryList() throws SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<categoryDTO> cateList = new ArrayList<categoryDTO>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT id, categoryName FROM tblCategory";
                ptm = con.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String cateName = rs.getString("categoryName");
                    int id = rs.getInt("id");
                    categoryDTO dto = new categoryDTO(id, cateName);
                    cateList.add(dto);
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
        return cateList;
    }
}
