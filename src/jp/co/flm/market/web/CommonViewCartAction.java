/**
 * jp.co.flm.market.web.CommonViewCartAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.entity.Orders;

/**
 * ショッピングカート画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class CommonViewCartAction{

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
            // セッションが確立されていない場合、エラーメッセージをリクエストスコープに格納する。
            ArrayList<String> errorMessageList = new ArrayList<String>();
            errorMessageList.add("セッションが無効になりました。再度トップ画面から操作をやりなおしてください。");
            req.setAttribute("errorMessageList", errorMessageList);
            page = "error.jsp";
        } else {
            // ショッピングカートを取得する。
            ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");
            // ショッピングカートができていない場合、作成する。
            if (cart == null) {
                cart = new ArrayList<Orders>();
                session.setAttribute("B01ShoppingCart", cart);
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

            // ショッピングカートが空かどうかを確認する。
            if (cart.size() == 0) {
                // ショッピングカートが空である場合、メッセージを設定する。
                req.setAttribute("message", "ショッピングカートに商品がありません。");
            }

            page = "shopping-cart-view.jsp";

        }

        return page;
    }
}
