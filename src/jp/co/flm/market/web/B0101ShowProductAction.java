package jp.co.flm.market.web;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Product;
import jp.co.flm.market.logic.ShowProductLogic;
/**
 * 商品詳細照会画面へ遷移するアクションクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class B0101ShowProductAction {
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
                // 指定された商品IDを取得する。
                String productId = req.getParameter("productId");

                // 商品情報を取得する。
                ShowProductLogic logic = new ShowProductLogic();
                Product product = logic.showProduct(productId);

                // 購入履歴情報をリクエストスコープへ格納する。
                req.setAttribute("product", product);
                page = "product-detail-view.jsp";

            } catch (MarketSystemException e) {
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

