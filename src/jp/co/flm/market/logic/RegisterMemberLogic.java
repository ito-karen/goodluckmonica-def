package jp.co.flm.market.logic;

import jp.co.flm.market.entity.Member;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.dao.ConnectionManager;
import jp.co.flm.market.dao.MemberDAO;

public class RegisterMemberLogic {
    public void registerMember(Member member) throws Exception{
        Connection con = null;
        try {
            con =ConnectionManager.getConnection();
            MemberDAO memberDAO = new MemberDAO(con);
            memberDAO.registerMember(member);
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception("会員登録中にエラーが発生しました。", e);
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                // TODO: handle exception
                throw new MarketBusinessException("システムエラーが発生しました。"
                    + "システム管理者に連絡してください。");
            }
        }
    }
    public Member getMember(String memberId) throws MarketBusinessException, MarketSystemException {
        Connection con = null;
        Member member = null;

        try {
            con = ConnectionManager.getConnection();

            MemberDAO memberDAO = new MemberDAO(con);
            member =  memberDAO.getMember(memberId);


        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new MarketSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
        }finally {
            try {
                if(con != null){
                    con.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
                throw new MarketSystemException("システムエラーが発生しました。システム管理者に連絡してください。");
            }
        }

        return member;
    }

}