/**
 * jp.co.flm.market.dao.ProductDAO
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.entity.Category;
import jp.co.flm.market.entity.Product;
import jp.co.flm.market.entity.Stock;

/**
 * Productテーブルを利用するDAOクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class ProductDAO {

    /** コネクション */
    private Connection con;

    /**
     * コンストラクタ
     *
     * @param con
     *            コネクション
     */
    public ProductDAO(Connection con) {
        this.con = con;
    }

    /**
     * カテゴリIDによる商品情報の検索を行う。
     *
     * @param categoryId
     *            カテゴリID
     * @return 商品情報
     * @throws SQLException
     *             SQL例外
     */
    public ArrayList<Product> showCategory(String categoryId) throws SQLException {
        //複数のproductオブジェクトを集約：戻り値の準備
        ArrayList<Product>productlist = new ArrayList<>();
        Product product = null;
        Stock stock = null;
        Category category = null;

      //SQL文
        String sql = "SELECT categoryname, product.productid, productname, price, quantity "
            + "FROM product INNER JOIN category "
            + "ON product.categoryid = category.categoryid "
            + "INNER JOIN stock "
            + "ON product.productid =stock.productid "
            + "WHERE product.categoryid=? ";
        ResultSet res = null;
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, categoryId);
           //SQL文実行
            res = stmt.executeQuery();

            while (res.next()) {
                product = new Product();
                product.setProductId(res.getString("productid"));
                product.setProductName(res.getString("productname"));
                product.setPrice(res.getInt("price"));

                stock = new Stock();
                stock.setQuantity(res.getInt("quantity"));
                product.setStock(stock);

                category = new Category();
                category.setCategoryName(res.getString("categoryname"));
                product.setCategory(category);

                productlist.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return productlist;
    }

    /**
     * 商品情報の1件検索を行う。
     *
     * @param productId
     *            商品ID
     * @return 商品情報
     * @throws SQLException
     *             SQL例外
     */
    public Product showProduct(String productId) throws SQLException {
        //戻り値準備
        Product product = null;
        Stock stock = null;
        Category category = null;

        //SQL文
        String sql = "SELECT category.categoryid, categoryname, product.picture, point, "
            + "product.productid, productname, price, quantity "
            + "FROM product INNER JOIN category "
            + "ON product.categoryid = category.categoryid "
            + "INNER JOIN stock "
            + "ON product.productid =stock.productid "
            + "WHERE product.productid=? ";
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, productId);
            //SQL文を実行する。
            res = stmt.executeQuery();
            if (res.next()) {
                // 商品情報を作成する。
                product = new Product();
                product.setPicture(res.getString("picture"));
                product.setProductId(res.getString("productid"));
                product.setProductName(res.getString("productname"));
                product.setPrice(res.getInt("price"));
                product.setPoint(res.getInt("point"));

                stock = new Stock();
                stock.setQuantity(res.getInt("quantity"));
                product.setStock(stock);

                category = new Category();
                category.setCategoryName(res.getString("categoryname"));
                category.setCategoryId(res.getString("categoryid"));
                product.setCategory(category);
            }
    }catch (SQLException e) {
        e.printStackTrace();
        throw e;
    } finally {
        if (res != null) {
            res.close();
        }
        if (stmt != null) {
            stmt.close();
        }
    }

    return product;
    }
}
