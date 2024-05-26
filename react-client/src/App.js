import logo from './assets/MainLogo.png';
import './App.css';
import Login from './components/UserAuthentication/Login/Login';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ConfigProvider } from "antd";

const App = () => {
  return (
    <div className="App">
      <Login />
    </div>
  );
};

export default App;
