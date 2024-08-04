import styled from "styled-components";
import { Plus } from 'lucide-react';
import Item from '../component/Item.jsx';
import { ChevronLeft } from 'lucide-react';
import { Routes, Link, Route } from 'react-router-dom';

function Recommend() {

    const recList = [
        {
            name:'운동 하기',
            desc:'오늘은 미세먼지가 적어요',
            c:'#FFAAAA',
        },
        {
            name:'피크닉 가기',
            desc:'오늘은 날씨가 맑아요',
            c:'#BAFF99',
        },
        {
            name:'영화 감상하기',
            desc:'영화 감상은 어떠신가요',
            c:'#C4EDFF',
        },
        {
            name:'카페 가기',
            desc:'음료 한잔 어떠신가요',
            c:'#F8FFA4',
        }
    ]

    return (
        <Container>
            <Schedule>
                <Link style={{ width: 'fit-content' }} to='/'><ChevronLeft /></Link>

                {recList.map((O)=>( <Item name={O.name} desc={O.desc} c={O.c} /> ))}
            </Schedule>

            <Link onClick={()=>{}} to='/Schedule'><Submit>일정 추가하기</Submit></Link>
        </Container>
    )
}

export default Recommend;

const Container = styled.div`
    max-width: 400px;
    background-color: white;
    border:solid 1px #E5E7EB;
    margin: 0px auto;
    min-height: 100svh;
    padding: 32px;
    box-sizing: border-box;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`

const Schedule = styled.div`
    display: flex;
    flex-direction: column;
    gap:16px;
`

const Submit = styled.button`
    width: 100%;
    height: 50px;
    font-size: 17px;
    border: none;
    background-color: #10B981;
    color: white;
    border-radius: 19px;
`