/**
 * jp.co.flm.market.dao.MemeberDAO
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.flm.market.entity.Member;

/**
 * Memberテーブルを利用するDAOクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class MemberDAO {

    /** コネクション */
    private Connection con;

    /**
     * コンストラクタ
     *
     * @param con
     *            コネクション
     */
    public MemberDAO(Connection con) {
        this.con = con;
    }

    /**
     * 会員情報の1件検索を行う。
     *
     * @param memberId
     *            会員ID
     * @param password
     *            パスワード
     * @return 会員情報
     * @throws SQLException
     *             SQL例外
     */
    public Member getMember(String memberId, String password) throws SQLException {
        // 戻り値の準備
        Member member = null;

        // SQL文の準備
        String sql = "SELECT memberid, membername, gender, address, phone, memberpoint "
            + "FROM member WHERE memberid=? AND password=?";
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, memberId);
            stmt.setString(2, password);
            // SQL文を実行する。
            res = stmt.executeQuery();

            if (res.next()) {
                // 会員情報を作成する。
                member = new Member();
                member.setMemberId(res.getString("memberid"));
                member.setMemberName(res.getString("membername"));
                member.setGender(res.getString("gender"));
                member.setAddress(res.getString("address"));
                member.setPhone(res.getString("phone"));
                member.setMemberPoint(res.getInt("memberpoint"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return member;
    }

    /**
     * 会員情報の1件検索を行う。
     *
     * @param memberId
     *            会員ID
     * @return 会員情報
     * @throws SQLException
     *             SQL例外
     */
    public Member getMember(String memberId) throws SQLException {
        // 戻り値の準備
        Member member = null;

        // SQL文の準備
        String sql = "SELECT memberid, membername FROM member WHERE memberid=?";//mudei
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, memberId);
            // SQL文を実行する。
            res = stmt.executeQuery();

            if (res.next()) {
                // 会員情報を作成する。
                member = new Member();
                member.setMemberId(res.getString("memberid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return member;
    }

    /**
     * 会員情報の登録を行う。
     *
     * @param member
     *            会員情報
     * @throws SQLException
     *             SQL例外
 ]    */
    public void registerMember(Member member) throws SQLException {
        // SQL文の準備
        String sql = "INSERT INTO member(memberid, password, membername, gender,"
            + "address, phone, memberpoint) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        int i = 1;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(i++, member.getMemberId());
            stmt.setString(i++, member.getPassword());
            stmt.setString(i++, member.getMemberName());
            stmt.setString(i++, member.getGender());
            stmt.setString(i++, member.getAddress());
            stmt.setString(i++, member.getPhone());
            stmt.setInt(i++, member.getMemberPoint());
            // SQL文を実行する。
           stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * 会員のポイントの更新を行う。
     *
     * @param memberId
     *            会員ID
     * @param point
     *            ポイント
     * @throws SQLException
     *             SQL例外
     */
    public void updateMemberPoint(String memberId, int point) throws SQLException {
        // SQL文の準備
        String sql = "UPDATE member SET memberpoint=memberpoint+? WHERE memberid=?";
        PreparedStatement stmt = null;
        int i = 1;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(i++, point);
            stmt.setString(i++, memberId);
            // SQL文を実行する。
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    /**
     * 会員情報の更新を行う。
     *
     * @param member
     *            会員情報
     * @throws SQLException
     *             SQL例外
     */
    public void updateMember(Member member) throws SQLException {
        // SQL文の準備
        String sql = "UPDATE member SET password = ?, membername = ?, gender = ?, address = ?, phone = ? "
            + "WHERE memberid = ?";
        PreparedStatement stmt = null;
        int i = 1;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(i++, member.getPassword());
            stmt.setString(i++, member.getMemberName());
            stmt.setString(i++, member.getGender());
            stmt.setString(i++, member.getAddress());
            stmt.setString(i++, member.getPhone());
            stmt.setString(i++, member.getMemberId());
            // SQL文を実行する。
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}