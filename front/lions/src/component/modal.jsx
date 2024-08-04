import { useState } from "react";
import styled from "styled-components";
import { X } from 'lucide-react'
import moment from "moment";

export default function Modal(props) {

    function onChange(event) {
        // 매개변수 'event'는 이벤트가 발생한 태그의 정보를 가져온다.
        setname(event.target.value)
        console.log(event.target.value);
        // 값이 바뀔때마다 setname으로 name값을 변경해준다.
    }
    function append() {
        //서버에게 "날짜" , {일정 객체} 주면서 추가해달라고 요청
        // props.setList(...props.List.schdules,
        //     props.List.find((obj) => obj.day === moment(value).format('YYYY-MM-DD')).schdules);
        props.setisOpen(false);
    }
    const [name, setname] = useState('');

    return (
        <Container onClick={() => props.setisOpen(false)}>
            <Contents onClick={(event) => event.stopPropagation()}>

                <Top><h2>일정 추가</h2> <X onClick={() => props.setisOpen(false)} strokeWidth='1.6' /></Top>
                <Input type='text' onChange={onChange} placeholder="제목" />
                <Submit onClick={append}><p>추가하기</p></Submit>
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
    border-radius: 19px;
    p{
        margin: 10px;
    }
`