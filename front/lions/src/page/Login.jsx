export default function Login(){
    const REST_API_KEY = '31198186a824f040716fbeb5f7e66b8a';
    const REDIRECT_URI = 'http://localhost:5173/login/oauth2/code/kakao';
    const link = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

    const loginHandler = () => {
        window.location.href = link;
    };

    return (
        <button type='button' onClick={loginHandler}>
            로그인 하기
        </button>
    )
}