/**
 * jp.co.flm.market.entity.Orders
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.entity;

import java.io.Serializable;

/**
 * 注文情報を管理するエンティティクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class Orders implements Serializable {

    /** 注文番号 */
    private int orderId;

    /** 会員ID */
    private String memberId;

    /** 注文日付 */
    private String orderDate;

    /** クレジットカード番号 */
    private String creditCardId;

    /** 注文数量 */
    private int quantity;

    /** 商品情報 */
    private Product product;

    /** 小計金額 */
    private int subTotal;

    /** 小計ポイント */
    private int subTotalPoint;

    /**
     * コンストラクタ
     */
    public Orders() {
    }

    /**
     * 注文番号を取得する。
     *
     * @return 注文番号
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * 注文番号を設定する。
     *
     * @param orderId
     *            注文番号
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
     * 注文日付を取得する。
     *
     * @return 注文日付
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * 注文日付を設定する。
     *
     * @param orderDate
     *            注文日付
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * クレジットカード番号を取得する。
     *
     * @return クレジットカード番号
     */
    public String getCreditCardId() {
        return creditCardId;
    }

    /**
     * クレジットカード番号を設定する。
     *
     * @param creditCardId
     *            クレジットカード番号
     */
    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    /**
     * 注文数量を取得する。
     *
     * @return 注文数量
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 注文数量を設定する。
     *
     * @param quantity
     *            注文数量
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 商品情報を取得する。
     *
     * @return 商品情報
     */
    public Product getProduct() {
        return product;
    }

    /**
     * 商品情報を設定する。
     *
     * @param product
     *            商品情報
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * 小計金額を取得する。
     *
     * @return 小計金額
     */
    public int getSubTotal() {
        return subTotal;
    }

    /**
     * 小計金額を設定する。
     *
     * @param subTotal
     *            小計金額
     */
    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * 小計ポイントを取得する。
     *
     * @return 小計ポイント
     */
    public int getSubTotalPoint() {
        return subTotalPoint;
    }

    /**
     * 小計ポイントを設定する。
     *
     * @param subTotalPoint
     *            小計ポイント
     */
    public void setSubTotalPoint(int subTotalPoint) {
        this.subTotalPoint = subTotalPoint;
    }

}
