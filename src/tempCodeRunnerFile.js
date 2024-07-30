
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <div className="login-container">
       
        <h1>로그인</h1>
          <div className="email">
           <label htmlFor="email">이메일</label>
            <input id="email" type="email" />
          </div>

          <div className="password">
          <label htmlFor="password">비밀번호</label>
          <div>
            <input id="password" type="password" /></div>
          </div>

          <button type="submit" className="login-button">로그인</button>

          <div className="additional-options">
            <span>아이디 찾기 | 비밀번호 찾기</span>
          </div>

          <button className="signup-button">회원 가입</button>

          <p></p>
          <a id="kakao-login-btn" href="javascript:loginWithKakao()">
            <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"
          alt="카카오 로그인 버튼"/>
          </a>
        </div>

        <p></p>
       
      </div>
   
  );
}

export default App;
