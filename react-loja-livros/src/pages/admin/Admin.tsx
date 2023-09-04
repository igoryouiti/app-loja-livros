import React, { Component } from 'react'
import { Route, Routes } from 'react-router-dom'
import Header from '../../components/client/header/Header'
import Footer from '../../components/client/footer/Footer'
import DashboardAdmin from './dashboard-admin/DashboardAdmin'


export default function Admin() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/*" element={<DashboardAdmin />} />
      </Routes>
      <Footer />
    </>
  )
}
