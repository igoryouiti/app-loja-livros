import React, { Component } from 'react';

import {Routes, Route } from 'react-router-dom';
import Home from './home/Home';
import Header from '../../components/client/header/Header';
import Footer from '../../components/client/footer/Footer';
import Product from './product/Product';

export default class Client extends Component {
  render() {
    return (
        <>
            <Header />
            <Routes> 
                <Route path="/" element={<Home />} />
                <Route path="/product" element={<Product />} />
            </Routes>
            <Footer />
        </>
    )
  }
}