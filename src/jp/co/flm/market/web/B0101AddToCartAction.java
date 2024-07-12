/**
 * jp.co.flm.market.web.B0101AddToCartAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.logic.ShoppingCartLogic;

/**
 * ショッピングカートに商品を追加し、ショッピングカート画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0101AddToCartAction {

    /**
     * セッションチェックを行う。
     *
     * @param req
     *            HttpServletRequest
     */
    public void checkSession(HttpServletRequest req) {

        // セッションを取得（セッションが作成されていない場合、作成）する。
        HttpSession session = req.getSession(true);

        // ショッピングカートを取得する。
        ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");
        // ショッピングカートが作成されていない場合、作成する。
        if (cart == null) {
            cart = new ArrayList<Orders>();
            session.setAttribute("B01ShoppingCart", cart);
        }

    }

    /**
     * アクションを実行する。
     *
     * @param req
     *            HttpServletRequest
     * @return 次画面のJSP名
     */
    public String execute(HttpServletRequest req) {

        String page = null;

        try {
            // セッションを取得する。
            checkSession(req);
            HttpSession session = req.getSession(false);

            // ショッピングカートを取得する。
            ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");

            // フォームで指定された商品IDを取得する。
            String productId = req.getParameter("productId");

            // 商品情報をショッピングカートへ追加する。
            ShoppingCartLogic logic = new ShoppingCartLogic();
            cart = logic.addToCart(cart, productId);

            // ショッピングカートをセッションへ格納する。
            session.setAttribute("B01ShoppingCart", cart);

            // ショッピングカートへ商品を追加したことを伝えるメッセージを格納する。
            req.setAttribute("message", "ショッピングカートへ商品を追加しました。");

            page = "shopping-cart-view.jsp";
        } catch(MarketBusinessException e) {
            // エラーメッセージを取得する。
            String errorMessage = e.getMessage();

            // リクエストスコープへエラーメッセージを格納する。
            ArrayList<String> errorMessageList = new ArrayList<String>();
            errorMessageList.add(errorMessage);
            req.setAttribute("errorMessageList", errorMessageList);

            page = "error.jsp";
        } catch (MarketSystemException e) {
            // エラーメッセージを取得する。
            String errorMessage = e.getMessage();

            // リクエストスコープへエラーメッセージを格納する。
            ArrayList<String> errorMessageList = new ArrayList<String>();
            errorMessageList.add(errorMessage);
            req.setAttribute("errorMessageList", errorMessageList);

            page = "error.jsp";
        }

        return page;
    }
}
