import React, { Component } from 'react'
import { Route, Routes } from 'react-router-dom'
import Transaction from '../../../components/admin/transactions/Transaction'
import AdminDetailedTransaction from '../../../components/admin/admin-detailed-transaction/AdminDetailedTransaction'


export default function DashboardAdmin() {
    return (
        <>
            <Routes>
                <Route path="/*" element={<Transaction />} />
                <Route path="/transactions/3*" element={<AdminDetailedTransaction />} />
            </Routes>
        </>
    )
}
