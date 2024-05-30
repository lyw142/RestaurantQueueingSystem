import logo from '../../assets/MainLogo.png';
import React from 'react';
import './NavBar.css';

const NavBar = () => {

    return (
        <div className="header">
            <div className="logo-container">
                <img src={logo} alt="Main Logo"></img>
            </div>
            <div className="header-right">
                <p>DineLine</p>
                <p>Contact</p>
            </div>
        </div>
    );
};

export default NavBar;