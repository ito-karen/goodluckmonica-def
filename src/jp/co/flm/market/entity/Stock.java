/**
 * jp.co.flm.market.entity.Stock
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.entity;

import java.io.Serializable;

/**
 * 在庫情報を管理するエンティティクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 *
 */
public class Stock implements Serializable {

    /** 商品ID */
    private String productId;

    /** 在庫数 */
    private int quantity;

    /**
     * コンストラクタ
     */
    public Stock() {
    }

    /**
     * 商品IDを取得する。
     *
     * @return 商品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 商品IDを設定する。
     *
     * @param productId
     *            商品ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 在庫数を取得する。
     *
     * @return 在庫数
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 在庫数を設定する。
     *
     * @param quantity
     *            在庫数
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}