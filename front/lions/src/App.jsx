import { useEffect, useState } from "react";
import { Routes, Link, Route } from "react-router-dom";
import InputPage from "./page/InputPage";
import Recommend from "./page/Recommend";
import ScheduleList from "./page/ScheduleList";
import Login from "./component/login/Login";

function App() {
  return (
    <Routes>
      <Route path="/" element={<InputPage />} />
      <Route path="/Recommend" element={<Recommend />} />
      <Route path="/Schedule" element={<ScheduleList />} />
      <Route path="/login" element={<Login />} />
    </Routes>
  );
}

export default App;
