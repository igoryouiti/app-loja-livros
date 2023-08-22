import React, { Component } from 'react';

import {Routes, Route } from 'react-router-dom';
import Home from './home/Home';
import Header from '../../components/client/header/Header';
import Footer from '../../components/client/footer/Footer';
import Products from './products/Products';
import Checkout from './checkout/Checkout';
import Chart from './chart/Chart';
import DashboardCli from './dashboard-cli/DashboardCli';

export default class Client extends Component {
  render() {
    return (
        <>
            <Header />
            <Routes> 
                <Route path="/" element={<Home />} />
                <Route path="/products/*" element={<Products />} />
                <Route path='/chart' element={<Chart />} />
                <Route path='/checkout' element={<Checkout />} />
                <Route path='/dashboard/*' element={<DashboardCli />} />
            </Routes>
            <Footer />
        </>
    )
  }
}