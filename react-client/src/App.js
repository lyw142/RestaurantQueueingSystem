import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ConfigProvider } from "antd";

import Login from './components/UserAuthentication/Login/Login';
import NavBar from './components/NavBar/NavBar';
import Queue from './components/Queue/Queue';

const App = () => {
  return (
    <Router>
      <Routes>
          <Route path="/navbar" element={<NavBar />} />
          <Route path="/login" element={<Login />} />
          <Route path="/queue" element={<Queue/>} />
      </Routes>
    </Router>
  );
};

export default App;
