/**
 * jp.co.flm.market.dao.StockDAO
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.flm.market.entity.Stock;

/**
 * Stockテーブルを利用するDAOクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class StockDAO {

    /** コネクション */
    private Connection con;

    /**
     * コンストラクタ
     *
     * @param con
     *            コネクション
     */
    public StockDAO(Connection con) {
        this.con = con;
    }

    /**
     * 在庫情報の1件排他検索を行う。
     *
     * @param productId
     *            商品ID
     * @return 在庫情報
     * @throws SQLException
     *             SQL例外
     */
    public Stock selectStockForUpdate(String productId) throws SQLException {
        // 戻り値の準備
        Stock stock = null;

        // SQL文の準備
        String sql = "SELECT productid, quantity FROM Stock WHERE productid = ? FOR UPDATE";
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, productId);

            // SQL文を実行する。
            res = stmt.executeQuery();

            if (res.next()) {
                // 在庫情報を作成する。
                stock = new Stock();
                stock.setProductId(res.getString("productId"));
                stock.setQuantity(res.getInt("quantity"));

            }
        } catch (SQLException e) {
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

        return stock;
    }

    /**
     * 在庫情報の更新を行う。
     *
     * @param productId
     *            商品ID
     * @param quantity
     *            在庫数
     * @throws SQLException
     *             SQL例外
     */
    public void updateStock(String productId, int quantity) throws SQLException {
        // SQL文の準備
        String sql = "UPDATE stock SET quantity = quantity - ? WHERE productid = ?";
        PreparedStatement stmt = null;
        int i = 1;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(i++, quantity);
            stmt.setString(i++, productId);
            // SQL文を実行する。
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }
}