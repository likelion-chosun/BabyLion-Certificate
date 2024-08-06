import styled from "styled-components";
import { Plus, ChevronLeft } from 'lucide-react';
import Item from '../component/Item.jsx';
import { Routes, Link, Route } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from "react";
import moment from "moment";


function Recommend(props) {

    const [recList,setrecList] = useState([]);//추천받은 일정 불러오기
    const [Toggle, setToggle] = useState(Array(4).fill(false));
    const C = [
        '#FFAAAA',
        '#BAFF99',
        '#C4EDFF',
        '#F8FFA4',
    ]

    useEffect(()=>{
        //일정 get요청
        const getdata = async () => {
            try{
                const response = await axios.get('https://babylion-api.yeongmin.kr/schedule/recommend');
                setrecList(response.data);
            } catch (error) {}
        }

        getdata();
    },[]);

    const append = () => {
        try{
            recList.map((obj,i)=>{
                if(Toggle[i]){
                    const data = {
                    "scheduleId" : obj.id,
                    "title" : obj.title,
                    "date": moment(new Date()).format("YYYY-MM-DD"),
                    "startTime": moment(new Date()).format("HH:mm"),
                    "endTime": moment(new Date()).format("HH:mm")
                    }
                    axios.post('https://babylion-api.yeongmin.kr/calendar/adddirect',data);
                }
            });
        } catch(error){

        } finally {
            axios.get('https://babylion-api.yeongmin.kr/calendar/events/all')
            .then((response)=>{ setList(response.data); })
            .catch((error)=>{console.log(error.mesaage);});
        }
    }

    return (
        <Container>
            <Schedule>
                <Link style={{ width: 'fit-content' }} to='/'><ChevronLeft /></Link>

                {recList==[]?"Loading...":recList.map((O,i) => (<Item name={O.title} desc={O.description} c={C[i]} Toggle={Toggle} setToggle={setToggle} i={i} />))}

            </Schedule>

            <Link onClick={(e)=>{
                // e.preventDefault();
                append().then(()=>{
                    // window.location.href = '/Schedule'; // 성공적으로 수행 후 페이지 전환
                })
                }} to='/Schedule'><Submit>일정 추가하기</Submit></Link>
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