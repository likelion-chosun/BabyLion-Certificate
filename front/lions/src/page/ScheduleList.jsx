import styled from "styled-components"
import { useState } from "react";
import { Link } from "react-router-dom";
import Calendar from "react-calendar";
import './Calendar.css';
import { ChevronLeft, Plus } from 'lucide-react';
import moment from "moment";
import Modal from "../component/modal";


export default function ScheduleList() {

  const [value, onChange] = useState(new Date());
  const [isOpen, setisOpen] = useState(false);
  const newlist = [
    {
      day: "2024-07-31",
      weekday: "월요일",
      schedules: [
        {
          title: "31도서관 가기",
          startTime: "09:00",
          endTime: "10:00"
        },
        {
          title: "영화 보기",
          startTime: "11:00",
          endTime: "12:00"
        },
        {
          title: "집에서 요리하기",
          startTime: "13:00",
          endTime: "15:00"
        },
        {
          title: "온천가기",
          startTime: "13:00",
          endTime: "15:00"
        }
      ]
    },
    {
      day: "2024-07-11",
      weekday: "목요일",
      schedules: [
        {
          title: "11일 일정입니다!",
          startTime: "09:00",
          endTime: "10:00"
        },
        {
          title: "11일에 밥약",
          startTime: "11:00",
          endTime: "12:00"
        },
        {
          title: "일정",
        },
        {
          title: "스크롤 테스트",
        },
        {
          title: "스크롤 테스트",
        },
        {
          title: "스크롤 테스트",
        },
        {
          title: "스크롤 테스트",
        },
        {
          title: "스크롤 테스트",
        },

      ]
    }
  ]

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
          if (newlist.find((x) => x.day === moment(date).format('YYYY-MM-DD'))) {
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
          <h4>Schedule - {moment(value).format("YYYY년 MM월 DD일")}</h4><Plus onClick={()=>setisOpen(true)} strokeWidth='1.5' />
        </Top>

        <Box>
        {/* newlist의 day들에 대해 선택한날짜랑 같다면 -> 선택한날짜의schedules mapping  */}
        {newlist.find((obj) => obj.day === moment(value).format('YYYY-MM-DD'))
          ? newlist.find((obj) => obj.day === moment(value).format('YYYY-MM-DD'))
            .schedules.map((s, index) => (
              <Schedule>
                {s.title}<Time><div>{s.startTime}</div>{s.endTime}</Time>
              </Schedule>
            ))
          : null}
        </Box>

      </View>

      
      {isOpen?<Modal setisOpen={setisOpen} />:null}
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