import React, { Component } from 'react'

import './DashboardCli.css'
import DashboardCliComp from '../../../components/client/dashboard-cli-comp/DashboardCliComp'
import { Route, Routes } from 'react-router-dom'
import DetailedTransaction from '../../../components/client/detailed-transaction/DetailedTransaction'


export default function DashboardCli() {
  return (
    <Routes>
      <Route path='/' element={<DashboardCliComp />} />
      <Route path="/transactions/3" element={<DetailedTransaction />} />
    </Routes>
  )
}
