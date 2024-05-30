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

const Queue = () => {
    const [selectedPax, setSelectedPax] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');

    const handlePaxSelection = (pax) => {
        setSelectedPax(pax);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Pax:', selectedPax);
        console.log('Phone Number:', phoneNumber);
        // You can add further logic to handle the form submission
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
                        required
                    />
                </div>
                <button type="submit" className="submit-button">
                    Confirm to queue
                </button>
            </form>
        </div>
    );
};

export default Queue;
