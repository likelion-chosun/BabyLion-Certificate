import styled from 'styled-components'
import Tag from '../component/Tag.jsx'
import { Link } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

function InputPage(props) {
  const [Words, setWords] = useState(['비오는', '행복한', '우울한', '맑은', '쉬고싶은', '지루한', '에너지 넘치는','신나는','슬픈',]);
  const [Toggle, setToggle] = useState(Array(9).fill(false));
  let res = {
    "prompt":","
  }
  function makeres(){
    for(let i=0; i<Toggle.length; i++ )
      if(Toggle[i]) res.prompt += Words[i];
    res.prompt += Direct;
    const config = {"Content-Type": 'application/json'};

    axios.post('https://babylion-api.yeongmin.kr/gpt/chat',res,config)
    .then((respones)=>{
      props.setR(respones.data);
    })
    .catch((error)=>{
      console.log("ERROR: "+error.message);
    });
    
    console.log(props.R) //확인용 출력
  }

  const [Direct,setDirect] = useState('');
  function onChange(event){//항상 직접입력 인풋 -> Direct에 넣어주는함수
    // console.log(event.target.value); //확인용 출력
    setDirect(event.target.value);
  }

  return (
    <Container>
      <div>
        <Title>오늘은</Title>
        <Title>어떤</Title>
        <Title>하루인가요?</Title>
        <TagBox>
          {Words.map((word, index) => (<Tag key={index} i={index} Toggle={Toggle} setToggle={setToggle} >{word=='슬픈'?'😭':word}</Tag>))}
        </TagBox>
        <Input onChange={onChange} placeholder='직접 입력'></Input>
      </div>

      <Link onClick={()=>{ makeres(); console.log(JSON.stringify(res)) }} to='/Recommend'><Submit>일정 추천받기</Submit></Link>
      {/* 실사용때 위에는 Link태그로 감싸져야함 */}
    </Container>
  )
}

const Logo = styled.h3`

`

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
const Title = styled.h1`
  margin: auto;
  color: #585858;
  font-size: 38px;
`

const TagBox = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
  margin-top: 30px;
  margin-bottom: 30px;
  gap:10px;
`

const Input = styled.input`
  &::placeholder{
    color: #C3C7CD;
	}
  &:focus{
    outline: none;
	}
  background-color: #F0F2F4;
  border: none;
  font-size: 24px;
  width: 60%;
  /* padding: 5px; */
  padding: 6px 8px;
  box-sizing: border-box;
  border-radius: 10px;
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
export default InputPage;