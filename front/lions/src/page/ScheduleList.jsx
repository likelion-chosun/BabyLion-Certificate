import styled from "styled-components"
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Calendar from "react-calendar";
import './Calendar.css';
import { ChevronLeft, Plus } from 'lucide-react';
import moment from "moment";
import Modal from "../component/modal";
import axios from "axios";
import Modify from "../component/Modify";


export default function ScheduleList() {

  const [value, onChange] = useState(new Date());//오늘 날짜
  const [isOpen, setisOpen] = useState(false);//일정 추가 모달창 토글
  const [isPAT, setisPAT] = useState(false);//수정 모달창 토글
  const [List, setList] = useState([]);//일정리스트
  const [cur,setcur] = useState();

  useEffect(()=>{
  axios.get('https://babylion-api.yeongmin.kr/calendar/events/all')
  .then((response)=>{ setList(response.data); })
  .catch((error)=>{console.log(error.mesaage);});
  },[]); //마운트때만 실행

  function deletes(id){
    axios.delete('https://babylion-api.yeongmin.kr/calendar/delete',{ data: { "id": id } })
    .then(()=>{})
    .catch(()=>{});

    console.log({"id" : id});
  }
  function update(s){
    console.log(s);
    axios.patch('https://babylion-api.yeongmin.kr/calendar/update',s);
  }

  return (
    <Container>
      <Header>
        <Link style={{ width: 'fit-content' }} to='/'><ChevronLeft /></Link>
      </Header>
      <Calendar
        onChange={onChange}
        value={value}
        formatDay={(locale, date) => moment(date).format("D")}
        minDetail="month" // 상단 네비게이션에서 '월' 단위만 보이게 설정
        prev2Label={null}
        next2Label={null}
        // tileContent={addContent}
        showNeighboringMonth={false}

        tileContent={({ date, view }) => {
          const html = [];
          if (List.find((x) => x.day === moment(date).format('YYYY-MM-DD'))) {
            html.push(<Dot></Dot>);
          }
          return (
            <>
              {html}
            </>
          );
        }}
      />


      <View>
        <Top>
          <h4>Schedule - {moment(value).format("YYYY년 MM월 DD일")}</h4><Plus onClick={() => { setisOpen(true); console.log(tmpList) }} strokeWidth='1.5' />
        </Top>

        <Box>
          {/* List의 day들에 대해 선택한날짜랑 같다면 -> 선택한날짜의schedules mapping  */}
          {List.find((obj) => obj.day === moment(value).format('YYYY-MM-DD'))
            ? List.find((obj) => obj.day === moment(value).format('YYYY-MM-DD'))
              .dayevent.map((s, index) => (
                <Schedule>
                  <div>{s.title}</div> <PAT onClick={()=>{setisPAT(true); setcur(s); }} ></PAT>  <Del onClick={()=>{deletes(s.id);}} ></Del>  <Time><div>{s.startTime}</div>{s.endTime}</Time>
                </Schedule>
              ))
            : null}
        </Box>

      </View>


      {isOpen ? <Modal setisOpen={setisOpen} List={List} setList={setList} /> : null}
      {isPAT ? <Modify cur={cur} setisOpen={setisPAT} List={List} setList={setList} /> : null }
    </Container>
  )
}

const Header = styled.div`
  width: 100%;
  height: 26px;
  border-bottom: 1px solid #E9E9E7;
`

const Container = styled.div`
  max-width: 400px;
  background-color: white;
  border:solid 1px #E9E9E7;
  margin: 0px auto;
  min-height: 100svh;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
`

const Dot = styled.div`
  background-color: #ff5353;
  width: 6px;
  height: 6px;
  border-radius: 50%;
  margin: 0 auto;
  display: block;
  box-sizing: border-box;
  margin-top: 2px;
`

const View = styled.div`
  width: 100%;
  min-height: 300px;
  box-sizing: border-box;
  padding: 0 24px;
`

const Top = styled.div`
width: 100%;
color: #37352F;
box-sizing: border-box;
border-top: 1px solid #E9E9E7;
display: flex;
justify-content: space-between;
margin-top: 10px;
padding: 18px 10px;
`

const Box = styled.div`
  height: 36svh;
  overflow-y: scroll;
`

const Schedule = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  box-sizing: border-box;
  border-top: 1px solid #E9E9E7;
  /* border-radius: 10px; */
  padding: 12px;
`
const Time = styled.div`
  font-size: small;
  display: flex;
  flex-direction: column;
`
const Del = styled.div`
  width: 20px;
  height: 20px;
  background-color: red;
`
const PAT = styled.div`
  width: 20px;
  height: 20px;
  background-color: blue;
`