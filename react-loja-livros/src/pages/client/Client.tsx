import React, { Component } from 'react';

import {Routes, Route } from 'react-router-dom';
import Home from './home/Home';
import Header from '../../components/client/header/Header';
import Footer from '../../components/client/footer/Footer';
import Products from './products/Products';

export default class Client extends Component {
  render() {
    return (
        <>
            <Header />
            <Routes> 
                <Route path="/" element={<Home />} />
                <Route path="/products/*" element={<Products />} />
            </Routes>
            <Footer />
        </>
    )
  }
}