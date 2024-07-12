/**
 * jp.co.flm.market.logic.MemberInfoLogic
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.dao.ConnectionManager;
import jp.co.flm.market.dao.MemberDAO;
import jp.co.flm.market.dao.OrdersDAO;
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.entity.Orders;

/**
 * 会員情報を照会する業務クラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class MemberInfoLogic {

    /**
     * 会員ID、パスワードによる会員情報の検索を行う。
     *
     * @param memberId
     *            会員ID
     * @param password
     *            パスワード
     * @return 会員情報
     * @throws MarketBusinessException
     *             本システムの業務例外
     * @throws MarketSystemException
     *             本システムのシステム例外
     */
    public Member getMember(String memberId, String password)
        throws MarketBusinessException, MarketSystemException {

        Connection con = null;
        Member member = null;

        try {
            con = ConnectionManager.getConnection();

            // 会員ID、パスワードにより会員情報を検索する。
            MemberDAO mdao = new MemberDAO(con);
            member = mdao.getMember(memberId, password);

            if (member == null) {
                // 認証に失敗した場合、エラーを発生させる。
                throw new MarketBusinessException("会員IDまたはパスワードが違います。");
            }

        } catch (SQLException e) {
            throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
                }
            }
        }

        return member;
    }

    /**
     * 会員の購入履歴情報の検索を行う。
     *
     * @param memberId
     *            会員ID
     * @return 購入履歴情報
     * @throws MarketSystemException
     *             本システムのシステム例外
     */
    public ArrayList<Orders> getOrderList(String memberId) throws MarketSystemException {

        Connection con = null;
        ArrayList<Orders> orderList = null;

        try {
            con = ConnectionManager.getConnection();

            // 会員IDにより購入履歴情報を検索する。
            OrdersDAO odao = new OrdersDAO(con);
            orderList = odao.getOrderList(memberId);

        } catch (SQLException e) {
            throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new MarketSystemException("システムエラーです。システム管理者に連絡してください。");
                }
            }
        }

        return orderList;
    }

}
