// import logo from './assets/MainLogo.png';
// import './App.css';
// import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import { ConfigProvider } from "antd";

// // import Login from './components/UserAuthentication/Login/Login';
// import NavBar from './components/NavBar/NavBar';

// const App = () => {
//   return (
//     <div className="App">
//       <NavBar />
//       {/* <Login /> */}
//     </div>
//   );
// };

// export default App;
import logo from './assets/MainLogo.png';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ConfigProvider } from "antd";

import Login from './components/UserAuthentication/Login/Login';
import NavBar from './components/NavBar/NavBar';

const App = () => {
  return (
    <Router>
      <Routes>
          <Route path="/navbar" element={<NavBar />} />
          <Route path="/login" element={<Login />} />
      </Routes>
    </Router>
  );
};

export default App;
