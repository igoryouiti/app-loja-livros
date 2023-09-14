import React, { Component } from 'react'
import { Route, Routes } from 'react-router-dom'
import Transaction from '../../../components/admin/transactions/Transaction'
import AdminDetailedTransaction from '../../../components/admin/admin-detailed-transaction/AdminDetailedTransaction'
import AdminDashSettings from '../../../components/admin/admin-dash-settings/AdminDashSettings'
import CustomersList from '../../../components/admin/customers-list/CustomersList'
import CustomerDetails from '../../../components/admin/customer-details/CustomerDetails'


export default function DashboardAdmin() {
    return (
        <>
            <Routes>
                <Route path="/*" element={<AdminDashSettings />} />
                <Route path="/customers" element={<CustomersList />} />
                <Route path="/customers/:customerId" element={<CustomerDetails />} />
                <Route path="/transactions/*" element={<Transaction />} />
                <Route path="/transactions/3*" element={<AdminDetailedTransaction />} />
            </Routes>
        </>
    )
}
