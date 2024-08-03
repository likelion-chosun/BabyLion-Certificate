const SocialNaver = () => {
  const NAVER_CLIENT_ID = 'Xmu2Q58jH8421TcZivly';
  const REDIRECT_URI = 'http://localhost:8080/login/oauth2/code/naver';
  const STATE = 'false';
  const NAVER_AUTH_URL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${NAVER_CLIENT_ID}&state=${STATE}&redirect_uri=${REDIRECT_URI}`;

  const NaverLogin = () => {
    window.location.href = NAVER_AUTH_URL;
  };

  return (
    <a id="naverIdLogin" href="#">
      <img
        src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.1"
        height="48"
        alt="네이버 로그인 버튼"
      />
    </a>
  );
};

export default SocialNaver;
