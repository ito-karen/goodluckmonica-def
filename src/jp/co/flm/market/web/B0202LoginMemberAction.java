/**
 * jp.co.flm.market.web.B0202LoginMemberAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.entity.Orders;
import jp.co.flm.market.logic.MemberInfoLogic;

/**
 * 会員情報照会画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0202LoginMemberAction {

    /**
     * セッションチェックを行う。
     *
     * @param req
     *            HttpServletRequest
     */
    public void checkSession(HttpServletRequest req) {

        // セッションを取得（セッションがない場合、作成）する。
        req.getSession(true);
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

        // フォームで指定された会員IDとパスワードを取得する。
        String memberId = req.getParameter("memberId");
        String password = req.getParameter("password");

        // 入力値を確認する（空チェック）。
        if (memberId.length() == 0) {
            errorMessageList.add("会員IDは入力必須項目です。");
        }
        if (password.length() == 0) {
            errorMessageList.add("パスワードは入力必須項目です。");
        }

        // 入力エラーが発生していたかを確認する。
        if (errorMessageList.size() != 0) {
            req.setAttribute("errorMessageList", errorMessageList);
            page = "member-login-view.jsp";
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

        checkSession(req);

        page = validate(req);

        if (page == null) {
            try {
                // フォームで指定された会員IDとパスワードを取得する。
                String memberId = req.getParameter("memberId");
                String password = req.getParameter("password");

                // 会員情報を取得する。
                MemberInfoLogic logic = new MemberInfoLogic();
                Member member = logic.getMember(memberId, password);

                // セッションを取得する。
                HttpSession session = req.getSession(false);
                // 会員情報をセッションへ格納する。
                session.setAttribute("CommonLoginMember", member);

                // 購入履歴情報を取得する。
                ArrayList<Orders> orderList = logic.getOrderList(memberId);

                // 購入履歴情報をリクエストスコープへ格納する。
                req.setAttribute("orderList", orderList);

                if (orderList.size() == 0) {
                    // 購入履歴情報がなかった場合、メッセージをリクエストスコープへ格納する。
                    req.setAttribute("message", "現在ご注文情報はありません。");
                }

                page = "member-info-view.jsp";
            } catch (MarketBusinessException e) {
                // エラーメッセージを取得する。
                String errorMessage = e.getMessage();

                // リクエストスコープへエラーメッセージを格納する。
                ArrayList<String> errorMessageList = new ArrayList<String>();
                errorMessageList.add(errorMessage);
                req.setAttribute("errorMessageList", errorMessageList);

                page = "member-login-view.jsp";
            } catch (MarketSystemException e) {
                // エラーメッセージを取得する。
                String errorMessage = e.getMessage();

                // リクエストスコープへエラーメッセージを格納する。
                ArrayList<String> errorMessageList = new ArrayList<String>();
                errorMessageList.add(errorMessage);
                req.setAttribute("errorMessageList", errorMessageList);

                page = "error.jsp";
            }
        }

        return page;
    }
}
