// export default Queue;
import React, { useState } from 'react';
import './Queue.css';
import logo from '../../assets/food.jpg';
import { enQueue, updateQueue } from '../../route';
import { ToastContainer, toast, Bounce } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import axios from 'axios';

const Queue = () => {
    const [selectedPax, setSelectedPax] = useState(2);
    const [phoneNum, setPhoneNum] = useState('');
    const [showPopup, setShowPopup] = useState(false);
    const [email, setEmail] = useState('');
    const [queueNo, setQueueNo] = useState('');
    const [waitingTime, setWaitingTime] = useState('');
    const [queueStatus, setQueueStatus] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        addQueue();
    };

    const handleSignUp = (e) => {
        e.preventDefault();
        console.log('Email:', email);
        // Add logic to handle the email sign-up
    };

    const closePopup = () => {
        setShowPopup(false);
    };

    const addQueue = async () => {
        try {
            const response = await axios.post(`${enQueue}`, {
                phoneNumber: phoneNum,
                restaurantID: 2,
                numOfPax: parseInt(selectedPax),
            });

            if (response.status !== 200) {
                toast.error('Error adding queue 1. Please try again', {
                    position: "bottom-right",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "light",
                    transition: Bounce
                });
                return;
            }

            console.log(response.data);
            setQueueNo(response.data['queueNo']);
            setWaitingTime(response.data['waitingTime']);
            setQueueStatus(response.data['status']);
            setShowPopup(true);

        } catch (err) {
            toast.error('Error adding queue 2. Please try again', {
                position: "bottom-right",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
                transition: Bounce
            });
        }
    };

    return (
        <div className="queue-container">
            <h2 className="queue-header">Register to Queue</h2>
            <form className="form-container" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label for="pax">Number of Pax:</label>
                    <input type="number" id="numOfPax" name="pax" className="input-field" value={selectedPax} onChange={(e) => setSelectedPax(e.target.value)} min="1" max="12" required></input>
                </div>
                <div className="form-group">
                    <label for="phoneNum">Phone Number:</label>
                    <input
                        type="tel"
                        className="input-field"
                        name="phoneNum"
                        placeholder="Enter phone number"
                        value={phoneNum}
                        onChange={(e) => setPhoneNum(e.target.value)}
                        pattern="[689][0-9]{7}"
                        title="Phone number should start with 6, 8, or 9 and be 8 digits long"
                        required
                    />
                </div>
                <button type="submit" className="submit-button">
                    Confirm to queue
                </button>
            </form>

            <div>
                <ToastContainer />
            </div>

            {showPopup && (
                <div className="popup">
                    <div className="popup-inner">
                        <div className="confirmation-text">
                            <h3>Thank You For Queueing!</h3>
                            <p>Below is the confirmed details and approximate waiting time for your queue</p>
                            <p>Queue Number: {queueNo}</p>
                            <p>Pax: {selectedPax}</p>
                            <p>Phone Number: {phoneNum}</p>
                            <p>Waiting Time: {waitingTime} minutes</p>
                            <p>Status : {queueStatus}</p>
                            <br></br>
                            <form onSubmit={handleSignUp} className="sign-up-form">
                                <h4>Sign Up Now!</h4>
                                <p>Receive promotions and exclusive offers delivered to your inbox</p>
                                <input
                                    type="email"
                                    className="input-field"
                                    placeholder="Enter your email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                                <button type="submit" className="confirm-submit-button">
                                    Sign Up
                                </button>
                            </form>
                            <br></br>
                            <button onClick={closePopup} className="close-button">Close</button>
                        </div>
                        <div className="image">
                            <img src={logo} alt="Food" />
                        </div>
                    </div>
                </div>
            )}

        </div>
    );
};

export default Queue;
