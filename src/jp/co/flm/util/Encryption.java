/**
 * jp.co.flm.util.Encryption
 *
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA256によるハッシュ化を実行するクラスです。
 *
 * @author FLM
 * @version 1.0 YYYY/MM/DD
 */
public class Encryption {

    /**
     * ハッシュ化の際にkeyに付加する文字列 デフォルトは"FLMOnlineShoppStore"
     */
    public static final String APPENDSTR = "FLMOnlineShoppStore";

    /**
     * ハッシュ化の際のストレッチ回数 デフォルトは1000
     */
    public static final int STRETCHCOUNT = 1000;

    /**
     * SHA256でtargetをハッシュ化し、16進数表記の文字列に変換した値を返却する。
     * keyとAPPENDSTRにより作成されたソルト値を利用しているため、 keyには重複しない値を指定すること
     * 戻り値であるハッシュ値はSTRETCHCOUNTで指定された回数ストレッチされている。
     *
     * @param key
     *            ソルト値生成のための固有の値
     * @param target
     *            ハッシュ化前の文字列
     * @return ハッシュ化後の文字列
     * @throws NoSuchAlgorithmException
     *             SHA256が利用できない場合
     */
    public static String getEncString(String key, String target) throws NoSuchAlgorithmException {

        // ソルト値
        String salt = key + APPENDSTR;

        // 指定回数分ストレッチしたハッシュ値
        String hash = "";
        for (int i = 0; i < STRETCHCOUNT; i++) {
            hash = sha256(hash + salt + target);
        }

        return hash;
    }

    /**
     * SHA256でハッシュ化し、16進数表記の文字列に変換した値を返却する。
     *
     * @param beforeStr
     *            ハッシュ化前の文字列
     * @return ハッシュ化後の文字列
     * @throws NoSuchAlgorithmException
     *             SHA256が利用できない場合
     * @throws IllegalArgumentException
     *             引数がnullの場合
     */
    private static String sha256(String beforeStr) throws NoSuchAlgorithmException {

        // SHA256で暗号化したByte配列を取得する。
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(beforeStr.getBytes());
        byte[] hash = md.digest();

        // 暗号化されたByte配列を16進数表記の文字列に変換する。
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            String hex = String.format("%02x", b);
            sb.append(hex);
        }
        String afterStr = sb.toString();

        return afterStr;
    }
}
