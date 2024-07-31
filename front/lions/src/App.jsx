import { useEffect, useState } from 'react';
import { Routes, Link, Route } from 'react-router-dom';
import InputPage from './page/InputPage';
import Recommend from './page/Recommend';
import ScheduleList from './page/ScheduleList';

function App() {
  return (
    <Routes>
      <Route path='/' element={<InputPage />} />
      <Route path='/Recommend' element={<Recommend />} />
      <Route path='/Schedule' element={<ScheduleList />}/>
    </Routes>
  )
}

export default App
