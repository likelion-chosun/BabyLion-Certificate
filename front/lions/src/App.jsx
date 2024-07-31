import { useEffect, useState } from 'react';
import { Routes, Link, Route } from 'react-router-dom';
import InputPage from './InputPage';
import Recommend from './Recommend';
import Calendar from "./Calendar";


function App() {
  return (
    <Routes>
      <Route path='/' element={<InputPage />} />
      <Route path='/Recommend' element={<Recommend />} />
        <Route path='/Calendar' element={<Calendar />} />
    </Routes>
  )
}

export default App
