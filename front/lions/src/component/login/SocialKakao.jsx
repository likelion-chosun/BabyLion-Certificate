const SocialKakao = () => {
  const Rest_api_key = '31198186a824f040716fbeb5f7e66b8a';
  const redirect_uri = 'http://localhost:8080/login/oauth2/code/kakao';

  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;

  const handleLogin = (e) => {
    e.preventDefault();
    window.location.href = kakaoURL;
  };

  return (
    <a id="kakao-login-btn" href="/" onClick={handleLogin}>
      <img
        src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
        width="222"
        alt="카카오 로그인 버튼"
      />
    </a>
  );
};

export default SocialKakao;
