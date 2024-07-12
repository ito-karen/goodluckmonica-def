/**
 * jp.co.flm.market.dao.CategoryDAO
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.entity.Category;

/**
 * Categoryテーブルを利用するDAOクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class CategoryDAO {

    /** コネクション */
    private Connection con;

    /**
     * コンストラクタ
     *
     * @param con
     *            コネクション
     */
    public CategoryDAO(Connection con) {
        this.con = con;
    }

    /**
     * カテゴリ情報の全件検索を行う。
     *
     * @return 全カテゴリ情報
     * @throws SQLException
     *             SQL例外
     */
    public ArrayList<Category> returnTop() throws SQLException {
        // カテゴリ情報を格納するリスト
        ArrayList<Category> categoryList = new ArrayList<Category>();
        // SQLクエリ
        String sql = "SELECT categoryname, picture, categoryid FROM category ORDER BY categoryid ASC";
        ResultSet res = null;
        PreparedStatement stmt = null;

        // SQL文の実行と結果セットの処理
        try {
            stmt = con.prepareStatement(sql);
            res = stmt.executeQuery();
            while (res.next()) {
             // Categoryオブジェクトを作成し、結果セットから情報を設定
                Category category = new Category();
                category.setPicture(res.getString("picture"));
                category.setCategoryName(res.getString("categoryname"));
                category.setCategoryId(res.getString("categoryid"));
                // Categoryオブジェクトをリストに追加
                categoryList.add(category);
            }
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        // カテゴリリストを返す
        return categoryList;
    }
}
