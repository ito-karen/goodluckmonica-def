/**
 * jp.co.flm.market.web.B0103PurchaseProductsAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.entity.Member;
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.entity.Product;

/**
 * 商品購入確認画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0103PurchaseProductsAction {

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
     * 入力チェックを行う。
     *
     * @param req
     *            HttpServletRequest
     * @return エラー画面のJSP名
     */
    public String validate(HttpServletRequest req) {
        String page = null;

        // メッセージ格納リストを作成する。
        ArrayList<String> errorMessageList = new ArrayList<String>();

        // フォームで指定されたクレジットカード番号を取得する。//名前要確認
        String creditCardIdfull = req.getParameter("creditCardIdfull");

        // 入力値を確認する（空チェック）。
        if (creditCardIdfull.length() == 0) {
            errorMessageList.add("クレジットカード番号は入力必須項目です。");
        }
        // 入力値を確認する（半角16桁の数字かどうか）。
        else if  (!creditCardIdfull.matches("^\\d{16}$")) {
                errorMessageList.add("クレジットカード番号の入力に誤りがあります。");
            }

        // 入力エラーが発生していたかを確認する。
        if (errorMessageList.size() != 0) {
            req.setAttribute("errorMessageList", errorMessageList);
            page = "purchase-products-view.jsp";
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
    public String execute(HttpServletRequest req)  {

        String page = null;

        page = checkSession(req);

        if (page == null) {
        page = validate(req);
        }

        if (page == null) {

          //セッションを取得する
            HttpSession session = req.getSession(false);

            // フォームで指定されたクレジットカード番号を取得する。
            String creditCardIdfull = req.getParameter("creditCardIdfull");

            // クレジットカード番号の下4桁を取得する
            String creditCardId = creditCardIdfull.substring(creditCardIdfull.length() - 4);

            //会員情報を取得する。
            Member member = (Member) session.getAttribute("CommonLoginMember");

            //ショッピングカートを取得する
           ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");

            //ショッピングカートと連結後消す

//             ArrayList<Orders> cart2 = new ArrayList<Orders>();
//             Orders test = new Orders();
//              test.setMemberId(member.getMemberId());
//             test.setOrderDate("2024-07-12");
//             test.setCreditCardId(creditCardId);
//             test.setQuantity(2);
//
//             Product test2 = new Product();
//             test2.setProductId("a01");
//             test2.setPrice(10000);
//             test2.setPoint(100);
//
//            test.setProduct(test2);
//
//              cart2.add(test);
//
//               session.setAttribute("B01ShoppingCart", cart2);
//
//               ArrayList<Orders> cart = (ArrayList<Orders>) session.getAttribute("B01ShoppingCart");


            //会員IDとクレジットカード番号の情報をcartの中に入れていく
            for(Orders order: cart) {
                order.setCreditCardId(creditCardId);
                order.setMemberId(member.getMemberId());
            }

            //ショッピングカート情報をセッションスコープへ格納する。
            session.setAttribute("B01ShoppingCart", cart);

            page = "purchase-check-view.jsp";
     }
        return page;
    }
}


