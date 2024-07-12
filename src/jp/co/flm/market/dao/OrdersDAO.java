/**
 * jp.co.flm.market.dao.OrdersDAO
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Product;

/**
 * Ordersテーブルを利用するDAOクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class OrdersDAO {

    /** 注文日付の文字列切り取り開始位置 */
    private static final int ORDERDATE_BEGIN_INDEX = 0;

    /** 注文日付の文字列終了開始位置 */
    private static final int ORDERDATE_END_INDEX = 10;

    /** コネクション */
    private Connection con;

    /**
     * コンストラクタ
     *
     * @param con
     *            コネクション
     */
    public OrdersDAO(Connection con) {
        this.con = con;
    }

    /**
     * 注文情報の登録を行う。
     *
     * @param order
     *            注文情報
     * @throws SQLException
     *             SQL例外
     */
    public void insertOrder(Orders order) throws SQLException {
        // SQL文の準備
        String sql = "INSERT INTO orders(memberid, orderdate, creditcardid, productid, "
            + "quantity, price, point) VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        ResultSet res = null;
        int i = 1;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(i++, order.getMemberId());
            stmt.setString(i++, order.getCreditCardId());
            stmt.setString(i++, order.getProduct().getProductId());
            stmt.setInt(i++, order.getQuantity());
            stmt.setInt(i++, order.getProduct().getPrice());
            stmt.setInt(i++, order.getProduct().getPoint());
            // SQL文を実行する。
            stmt.executeUpdate();
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
    }

    /**
     * 会員の購入履歴情報の検索を行う。
     *
     * @param memberId
     *            会員ID
     * @return 購入履歴
     * @throws SQLException
     *             SQL例外
     */
    public ArrayList<Orders> getOrderList(String memberId) throws SQLException {
        // 戻り値の準備
        ArrayList<Orders> orderList = new ArrayList<Orders>();

        // SQL文の準備
        String sql = "SELECT orderid, memberid, orderdate, product.productid, product.productname, "
            + "quantity, orders.price, orders.point, quantity * orders.price AS subtotal, "
            + "quantity * orders.point AS subtotalpoint "
            + "FROM orders INNER JOIN product ON orders.productid=product.productid "
            + "WHERE memberid=? ORDER BY orderid DESC";
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, memberId);
            // SQL文を実行する。
            res = stmt.executeQuery();

            while (res.next()) {
                // 購入履歴情報を作成する。
                Orders order = new Orders();
                order.setOrderId(res.getInt("orderid"));
                order.setMemberId(res.getString("memberid"));
                order.setOrderDate(
                    res.getString("orderdate").substring(ORDERDATE_BEGIN_INDEX, ORDERDATE_END_INDEX));

                // 購入履歴の商品情報を作成する。
                Product product = new Product();
                product.setProductId(res.getString("productid"));
                product.setProductName(res.getString("productname"));
                product.setPrice(res.getInt("price"));
                product.setPoint(res.getInt("point"));
                order.setProduct(product);

                order.setQuantity(res.getInt("quantity"));
                order.setSubTotal(res.getInt("subtotal"));
                order.setSubTotalPoint(res.getInt("subtotalpoint"));

                // 購入履歴情報をリストに追加する。
                orderList.add(order);
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
        return orderList;
    }
}
