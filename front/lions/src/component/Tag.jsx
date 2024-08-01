import { useState } from "react";
import styled from "styled-components";


function Tag(props){

  const [isAdd,setisAdd] = useState(false);
  function toggle(){
    setisAdd(!isAdd);
    const tmp = props.Toggle;
    tmp[props.i] = !tmp[props.i];
    props.setToggle(tmp);
    
    console.log(props.Toggle);
  }

    return(
        <Item isAdd={isAdd} onClick={toggle} >{props.children}</Item>
    )
}
const Item = styled.button`
  width: fit-content;
  background-color: ${props => props.isAdd ? '#efefef' : '#ffffff'};
  color: #8d939c;
  border-radius: 15px;
  border: solid 1px #E6E6E6;
  box-sizing: border-box;
  font-size: 24px;
  padding: 2px 8px;
  transition: 0.15s;
`

export default Tag;
