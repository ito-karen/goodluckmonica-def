/**
 * jp.co.flm.market.logic.ShoppingCartLogic
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.dao.ConnectionManager;
import jp.co.flm.market.dao.MemberDAO;
import jp.co.flm.market.dao.OrdersDAO;
import jp.co.flm.market.dao.StockDAO;
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Stock;

/**
 * 商品を購入する業務クラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */

public class PurchaseProductsLogic {

    /**
     * 会員ID、パスワードによる会員情報の取得を行う。
     *
     * @param memberId
     *            会員ID
     * @param password
     *            パスワード
     * @return 会員情報
     * @throws MarketBusinessException
     *             本システムの業務例外
     * @throws MarketSystemException
     *             本システムのシステム例外
     */
    public Member getMember(String memberId, String password)
        throws MarketBusinessException, MarketSystemException {

        Connection con = null;
        Member member = null;

        try {
            con = ConnectionManager.getConnection();

            // 会員ID、パスワードにより会員情報を検索する。
            MemberDAO mdao = new MemberDAO(con);
            member = mdao.getMember(memberId, password);

            if (member == null) {
                // 認証に失敗した場合、エラーを発生させる。
                throw new MarketBusinessException("会員IDまたはパスワードが違います。");
            }

        } catch (SQLException e) {
            throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
                }
            }
        }

        return member;
    }

    /**
     * 注文処理を行うメソッド
     * @param cart ショッピングカートの注文リスト
     *  @throws MarketSystemException
     *             本システムのシステム例外
     */

     public void processOrder(ArrayList<Orders> cart) throws MarketSystemException {

        Connection con = null;

        try {
            con = ConnectionManager.getConnection();
            //オートコミット解除
            con.setAutoCommit(false);

            // 注文処理の実行
            for(Orders order: cart) {
            insertOrder(order); // 注文の挿入
            selectStockForUpdate(order.getProduct().getProductId()); // 在庫の取得
            updateStock(order.getProduct().getProductId(), order.getQuantity()); // 在庫の更新
            updateMemberPoint(order.getMemberId(), order.getProduct().getPoint()); // 会員ポイントの更新
            }

           // トランザクションのコミット。データベースへの更新内容が反映が確定する
            con.commit();

        } catch (SQLException e) {

            try {
                if(con != null) {
                // トランザクションのロールバック
                con.rollback();
                }
            } catch (SQLException e2) {
                // スタックトレースを出力
                e2.printStackTrace();
            }
        } finally {
                try {
                    if(con != null) {
                    // データベース接続の切断
                    con.close();
                    }
                } catch(SQLException e3) {
                    // スタックトレースを出力
                    e3.printStackTrace();
                }
            }
        }


    /**
     * 注文情報を挿入するメソッド
     * @param order 注文情報
     * @throws MarketSystemException
     *             本システムのシステム例外
     */
    public void insertOrder(Orders order) throws MarketSystemException {

        Connection con = null;

        try {
            con = ConnectionManager.getConnection();

         // 商品テーブルのDAOを生成し、メソッドを呼び出す。

            OrdersDAO oDAO = new OrdersDAO(con);
            oDAO.insertOrder(order);

        } catch (SQLException e) {
            throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
                }
            }
        }
    }


    /**
     * 在庫情報を取得するメソッド（FOR UPDATE）
     * @param productId 商品ID
     * @return Stock 在庫情報
     *  @throws MarketSystemException
     *             本システムのシステム例外
     */
    public Stock selectStockForUpdate(String productId) throws MarketSystemException {

        Connection con = null;
        Stock stock = null;

        try {
            con = ConnectionManager.getConnection();

         // 在庫テーブルのDAOを生成し、在庫情報を検索する。

            StockDAO sDAO = new StockDAO(con);
            stock = sDAO.selectStockForUpdate(productId);

        } catch (SQLException e) {
            throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
                }
            }
        }

        return stock;
    }

    /**
     * 在庫情報を更新するメソッド
     * @param productId 商品ID
     * @param quantity 注文数量
     * @throws MarketSystemException
     *             本システムのシステム例外
     */

    public void updateStock(String productId, int quantity) throws MarketSystemException {

        Connection con = null;

        try {
            con = ConnectionManager.getConnection();

            // 在庫テーブルのDAOを生成し、メソッドを呼び出す。
            StockDAO sDAO = new StockDAO(con);
            sDAO.updateStock(productId, quantity);

        } catch (SQLException e) {
            throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
                }
            }
        }
    }

    /**
     * 会員のポイント数を更新するメソッド
     * @param memberId 会員ID
     * @param point ポイント
     * @throws MarketSystemException
     *             本システムのシステム例外
     */
    public void updateMemberPoint(String memberId, int point) throws MarketSystemException {

        Connection con = null;

        try {
            con = ConnectionManager.getConnection();

            // 在庫テーブルのDAOを生成し、メソッドを呼び出す。
            MemberDAO mDAO = new MemberDAO(con);
            mDAO.updateMemberPoint(memberId, point);

        } catch (SQLException e) {
            throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
                }
            }
        }
    }

}