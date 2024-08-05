import { useEffect, useState } from 'react';
import { Routes, Link, Route } from 'react-router-dom';
import InputPage from './page/InputPage';
import Recommend from './page/Recommend';
import ScheduleList from './page/ScheduleList';
import Login from './page/Login';
import Redirection from './page/Redirection';

function App() {

  const [R,setR] = useState([]);

  return (
    <Routes>
      <Route path='/login/oauth2/code/kakao' element={<Redirection />}  />
      <Route path='/L' element={<Login />}  />

      <Route path='/' element={<InputPage R={R} setR={setR} />} />
      <Route path='/Recommend' element={<Recommend R={R} setR={setR} />} />
      <Route path='/Schedule' element={<ScheduleList />}/>
    </Routes>
  )
}

export default App
