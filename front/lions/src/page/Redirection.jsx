import { useEffect } from "react";
import axios from "axios";

export default function Redirection() {

    useEffect(() => {
        const code = new URL(document.location.toString()).searchParams.get('code'); // 이상한코드가한가득
        axios.post('https://babylion-api.yeongmin.kr/spring-login',code).then((r) => {
            console.log(r.data); // 토큰과 함께 오는 정보들을 출력해보자
            navigate('/loginSuccess'); // 
        });
    }, []);


    return (
    <div>
        리다이렉션!
    </div>
    )
}