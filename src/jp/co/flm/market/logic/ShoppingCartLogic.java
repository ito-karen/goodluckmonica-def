/**
 * jp.co.flm.market.logic.ShoppingCartLogic
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.dao.ConnectionManager;
import jp.co.flm.market.dao.ProductDAO;
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Product;

/**
 * ショッピングカートの業務クラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class ShoppingCartLogic {

    /**
     * 商品情報をショッピングカートに追加する。
     *
     * @param cart
     *            ショッピングカート
     * @param productId
     *            追加する商品ID
     * @return 該当商品が追加されたショッピングカート
     * @throws MarketSystemException
     *             本システムのシステム例外
     * @throws MarketBusinessException
     */
    public ArrayList<Orders> addToCart(ArrayList<Orders> cart, String productId)
        throws MarketSystemException, MarketBusinessException {

        // カートに追加する商品情報を検索する。
        Product product = getProduct(productId);
        //在庫情報を調査：ゼロの場合エラーメッセージを格納
        if (product != null) {
            int stock = product.getStock().getQuantity(); // 在庫を取得
            if (stock == 0) {
                throw new MarketBusinessException("商品" + product.getProductName() + " は在庫切れです。");
            }
        }
        //商品情報が存在しない場合

        // 追加する商品の注文情報の準備
        Orders newOrder = null;
        for (Orders order : cart) {
            // 既にカートに同じ商品が追加されているかを確認する。
            if (product.getProductId().equals(order.getProduct().getProductId())) {
                // 既にカートに同じ商品が追加されている場合、注文数量を1加算する。
                newOrder = order;

                // カートの注文数量を更新する。
                int quantity = order.getQuantity() + 1;
                order.setQuantity(quantity);

                // 小計金額、小計ポイントを更新する。
                order.setSubTotal(product.getPrice() * quantity);
                order.setSubTotalPoint(product.getPoint() * quantity);

                // 同じ商品の注文情報は他にないため、繰り返しから抜ける。
                break;
            }
        }

        // カートに同じ商品が追加されていない場合、注文情報を作成する。
        if (newOrder == null) {
            newOrder = new Orders();
            newOrder.setProduct(product);

            // 注文数量に1を設定する。
            newOrder.setQuantity(1);

            // 小計金額、小計ポイントを更新する。
            newOrder.setSubTotal(product.getPrice());
            newOrder.setSubTotalPoint(product.getPoint());

            // 注文情報をカートに追加する。
            cart.add(newOrder);
        }

        return cart;
    }

    /**
     * ショッピングカートの注文数量を更新する。
     *
     * @param cart
     *            ショッピングカート
     * @param quantityList
     *            注文数量のリスト
     * @return 注文数量が更新されたショッピングカート
     */
    public ArrayList<Orders> updateCart(ArrayList<Orders> cart, int[] quantityList) {

        for (int i = 0; i < cart.size(); i++) {
            Orders order = cart.get(i);
            // カートの注文数量を更新する。
            order.setQuantity(quantityList[i]);

            // 小計金額、小計ポイントを更新する。
            order.setSubTotal(order.getProduct().getPrice() * quantityList[i]);
            order.setSubTotalPoint(order.getProduct().getPoint() * quantityList[i]);
        }

        return cart;
    }

    /**
     * 商品情報をショッピングカートから削除する。
     *
     * @param cart
     *            ショッピングカート
     * @param deleteProductId
     *            削除する商品ID
     * @return 該当商品が削除されたショッピングカート
     */
    public ArrayList<Orders> deleteFromCart(ArrayList<Orders> cart, String deleteProductId) {
        for (Orders order : cart) {
            // 商品がカートに存在するかを確認し、削除する。
            if (deleteProductId.equals(order.getProduct().getProductId())) {
                cart.remove(order);
                break;
            }
        }

        return cart;
    }

    /**
     * 商品IDによる商品情報の1件検索を行う。
     *
     * @param productId
     *            商品ID
     * @return 商品情報
     * @throws MarketSystemException
     *             本システムのシステム例外
     */
    private Product getProduct(String productId) throws MarketSystemException {

        Connection con = null;
        Product product = null;

        try {
            con = ConnectionManager.getConnection();

            ProductDAO pdao = new ProductDAO(con);
            // 商品IDにより商品情報を検索する。
            product = pdao.showProduct(productId);

            if (product == null) {
                throw new MarketSystemException("対象の商品は現在ありません。");
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

        return product;
    }
}
