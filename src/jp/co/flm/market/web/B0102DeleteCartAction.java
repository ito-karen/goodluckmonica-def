/**
 * jp.co.flm.market.web.B0102DeleteCartAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.logic.ShoppingCartLogic;

/**
 * ショッピングカートから商品を削除し、ショッピングカート画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0102DeleteCartAction {

    /**
     * セッションチェックを行う。
     *
     * @param req
     *            HttpServletRequest
     * @return エラー画面のJSP名
     */
    public String checkSession(HttpServletRequest req) {
        String page = null;

        // セッションを取得する。
        HttpSession session = req.getSession(false);

        if (session == null) {
            // セッションが確立されていない場合、エラーメッセージをリクエストスコープへ格納する。
            ArrayList<String> errorMessageList = new ArrayList<String>();
            errorMessageList.add("セッションが無効になりました。再度トップ画面から操作をやりなおしてください。");
            req.setAttribute("errorMessageList", errorMessageList);
            page = "error.jsp";
        } else {
            // ショッピングカートを取得する。
            ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");
            // ショッピングカートができていない場合、エラーメッセージをリクエストスコープへ格納する。
            if (cart == null) {
                ArrayList<String> errorMessageList = new ArrayList<String>();
                errorMessageList.add("セッションが無効になりました。再度トップ画面から操作をやりなおしてください。");
                req.setAttribute("errorMessageList", errorMessageList);
                page = "error.jsp";
            }
        }

        return page;
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

        // セッションを取得する。
        page = checkSession(req);

        if (page == null) {
            HttpSession session = req.getSession(false);

            // ショッピングカートを取得する。
            ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");

            // フォームで指定された削除対象の商品情報を取得する。
            String deleteProductId = req.getParameter("deleteProductId");
            ShoppingCartLogic logic = new ShoppingCartLogic();
            cart = logic.deleteFromCart(cart, deleteProductId);

            // ショッピングカートをセッションへ格納する。
            session.setAttribute("B01ShoppingCart", cart);

            // ショッピングカートから商品を削除したことを伝えるメッセージを格納する。
            req.setAttribute("message", "ショッピングカート内の商品を削除しました。");

            page = "shopping-cart-view.jsp";
        }

        return page;
    }
}
