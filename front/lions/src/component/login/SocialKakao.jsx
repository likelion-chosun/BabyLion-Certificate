import { useEffect } from "react";
import axios from "axios";

const client_id = "31198186a824f040716fbeb5f7e66b8a";
const redirect_uri = "https://babylion-api.yeongmin.kr/login/oauth2/code/kakao";
const response_type = "code";
const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${client_id}&redirect_uri=${redirect_uri}&response_type=code`;

const SocialKakao = () => {
  //백엔드가 있는 경우 : 백엔드에서 카카오로 호출하는 api

  //카카오에서 값을 받으면 /code=어쩌고 저쩌고 형태로
  // => code= 어쩌고 형태인경우 : 카카오로부터 인가코드를 받은것으로 판단(임시 ..)

  // useEffect(() => {
  //   if (window.kakao) {
  //     window.Kakao.init(client_id);
  //   }
  // });

  const loginWithKakao = () => {
    window.Kakao.Auth.login({
      success: function (authObj) {
        console.log(authObj);
        // 로그인 성공 시 처리
      },
      fail: function (err) {
        console.error(err);
        // 로그인 실패 시 처리
      },
    });
  };

  useEffect(() => {
    const search = new URLSearchParams(window.location.search);
    const code = search.get("code");

    const accessToken = localStorage.getItem("access_token");

    //카카오로부터 리다이렉트 당한 경우 code가 들어있음
    if (code && (!accessToken || accessToken === "undefined")) {
      //POST   /auth/token 날림 (참고 : 이거 원래 서버에서 하는 거임)
      handleGetToken();
    }
  }, []); //1. 최초 진입시 발동 (1. 찐 최조 / 2.카카오로부터 리다이렉트 당해서  진입)

  const handleGetToken = async () => {
    //원래라면 여기서 백엔드 서버 요청 -> 백엔드 서버에서 카카오로 요청
    //프론트에서 걍 바로 카카오로 날림

    //응답
    const {
      token_type,
      access_token,
      expires_in,
      refresh_token,
      refresh_token_expires_in,
    } = await getToken();

    localStorage.setItem("access_token", access_token);
  };

  const authParam = new URLSearchParams({
    client_id,
    redirect_uri,
    response_type,
  });

  const handleLogin = (e) => {
    e.preventDefault();
    window.location.href = kakaoURL;
  };

  return (
    <a id="kakao-login-btn" href="#" onClick={handleLogin}>
      <img
        src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
        width="222"
        alt="카카오 로그인 버튼"
      />
      <div>{/* <button onClick={handleLogin}>Login with Kakao</button> */}</div>
    </a>
  );
};

export default SocialKakao;
