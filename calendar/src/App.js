import logo from './logo.svg';
import './App.css';
import React, { useState } from 'react'
import Circle from './Circle';

function App() {
  const [timer, setTimer] = useState("00:00:00");

  const currentTimer = () => {
    const date = new Date();
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0");
    setTimer(`${hours}:${minutes}:${seconds}`)
  }

  const startTimer = () => {
    setInterval(currentTimer, 1000)
  }

  startTimer()

  return (
    <>
      <h1 className='h1'
      >{timer}</h1>
      <hr style={{marginTop: "100px"}}/> 

      <Circle></Circle>
      
    </>
  )
}

export default App;
