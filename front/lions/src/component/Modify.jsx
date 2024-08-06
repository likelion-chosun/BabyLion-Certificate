import { useState } from "react";
import styled from "styled-components";
import { X } from 'lucide-react'
import moment from "moment";
import axios from "axios";

export default function Modify(props) {

    const [name, setname] = useState(props.cur.title);
    const [time1, setTime1] = useState(props.cur.startTime);
    const [time2, setTime2] = useState(props.cur.endTime);
    const handle1 = (event)=>{ setTime1(event.target.value); console.log(event.target.value); }
    const handle2 = (event)=>{ setTime2(event.target.value); console.log(event.target.value); }

    function onChange(event) {
        // 매개변수 'event'는 이벤트가 발생한 태그의 정보를 가져온다.
        setname(event.target.value)
        console.log(event.target.value);
        // 값이 바뀔때마다 setname으로 name값을 변경해준다.
    }
    const update = async () => {
        try{
        let data = props.cur;
        data.title = name?name:'내 일정',
        data.startTime = time1;
        data.endTime = time2;
        await axios.patch('https://babylion-api.yeongmin.kr/calendar/update',data);
        props.refreshList();//일정리스트 리렌더링
        } finally{ props.setisOpen(false); }
    }

    return (
        <Container onClick={() => props.setisOpen(false)}>
            <Contents onClick={(event) => event.stopPropagation()}>

                <Top><h2>일정 수정</h2> <X onClick={() => props.setisOpen(false)} strokeWidth='1.6' /></Top>
                <Input type='text' onChange={onChange} placeholder="제목" defaultValue={props.cur.title}/>
                <input type="time" value={time1}  onChange={handle1} />
                <input type="time" value={time2}    onChange={handle2} />

                <Submit onClick={update}><p>추가하기</p></Submit>
                {/* 위 온클릭 안에는 update가 들어가야함 */}
            </Contents>
        </Container>
    )
}

//선택한 날짜에 일정 추가
//일정 이름, 시작시간, 종료시간 입력받아서 부모 컴포넌트 객체에 넣기

const Container = styled.div`
    background-color: rgba(0, 0, 0, 0.4);
    width: 100%;
    height: 100svh;
    z-index: 10;
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    align-items: center;
`
const Contents = styled.div`
    width: 300px;
    height: 300px;
    background-color: white;
    margin: 0 auto;
    border-radius: 16px;
    box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
    padding: 20px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
`
const Top = styled.div`
    width: 100%;
    display: flex;
    justify-content: space-between;
`
const Input = styled.input`
    width: 80%;
    height: 30px;
    font-size: 20px;
`

const Submit = styled.button`
    width: 100%;
    font-size: 17px;
    border: none;
    background-color: #10B981;
    color: white;
    border-radius: 10px;
    p{
        margin: 10px;
    }
`