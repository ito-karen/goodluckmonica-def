/**///メンバーIDとパスワードの入力チェック
function check() {
	//メッセージ
	var message = "";
	//メンバーIDの入力値
	var memberId = document.getElementById("memberId").value;
	//パスワードの入力値
	var password = document.getElementById("password").value;
	//メンバーIDが未入力の場合
	if (memberId.length == 0) {
		message += "<p>会員IDは入力必須項目です。</p>";
	}
	//パスワードが未入力の場合
	if (password.length == 0) {
		message += "<p>パスワードは入力必須項目です。</p>";
	}
	//メッセージが設定された場合、メッセージを表示し、送信中止
	if (message != "") {
		var targetDiv = document.getElementById("target");
		targetDiv.innerHTML = message;
		return false;
	}
	//送信継続
	return true;
}
//読み込み時
window.onload = function() {
	//submitイベントハンドラの設定
	var chkForm = document.getElementById("chkForm");
	chkForm.onsubmit = check;
};