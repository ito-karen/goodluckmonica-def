/**
 * jp.co.flm.market.dao.ConnectionManager
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベースとの接続を管理するクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class ConnectionManager {

    /** DB接続情報（URL） */
    private static final String URL = "jdbc:mysql://localhost:3306/freemarknDB";

    /** DB接続情報（ユーザー名） */
    private static final String USER = "mysql";

    /** DB接続情報（パスワード） */
    private static final String PASSWORD = "mysql";

    /**
     * コネクションを取得する。
     *
     * @return コネクション
     * @throws SQLException
     *             SQL例外
     */
    public static synchronized Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return con;
    }

}
