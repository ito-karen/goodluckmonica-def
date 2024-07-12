package jp.co.flm.market.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.flm.market.common.MarketBusinessException;
import jp.co.flm.market.common.MarketSystemException;
import jp.co.flm.market.entity.Member;
import jp.co.flm.market.logic.RegisterMemberLogic;


public class B0201CheckEmailAction {

    //セッションを取得（ない場合新規作成する）
    public void checkSession(HttpServletRequest req) {
        req.getSession(true);
    }

    public String validate(HttpServletRequest req) {
        String page = null;

        // メッセージ格納リストを作成する。
        ArrayList<String> errorMessageList = new ArrayList<String>();

        //フォームで入力されたメールアドレスを取得する。
        String memberId = req.getParameter("memberId");

        //入力値を確認する（空チェック）
        if(memberId.length() == 0) {
            errorMessageList.add("メールアドレスは入力必須項目です。");
        }
        //入力値を確認する（不正な文字が含まれているかどうか）
        String [] array ={"<" ,">" ," ' " ,"(", ")", "{", "}", "[", "]", "/", "*", "&", "#", "$", "\\", "\""};
        for (String n:array){
            if(memberId.contains(n)){
                errorMessageList.add("メールアドレスに不正な文字が含まれています。");
                break;
            }
         }

        //入力値を確認する（＠マークが抜けていないかどうか）
        if(!memberId.contains("@")) {
            errorMessageList.add("メールアドレスには＠マークを含めてください。");
        }

        //入力エラーが発生していたかを確認する。
        if (errorMessageList.size() != 0) {
            req.setAttribute("errorMessageList", errorMessageList);
            page = "email-register-view.jsp";
        }

        return page;
    }

    //アクションを実行する
    public String execute(HttpServletRequest req) {

        String page = null;

        checkSession(req); //15行目で定義したメソッドを実行　この段階でセッションは新規作成されているはず

        page = validate(req);

        if(page == null) {     //pageがnullの時はエラーが起きていない状態
           try {
               //フォームで入力されたメールアドレスを取得する。
               String memberId = req.getParameter("memberId");

               //取得したメールアドレスをもとにデータベースを確認する
               RegisterMemberLogic logic = new RegisterMemberLogic();
               logic.getMember(memberId); //getMember(業務Logic)メソッドに戻り値ない

               //セッションを取得する。
               HttpSession session = req.getSession(false); //セッションがない場合は何もしない（絶対新規作成されてるはずなのでTrue指定しない）

               //メールアドレスをセッションへ格納する。
               session.setAttribute("Member", memberId);

               page="member-register-view.jsp";

               }catch(MarketBusinessException e) {
                   //エラーメッセージを取得する。
                   String errorMessage = e.getMessage();

                   //リクエストスコープへエラーメッセージを格納する。
                   ArrayList<String> errorMessageList = new ArrayList<String>();
                   errorMessageList.add(errorMessage);
                   req.setAttribute("errorMessageList", errorMessageList);

                   page = "member-register-view.jsp";

               }catch(MarketSystemException e) {
                 //エラーメッセージを取得する。
                   String errorMessage = e.getMessage();

                   //リクエストスコープへエラーメッセージを格納する。
                   ArrayList<String> errorMessageList = new ArrayList<String>();
                   errorMessageList.add(errorMessage);
                   req.setAttribute("errorMessageList", errorMessageList);

                   page = "error.jsp";
           }

        }

               return page;
    }


}