/* jp.co.flm.market.web.B0103PurchaseProductsReturnAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.entity.Member;
import jp.co.flm.market.entity.Orders;

/**
 * 商品購入画面へ戻るアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0103PurchaseProductsReturnAction {

    /**
     * セッションチェックを行う。
     *
     * @param req
     *            HttpServletRequest
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
//            // ショッピングカートを取得する。
//            ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");
//
//            //会員情報を取得する
//            Member member = (Member) session.getAttribute("CommonLoginMember");
//
//            page = "purchase-products-view.jsp";
//
//         // ショッピングカートができていないもしくは会員情報取得ができてない場合、エラーメッセージをリクエストスコープに格納する。
//            if (cart == null || member == null ) {
//                ArrayList<String> errorMessageList = new ArrayList<String>();
//                errorMessageList.add("セッションが無効になりました。再度トップ画面から操作をやりなおしてください。");
//                req.setAttribute("errorMessageList", errorMessageList);
//
//                page = "error.jsp";
//
//            }

        }
        return page;
    }

    /**
     * アクションを実行する。
     *
     * @param req HttpServletRequest
     * @return 次画面のJSP名
     */
    public String execute(HttpServletRequest req) {
        String page = null;

     // セッションチェックを行う
        page = checkSession(req);

        return page;
    }
}