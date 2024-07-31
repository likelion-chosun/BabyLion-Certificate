import { useEffect, useState } from "react";
import './Login.css';
// import NaverIdLogin from "./NaverIdLogin.js"

export default function Login() {

    const [getToken, setGetToken] = useState('');
    const [userInfo, setUserInfo] = useState({});

    return (
        <div className="App">
            <div className="login-container">

                <h1>로그인</h1>
                <div className="email">
                    <label htmlFor="email">이메일</label>
                    <input id="email" type="email" required />
                </div>

                <div className="password">
                    <label htmlFor="password">비밀번호</label>
                    <div>
                        <input id="password" type="password" required /></div>
                </div>

                <button type="submit" className="login-button">로그인</button>

                <div className="additional-options">
                    <span> 아이디 찾기  |  비밀 번호 찾기 </span>
                </div>

                <button className="signup-button">회원 가입</button>

                <p></p>
                <a id="kakao-login-btn" href="javascript:loginWithKakao()">
                    <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"
                        alt="카카오 로그인 버튼" />
                </a>

                <p></p>
                <div id="naverIdLogin">
                    <a id="naverIdLogin_loginButton" href="#">
                        <img src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.1" height="50" />
                    </a>
                </div>

                {/* <NaverIdLogin setGetToken={setGetToken} setUserInfo={setUserInfo} />
        <a id="naver-login-btn">
          <img src="https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.dcafe.kr%2Fbbs_shop%2Fread.htm%3Fprint_yn%3D1%26board_code%3Dbbr_2%26idx%3D26061&psig=AOvVaw0J7hjRwojz2E5-laKrusKD&ust=1722401955627000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCODd1bf9zYcDFQAAAAAdAAAAABAJ" 
          width="222" alt="네이버 로그인 버튼" />
        </a> */}

            </div>

            <p></p>
        </div>

    );

}
