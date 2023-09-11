import React from 'react'
import './DashboardCliSettings.css'
import { Link, Route, Routes } from 'react-router-dom'
import CustomerEdit from '../../../components/client/customer-edit/CustomerEdit'
import DashboardCliSettingsComp from '../../../components/client/dashboard-cli-settings-comp/DashboardCliSettingsComp'
import BillingList from '../../../components/client/billing-list/BillingList'
import BillingEdit from '../billing-edit/BillingEdit'



function DashboardCliSettings() {
    return (
        <>
            <Routes>
                <Route path="/" element={<DashboardCliSettingsComp />} />
                <Route path="/customer" element={<CustomerEdit />} />
                <Route path="/billings" element={<BillingList />} />
                <Route path="/billings/:billingAddressId" element={<BillingEdit />} />
                <Route path="/shippings" element={<DashboardCliSettings />} />
                <Route path="/credit-cards" element={<DashboardCliSettings />} />
                <Route path="/password" element={<DashboardCliSettings />} />
            </Routes>

        </>
    )
}

export default DashboardCliSettings

