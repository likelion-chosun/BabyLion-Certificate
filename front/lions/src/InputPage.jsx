import styled from 'styled-components'
import Tag from './Tag.jsx'
import { Link } from 'react-router-dom';

function InputPage(){
    return(
        <Container>
        <div>
          <Title>오늘은</Title>
          <Title>어떤</Title>
          <Title>하루인가요?</Title>
          <TagBox>
            <Tag name='비오는' /><Tag name='행복한' /><Tag name='우울한' />
            <Tag name='맑은' /><Tag name='쉬고싶은' /><Tag name='지루한' />
            <Tag name='에너지 넘치는' /><Tag name='😍' /><Tag name='😭' />
          </TagBox>
          <Input placeholder='직접 입력'></Input>
        </div>
  
        <Link to='/Recommend'><Submit>일정 추천받기</Submit></Link>
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

// const Tag = styled.button`
//   width: fit-content;
//   background-color: #F0F2F4;
//   color: #606A78;
//   border-radius: 15px;
//   border: none;
//   box-sizing: border-box;
//   font-size: 24px;
//   padding: 2px 8px;
// `

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