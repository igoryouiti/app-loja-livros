import React from 'react'

import './CustomerRegister.css'
import { Route, Routes } from 'react-router-dom'

import UserRegister from '../../../components/client/user-register/UserRegister'
import CustomerRegisterComp from '../../../components/client/customer-register-comp/CustomerRegisterComp'

export default function CustomerRegister() {
  return (
    <>
      <Routes>
        <Route path="/" element={<UserRegister />}/>
        <Route path="/:userId/customer" element={<CustomerRegisterComp />} />
      </Routes>
    </>
  )
}
