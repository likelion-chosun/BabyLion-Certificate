import SocialKakao from "./SocialKakao";
import SocialNaver from "./SocialNaver";

import "./Login.css";

export default function Login() {
  return (
    <div className="LogContainer">
      <h3>
        로그인을 해주세요 !
        <p className="second-element">
          <span id="benefit">소셜 로그인으로 가입할 수 있습니다 </span>
        </p>
      </h3>

      <div className="login-button-container">
        <div className="kakao-button">
          <SocialKakao />
        </div>

        <div className="naver-button">
          <SocialNaver />
        </div>
      </div>
    </div>
  );
}
