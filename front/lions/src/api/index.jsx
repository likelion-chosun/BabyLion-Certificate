import { useEffect } from "react";
import axios from "axios";

const client_id = "31198186a824f040716fbeb5f7e66b8a";
const redirect_uri = "https://babylion-api.yeongmin.kr/login/oauth2/code/kakao"; // 여기에 실제 리다이렉트 URI를 설정하세요.
const response_type = "code";
const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${client_id}&redirect_uri=${redirect_uri}&response_type=${response_type}`;

const SocialKakao = () => {
  useEffect(() => {
    if (window.Kakao) {
      window.Kakao.init(client_id);
    }
  }, []);

  const handleLogin = (e) => {
    e.preventDefault();
    window.location.href = kakaoURL;
  };

  useEffect(() => {
    const search = new URLSearchParams(window.location.search);
    const code = search.get("code");

    const accessToken = localStorage.getItem("access_token");

    if (code && (!accessToken || accessToken === "undefined")) {
      handleGetToken(code);
    }
  }, []);

  const handleGetToken = async (code) => {
    // 원래라면 여기서 백엔드 서버 요청 -> 백엔드 서버에서 카카오로 요청
    try {
      const response = await axios.post("https://kauth.kakao.com/oauth/token", {
        grant_type: "authorization_code",
        client_id,
        redirect_uri,
        code,
      });
      const { access_token } = response.data;
      localStorage.setItem("access_token", access_token);
    } catch (error) {
      console.error("Failed to get access token:", error);
    }
  };

  const axios = require("axios");
};

export default SocialKakao;
