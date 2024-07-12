package jp.co.flm.market.logic;
    import jp.co.flm.market.dao.CategoryDAO;
    import jp.co.flm.market.entity.Category;
    import jp.co.flm.market.dao.ConnectionManager;
    import jp.co.flm.market.common.MarketSystemException;

    import java.sql.Connection;
    import java.sql.SQLException;
    import java.util.ArrayList;

public class CommonShowTopPageLogic {
        // トップページに表示するカテゴリ情報を取得するメソッド
        // 戻り値: カテゴリ情報を格納したList<Category>オブジェクト
        // 例外: MarketSystemException データベースアクセスエラーが発生した場合
        public ArrayList<Category> returnTop() throws MarketSystemException {
            Connection con = null;
            ArrayList<Category> categoryList = null;

            try {
                // データベース接続を取得
                con = ConnectionManager.getConnection();

                // CategoryDAOオブジェクトを作成
                CategoryDAO categoryDAO = new CategoryDAO(con);

                // CATEGORYテーブルから全カテゴリ情報を取得し、昇順にソートして返す
                categoryList = categoryDAO.returnTop();
               //カテゴリー内の商品がゼロの場合
                if (categoryList.size() == 0) {
                    throw new MarketSystemException("カテゴリーが存在しません。");
                }
            } catch (SQLException e) {
                // SQL例外が発生した場合にMarketSystemExceptionをスロー
                throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
            } finally {
                // データベース接続をクローズ
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        // クローズ時に例外が発生した場合にもMarketSystemExceptionをスロー
                        throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
                    }
                }
            }

            return categoryList;
        }
    }
