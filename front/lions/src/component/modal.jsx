import { useState } from "react";
import styled from "styled-components";
import { X } from 'lucide-react'

export default function Modal(props){

    const [value, onChange] = useState('10:00');

    return(
        <Container onClick={()=>props.setisOpen()}>
            <Contents>
                <div>
                <Top><h2>일정 추가</h2> <X onClick={()=>props.setisOpen()} strokeWidth='1.6' /></Top>
                <Input placeholder="제목" />
                </div>
                <Submit onClick={()=>{}}><p>추가하기</p></Submit>
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
    margin-top: 20px;
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