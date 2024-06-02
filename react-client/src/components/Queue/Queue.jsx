// import React, { useState } from 'react';
// import './Queue.css';

// const Queue = () => {
//     const [selectedPax, setSelectedPax] = useState('');
//     const [phoneNumber, setPhoneNumber] = useState('');

//     const handlePaxSelection = (pax) => {
//         setSelectedPax(pax);
//     };

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         console.log('Pax:', selectedPax);
//         console.log('Phone Number:', phoneNumber);
//         // You can add further logic to handle the form submission
//     };

//     return (
//         <div className="queue-container">
//             <h2 className="queue-header">Please Queue Now</h2>
//             <form onSubmit={handleSubmit}>
//                 <div className="button-container">
//                     <button
//                         type="button"
//                         className={`queue-button ${selectedPax === '1-2' ? 'selected' : ''}`}
//                         onClick={() => handlePaxSelection('1-2')}
//                     >
//                         1-2 pax
//                     </button>
//                     <button
//                         type="button"
//                         className={`queue-button ${selectedPax === '3-4' ? 'selected' : ''}`}
//                         onClick={() => handlePaxSelection('3-4')}
//                     >
//                         3-4 pax
//                     </button>
//                     <button
//                         type="button"
//                         className={`queue-button ${selectedPax === '5-6' ? 'selected' : ''}`}
//                         onClick={() => handlePaxSelection('5-6')}
//                     >
//                         5-6 pax
//                     </button>
//                     <button
//                         type="button"
//                         className={`queue-button ${selectedPax === '>6' ? 'selected' : ''}`}
//                         onClick={() => handlePaxSelection('>6')}
//                     >
//                         &gt;6 pax
//                     </button>
//                 </div>
//                 <div className="form-group">
//                     Phone Number: <input
//                         type="tel"
//                         className="input-field"
//                         placeholder="Enter phone number"
//                         value={phoneNumber}
//                         onChange={(e) => setPhoneNumber(e.target.value)}
//                         required
//                     />
//                     <button type="submit" className="submit-button">
//                         Confirm to queue
//                     </button>
//                 </div>
//             </form>
//         </div>
//     );
// };

// export default Queue;
import React, { useState } from 'react';
import './Queue.css';
import logo from '../../assets/food.jpg';

const Queue = () => {
    const [selectedPax, setSelectedPax] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [showPopup, setShowPopup] = useState(false);
    const [email, setEmail] = useState('');
    const [waitingTime, setWaitingTime] = useState(15); // Set a default waiting time

    const handlePaxSelection = (pax) => {
        setSelectedPax(pax);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Pax:', selectedPax);
        console.log('Phone Number:', phoneNumber);
        // You can add further logic to handle the form submission
        setShowPopup(true);
    };

    const handleSignUp = (e) => {
        e.preventDefault();
        console.log('Email:', email);
        // Add logic to handle the email sign-up
    };

    const closePopup = () => {
        setShowPopup(false);
    };

    return (
        <div className="queue-container">
            <h2 className="queue-header">Please Queue Now</h2>
            <form className="form-container" onSubmit={handleSubmit}>
                <div className="button-container">
                    <button
                        type="button"
                        className={`queue-button ${selectedPax === '1-2' ? 'selected' : ''}`}
                        onClick={() => handlePaxSelection('1-2')}
                    >
                        1-2 pax
                    </button>
                    <button
                        type="button"
                        className={`queue-button ${selectedPax === '3-4' ? 'selected' : ''}`}
                        onClick={() => handlePaxSelection('3-4')}
                    >
                        3-4 pax
                    </button>
                    <button
                        type="button"
                        className={`queue-button ${selectedPax === '5-6' ? 'selected' : ''}`}
                        onClick={() => handlePaxSelection('5-6')}
                    >
                        5-6 pax
                    </button>
                    <button
                        type="button"
                        className={`queue-button ${selectedPax === '>6' ? 'selected' : ''}`}
                        onClick={() => handlePaxSelection('>6')}
                    >
                        &gt;6 pax
                    </button>
                </div>
                <div className="form-group">
                    Phone Number: 
                    <input
                        type="tel"
                        className="input-field"
                        placeholder="Enter phone number"
                        value={phoneNumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                        pattern="[689][0-9]{7}"
                        title="Phone number should start with 6, 8, or 9 and be 8 digits long"
                        required
                    />
                </div>
                <button type="submit" className="submit-button">
                    Confirm to queue
                </button>
            </form>

            {showPopup && (
                <div className="popup">
                    <div className="popup-inner">
                        <div className="confirmation-text">
                            <h3>Thank You For Queueing!</h3>
                            <p>Below is the confirmed details and approximate waiting time for your queue</p>
                            <p>Pax: {selectedPax}</p>
                            <p>Phone Number: {phoneNumber}</p>
                            <p>Waiting Time: {waitingTime} minutes</p>
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
