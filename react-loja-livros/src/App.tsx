import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Client from './pages/client/Client';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/home" element={<Client />} />
      </Routes>
    </Router>
  );
}

export default App;
