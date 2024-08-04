import styled from "styled-components";
import { Plus, ChevronLeft } from 'lucide-react';
import Item from '../component/Item.jsx';
import { Routes, Link, Route } from 'react-router-dom';
import axios from 'axios';
import { useState } from "react";
import moment from "moment";


function Recommend(props) {

    // const [data, setData] = useState(null);
    // const [loading, setLoading] = useState(true);
    // const [error, setError] = useState(null);

    const recList = props.R;
    const [Toggle, setToggle] = useState(Array(4).fill(false));
    const C = [
        '#FFAAAA',
        '#BAFF99',
        '#C4EDFF',
        '#F8FFA4',
    ]
    function append(){
        props.R.map((obj)=>{
            const data = {
                "title": "집 앞 산책",
                "date": "2024-08-04",
                "startTime": "07:00",
                "endTime": "08:00"
            }
            axios.post('https://babylion-api.yeongmin.kr/calendar/adddirect',data)
            .then(()=>{console.log("one!")})
            .catch(()=>{})
        });
    }

    return (
        <Container>
            <Schedule>
                <Link style={{ width: 'fit-content' }} to='/'><ChevronLeft /></Link>

                {recList.map((O,i) => (<Item name={O.title} desc={O.description} c={C[i]} Toggle={Toggle} setToggle={setToggle} i={i} />))}

            </Schedule>

            <Link onClick={() => { append(); console.log(Toggle); }} to='/Schedule'><Submit>일정 추가하기</Submit></Link>
            {/* 위 onClick은 선택된 일정을 추가하는 POST를 보내야함 */}
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