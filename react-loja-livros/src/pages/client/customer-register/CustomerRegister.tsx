import React from 'react'

import './CustomerRegister.css'
import { Route, Routes } from 'react-router-dom'

import CustomerRegisterComp from '../../../components/client/customer-register-comp/CustomerRegisterComp'
import UserRegister from '../../../components/client/user-register/UserRegister'

export default function CustomerRegister() {
  return (
    <>
      <Routes>
        <Route path="/" element={<UserRegister />}/>
        <Route path="/customer" element={<CustomerRegisterComp />} />
      </Routes>
    </>
  )
}
