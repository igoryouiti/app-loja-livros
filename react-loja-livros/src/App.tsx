import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Client from './pages/client/Client';
import Product from './pages/client/products/Products';
import Admin from './pages/admin/Admin';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/*" element={<Client />} />
        <Route path="/admin/*" element={<Admin/>} />
      </Routes>
    </Router>
  );
}

export default App;
