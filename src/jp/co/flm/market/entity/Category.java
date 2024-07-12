/**
 * jp.co.flm.market.entity.Category
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */
package jp.co.flm.market.entity;

import java.io.Serializable;

/**
 * カテゴリ情報を管理するエンティティクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 *
 */
public class Category implements Serializable {

    /** カテゴリID */
    private String categoryId;

    /** カテゴリ名 */
    private String categoryName;

    /** カテゴリ画像 */
    private String picture;

    /**
     * コンストラクタ
     */
    public Category() {
    }

    /**
     * カテゴリIDを取得する。
     *
     * @return カテゴリID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * カテゴリIDを設定する。
     *
     * @param categoryId
     *            カテゴリID
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * カテゴリ名を取得する。
     *
     * @return カテゴリ名
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * カテゴリ名を設定する。
     *
     * @param categoryName
     *            カテゴリ名
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * カテゴリ画像を取得する。
     *
     * @return カテゴリ画像
     */
    public String getPicture() {
        return picture;
    }

    /**
     * カテゴリ画像を設定する。
     *
     * @param picture
     *            カテゴリ画像
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

}
