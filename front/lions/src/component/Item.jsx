import styled from "styled-components";
import { Plus,Minus } from 'lucide-react';
import { useState } from "react";

function Item({name,desc,c,Toggle,setToggle,i}){

    const [isAdd,setisAdd] = useState(false);

    function toggle(){
        setisAdd(!isAdd);
        const tmp = Toggle;
        tmp[i] = !tmp[i];
        setToggle(tmp);
    
        console.log(Toggle); //디버깅용 출력
    }

    return (
        <Box col={c} bg={isAdd} onClick={toggle} >
                <div>
                    <Name>{name}</Name>
                    <Desc>{desc}</Desc>
                </div>
                <Add >{isAdd?<Minus />:<Plus />}</Add>
        </Box>
    )
}

const Box = styled.div`
    background-color: ${props => props.bg?'#ededed':props.col  };
    min-height: 110px;
    border-radius: 18px;
    display: flex;
    justify-content: space-between;
    padding: 20px;
    box-sizing: border-box;
    transition: 0.2s;
    /* box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24); */
`

const Name = styled.h4`
    font-size: 26px;
    font-weight: 500;
    margin: 0;
    color: #484848;
`
const Desc = styled.p`
    font-size: 14px;
    margin: 0;
    margin-top: 6px;
    color: #595959;
`

const Add = styled.button`
    width: 40px;
    height: 40px;
    background-color: white;
    margin: auto 0;
    border-radius: 100px;
    border: none;
    box-sizing: border-box;
    padding: 7px;
    transition: 0.1s;
`

export default Item;