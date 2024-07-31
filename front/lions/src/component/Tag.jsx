import { useState } from "react";
import styled from "styled-components";


function Tag({children}){

  const [Added,setAdded] = useState(false);

    return(
        <Item added={Added} onClick={()=>{setAdded(!Added);}} >{children}</Item>
    )
}
const Item = styled.button`
  width: fit-content;
  background-color: ${props => props.added ? '#F4F4F5' : '#ffffff'};
  color: #8d939c;
  border-radius: 15px;
  border: solid 1px #E6E6E6;
  box-sizing: border-box;
  font-size: 24px;
  padding: 2px 8px;
  transition: 0.15s;
`

export default Tag;
