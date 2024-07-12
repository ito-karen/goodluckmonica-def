package jp.co.flm.market.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * ログアウト処理を行うアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0101LogOutAction {
    /**
     * アクションを実行する。
     *
     * @param req
     *            HttpServletRequest
     * @return 次画面のJSP名
     */
    public String execute(HttpServletRequest req) {
        String page = null;

        // セッションを取得する
        HttpSession session = req.getSession(false);

        if (session != null) {
            // セッションを無効にする
            session.invalidate();
        }

        // ログアウト後の遷移先を設定する
        page = "logout-view.jsp"; // ログイン画面に遷移

        return page;
    }
}

