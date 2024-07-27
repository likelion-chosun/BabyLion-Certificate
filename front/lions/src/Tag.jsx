import { useState } from "react";
import styled from "styled-components";


function Tag({name}){

  const [Added,setAdded] = useState(false);

    return(
        <Item added={Added} onClick={()=>{setAdded(!Added)}} >{name}</Item>
    )
}
const Item = styled.button`
  width: fit-content;
  background-color: ${props => props.added ? '#F4F4F5' : '#ffffff'};
  /* background-color: ${props => props.added ? '#9CF1A5' : '#F0F2F4'}; */
  /* color: #606A78; */
  color: #8d939c;
  /* color: ${props => props.added ? '#606A78' : '#8d939c'}; */
  border-radius: 15px;
  border: solid 1px #E6E6E6;
  box-sizing: border-box;
  font-size: 24px;
  padding: 2px 8px;
  transition: 0.15s;
`

export default Tag;
