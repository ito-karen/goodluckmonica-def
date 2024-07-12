/**
 * jp.co.flm.market.web.CommonLoginAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import javax.servlet.http.HttpServletRequest;

/**
 * ログイン画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class CommonLoginAction {

    /**
     * アクションを実行する。
     *
     * @param req
     *            HttpServletRequest
     * @return 次画面のJSP名
     */
    public String execute(HttpServletRequest req) {

        String page = "member-login-view.jsp";

        return page;
    }

}
