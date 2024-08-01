import styled from 'styled-components'
import Tag from '../component/Tag.jsx'
import { Link } from 'react-router-dom';
import { useState } from 'react';

function InputPage() {

  const [Words, setWords] = useState(['비오는', '행복한', '우울한', '맑은', '쉬고싶은', '지루한', '에너지 넘치는', '😍', '😭']);
  const [Toggle, setToggle] = useState(Array(9).fill(false));
  const res = []
  function makeres(){
    for(let i=0; i<Toggle.length; i++ )
      if(Toggle[i]) res.push(Words[i]);
    console.log(res)
  }

  return (
    <Container>
      <div>
        <Title>오늘은</Title>
        <Title>어떤</Title>
        <Title>하루인가요?</Title>
        <TagBox>
          {Words.map((word, index) => (<Tag key={index} i={index} Toggle={Toggle} setToggle={setToggle} >{word}</Tag>))}
        </TagBox>
        <Input placeholder='직접 입력'></Input>
      </div>

      <Link onClick={()=>{ makeres(); }} to='/Recommend'><Submit>일정 추천받기</Submit></Link>
      {/* 위에 Link로 감싸기 */}
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