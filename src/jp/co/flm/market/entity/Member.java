/**
 * jp.co.flm.market.entity.Member
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.entity;

import java.io.Serializable;

/**
 * 会員情報を管理するエンティティクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 *
 */
public class Member implements Serializable {

    /** 会員ID */
    private String memberId;

    /** パスワード */
    private String password;

    /** 会員名 */
    private String memberName;

    /** 性別 */
    private String gender;

    /** 住所 */
    private String address;

    /** 電話番号 */
    private String phone;

    /** ポイント */
    private int memberPoint;

    /**
     * コンストラクタ
     */
    public Member() {
    }

    /**
     * 会員IDを取得する。
     *
     * @return 会員ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 会員IDを設定する。
     *
     * @param memberId
     *            会員ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * パスワードを取得する。
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定する。
     *
     * @param password
     *            パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 会員名を取得する。
     *
     * @return 会員名
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 会員名を設定する。
     *
     * @param memberName
     *            会員名
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 性別を取得する。
     *
     * @return 性別
     */
    public String getGender() {
        return gender;
    }

    /**
     * 性別を設定する。
     *
     * @param gender
     *            性別
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 住所を取得する。
     *
     * @return 住所
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住所を設定する。
     *
     * @param address
     *            住所
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 電話番号を取得する。
     *
     * @return 電話番号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 電話番号を設定する。
     *
     * @param phone
     *            電話番号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * ポイントを取得する。
     *
     * @return ポイント
     */
    public int getMemberPoint() {
        return memberPoint;
    }

    /**
     * ポイントを設定する。
     *
     * @param memberPoint
     *            ポイント
     */
    public void setMemberPoint(int memberPoint) {
        this.memberPoint = memberPoint;
    }

    /**
     * ポイントを計算する。
     *
     * @param addPoint
     *            ポイント
     */
    public void calcMemberPoint(int addPoint) {
        memberPoint += addPoint;
    }

}