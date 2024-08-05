import axios from "axios";

const getInfoApi = async () => {
  const Server_IP = process.env.REACT_APP_Server_IP;
  try {
    const response = await axios.get(`${Server_IP}/info`); // get 요청을 보내는 URI => 'http://127.0.0.1:8080/info'
    return response.data;
  } catch (err) {
    console.log(err);
    throw err; // 에러를 다시 던져서 호출한 측에서 처리할 수 있게 합니다.
  }
};

// 함수 호출 예시
getInfoApi()
  .then((data) => console.log(data))
  .catch((err) => console.error("API 호출 실패:", err));
