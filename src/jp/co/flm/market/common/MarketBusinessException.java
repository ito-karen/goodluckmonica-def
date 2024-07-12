/**
 * jp.co.flm.market.common.MarketBusinessException
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.market.common;

/**
 * 本システムにおける業務例外クラスです
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class MarketBusinessException extends Exception {

    /**
     * コンストラクタ
     */
    public MarketBusinessException() {
    }

    /**
     * コンストラクタ
     *
     * @param message
     *            エラーメッセージ
     */
    public MarketBusinessException(String message) {
        super(message);
    }

}
