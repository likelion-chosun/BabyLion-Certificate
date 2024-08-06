import styled from "styled-components"
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Calendar from "react-calendar";
import './Calendar.css';
import { ChevronLeft, Plus, X, Pen } from 'lucide-react';
import moment from "moment";
import Modal from "../component/modal";
import axios from "axios";
import Modify from "../component/Modify";


export default function ScheduleList(props) {

  //일정리스트는 props에서
  
  const setList = props.setList;
  const List = props.List;

  const [value, onChange] = useState(new Date());//오늘 날짜
  const [isOpen, setisOpen] = useState(false);//일정 추가 모달창 토글
  const [isPAT, setisPAT] = useState(false);//수정 모달창 토글
  const [cur,setcur] = useState();

  useEffect( ()=>{
    axios.get('https://babylion-api.yeongmin.kr/calendar/events/all')
    .then((response)=>{ setList(response.data); })
    .catch((error)=>{console.log(error.mesaage);});
  },[]); //마운트때만 실행

    const deletes = async (id) => {
      try{
        await axios.delete('https://babylion-api.yeongmin.kr/calendar/delete',{ data: { "id": id } });
        await refreshList();//일정리스트 리렌더링
      } catch (error){
        console.log("ERROR:"+error.message);
      }

    
  }
  const refreshList = async ()=>{
    try{
      const response = await axios.get('https://babylion-api.yeongmin.kr/calendar/events/all');//여기 await붙히니까 잘됨
      setList(response.data);
    } catch(error){
      console.log(error.mesaage);
    }
  }

  return (
    <Container>
      <Header>
        <Link style={{ width: 'fit-content' }} to='/'><ChevronLeft /></Link>
        <Logo>TOPL</Logo>
        <div></div>
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
          <h4>{moment(value).format("YYYY년 MM월 DD일")}</h4><Plus onClick={() => { setcur(moment(value).format("YYYY-MM-DD")); setisOpen(true); }} strokeWidth='1.5' />
        </Top>

        <Box>
          {/* List의 day들에 대해 선택한날짜랑 같다면 -> 선택한날짜의schedules mapping  */}
          {List.find((obj) => obj.day === moment(value).format('YYYY-MM-DD'))
            ? List.find((obj) => obj.day === moment(value).format('YYYY-MM-DD'))
              .dayevent.map((s, index) => (
                <Schedule>
                  <Title>{s.title}</Title>
                  <Boxright>
                  <PAT onClick={()=>{setisPAT(true); setcur(s); }} ><Pen size={16} /></PAT>
                  <Del onClick={()=>{deletes(s.id);}} ><X size={16} /></Del>
                  <Time><div>{s.startTime}</div>{s.endTime}</Time>
                  </Boxright>
                </Schedule>
              ))
            : null}
        </Box>

      </View>


      {isOpen ? <Modal refreshList={refreshList} cur={cur} setisOpen={setisOpen} List={List} setList={setList} /> : null}
      {isPAT ? <Modify cur={cur} setisOpen={setisPAT} List={List} setList={setList} /> : null }
    </Container>
  )
}
const Logo = styled.h3`
  margin-left: 130px;
  font-size: 26px;
`

const Header = styled.div`
  width: 100%;
  /* height: 26px; */
  border-bottom: 1px solid #E9E9E7;
  display: flex;
  padding: 10px;
  box-sizing: border-box;
  align-items: center;
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
  display: flex;
  flex-direction: column;
  gap: 10px;
  ::-webkit-scrollbar{
    width: 10px;
  }
`

const Schedule = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  box-sizing: border-box;
  border: 1px solid #E9E9E7;
  border-radius: 10px;
  padding: 12px;
  align-items: center;
`
const Title = styled.h4`

`
const Time = styled.div`
  font-size: small;
  display: flex;
  flex-direction: column;
  color: grey;
`
const Del = styled.div`
  width: 20px;
  height: 20px;
  /* background-color: red; */
`
const PAT = styled.div`
  width: 20px;
  height: 20px;
  /* background-color: blue; */
`
const Boxright = styled.div`
  display: flex;
  gap: 10px;
  align-items: center;
`