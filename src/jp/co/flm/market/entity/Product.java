/**
 * jp.co.flm.market.entity.Product
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.entity;

import java.io.Serializable;

/**
 * 商品情報を管理するエンティティクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 *
 */
public class Product implements Serializable {

    /** 商品ID */
    private String productId;

    /** 商品名 */
    private String productName;

    /** 単価 */
    private int price;

    /** 商品画像 */
    private String picture;

    /** ポイント */
    private int point;

    /** カテゴリ情報 */
    private Category category;

    /** 在庫情報 */
    private Stock stock;

    /**
     * コンストラクタ
     */
    public Product() {
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
     * 商品名を取得する。
     *
     * @return 商品名
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 商品名を設定する。
     *
     * @param productName
     *            商品名
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 単価を取得する。
     *
     * @return 単価
     */
    public int getPrice() {
        return price;
    }

    /**
     * 単価を設定する。
     *
     * @param price
     *            単価
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * 商品画像を取得する。
     *
     * @return 商品画像
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 商品画像を設定する。
     *
     * @param picture
     *            商品画像
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * ポイントを取得する。
     *
     * @return ポイント
     */
    public int getPoint() {
        return point;
    }

    /**
     * ポイントを設定する。
     *
     * @param point
     *            ポイント
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * カテゴリ情報を取得する。
     *
     * @return カテゴリ情報
     */
    public Category getCategory() {
        return category;
    }

    /**
     * カテゴリ情報を設定する。
     *
     * @param category
     *            カテゴリ情報
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * 在庫情報を取得する。
     *
     * @return 在庫情報
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * 在庫情報を設定する。
     *
     * @param stock
     *            在庫情報
     */
    public void setStock(Stock stock) {
        this.stock = stock;
    }

}
