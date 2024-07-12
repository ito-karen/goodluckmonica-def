/**
 * jp.co.flm.market.web.CommonAction
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Category;
import jp.co.flm.market.logic.CommonShowTopPageLogic;


/**
 * トップ画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class CommonShowTopPageAction {

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
         // TOP表示の業務Controllerを生成し、メソッドを呼び出す。
            CommonShowTopPageLogic logic = new CommonShowTopPageLogic();
            ArrayList<Category> categoryList = logic.returnTop();
         // 検索結果をリクエストスコープに設定する。
            req.setAttribute("categoryList", categoryList);
         // 結果画面を戻り値に設定する。
            page = "top-view.jsp";
        } catch(MarketSystemException e) {
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
