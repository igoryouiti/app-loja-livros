import React, { Component } from 'react'

import './DashboardCli.css'
import DashboardCliComp from '../../../components/client/dashboard-cli-comp/DashboardCliComp'
import { Route, Routes } from 'react-router-dom'
import DetailedTransaction from '../../../components/client/detailed-transaction/DetailedTransaction'
import DashboardCliSettings from '../dashboard-cli-settings/DashboardCliSettings'
import CustomerEdit from '../../../components/client/customer-edit/CustomerEdit'


export default function DashboardCli() {
  return (
    <Routes>
      <Route path='/' element={<DashboardCliComp />} />
      <Route path="/transactions/3" element={<DetailedTransaction />} />
      <Route path="/:customerId/settings/*" element={<DashboardCliSettings />} />
    </Routes>
  )
}
