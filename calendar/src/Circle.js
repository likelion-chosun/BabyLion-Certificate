
import React from 'react';

export default function App(){
    return(
        <div style={{
            position: 'absolute',
            top: 10,
            right: 10,
            width: '100vw', // 화면 전체 너비
            height: '100vh', // 화면 전체 높이
            margin: 0, // 기본 여백 제거
        }}>

            <div style={{
                width: '100px',
                height: '100px',
                backgroundColor: '#E8FFA7',
                borderRadius: '50%',
                textAlign: 'center',
                fontSize: '20px',
                position: 'absolute',
                top: '10px',
                right: '10px',
            }}>
                <button style={{
                    fontSize: '40px',
                    background: 'none',
                    border: 'none',
                    alignItems: 'center',
                    justifyContent: 'center',
                    padding: '25px',
                }}>+</button>
            </div>
        </div>
    );
}
