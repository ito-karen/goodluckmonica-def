/**
 * jp.co.flm.market.web.B0102UpdateCartAction
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
 * 注文数量を更新し、ショッピングカート画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0102UpdateCartAction {

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

        page = checkSession(req);

        if (page == null) {
            // セッションを取得する。
            HttpSession session = req.getSession(false);

            // ショッピングカートを取得する。
            ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");

            // フォームで指定された注文数量を取得する。
            String[] qty = req.getParameterValues("productQty");
            int[] quantityList = new int[qty.length];
            for (int i = 0; i < qty.length; i++) {
                quantityList[i] = Integer.parseInt(qty[i]);
            }

            // ショッピングカートが空かどうかを確認する。
            if (cart.size() == 0) {
                // ショッピングカートが空である場合、メッセージをリクエストスコープへ格納する。
                req.setAttribute("message", "ショッピングカートに商品がありません。");
            } else {
                ShoppingCartLogic logic = new ShoppingCartLogic();
                // ショッピングカートの注文数量を指定された数量に更新する。
                cart = logic.updateCart(cart, quantityList);

                // 最新の注文情報（小計金額、小計ポイント）が設定されたショッピングカートをセッションへ格納する。
                session.setAttribute("B01ShoppingCart", cart);

                // ショッピングカートの注文数量を更新したことを伝えるメッセージを格納する。
                req.setAttribute("message", "ショッピングカート内の注文数量を更新しました。");
            }

            page = "shopping-cart-view.jsp";

        }

        return page;
    }
}
