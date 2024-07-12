package jp.co.flm.market.web;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Product;
import jp.co.flm.market.logic.ShowProductLogic;
/**
 * 指定されたカテゴリー内にあるすべての商品を表示する。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0101ShowCategoryAction {
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
        //指定されたカテゴリーIDを取得する。
            String categoryId = req.getParameter("categoryId");
        // 得意先検索用の業務Controllerを生成し、メソッドを呼び出す。
            ShowProductLogic logic = new ShowProductLogic();
            ArrayList<Product> productlist = logic.showCategory(categoryId);

        // 検索結果をリクエストスコープに設定する。
        req.setAttribute("productlist", productlist);

        // 結果画面を戻り値に設定する。
        page = "product-result-view.jsp";

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
