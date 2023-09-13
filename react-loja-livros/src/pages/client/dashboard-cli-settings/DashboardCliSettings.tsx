import React from 'react'
import './DashboardCliSettings.css'
import { Link, Route, Routes } from 'react-router-dom'
import CustomerEdit from '../../../components/client/customer-edit/CustomerEdit'
import DashboardCliSettingsComp from '../../../components/client/dashboard-cli-settings-comp/DashboardCliSettingsComp'
import BillingList from '../../../components/client/billing-list/BillingList'
import BillingEdit from '../../../components/client/billing-edit/BillingEdit'
import BillingCreate from '../../../components/client/billing-create/BillingCreate'
import ShippingList from '../../../components/client/shipping-list/ShippingList'
import ShippingEdit from '../../../components/client/shipping-edit/ShippingEdit'
import ShippingCreate from '../../../components/client/shipping-create/ShippingCreate'
import CreditCardEdit from '../../../components/client/credit-card-edit/CreditCardEdit'
import CreditCardCreate from '../../../components/client/credit-card-create/CreditCardCreate'
import CreditCardList from '../../../components/client/credit-card-list/CreditCardList'




function DashboardCliSettings() {
    return (
        <>
            <Routes>
                <Route path="/" element={<DashboardCliSettingsComp />} />
                <Route path="/customer" element={<CustomerEdit />} />
                <Route path="/billings" element={<BillingList />} />
                <Route path="/billings/:billingAddressId" element={<BillingEdit />} />
                <Route path="/billings/create" element={<BillingCreate />} />
                <Route path="/shippings" element={<ShippingList />} />
                <Route path="/shippings/:shippingAddressId" element={<ShippingEdit />} />
                <Route path="/shippings/create" element={<ShippingCreate />} />
                <Route path="/credit-cards" element={<CreditCardList />} />
                <Route path="/credit-cards/:creditCardId" element={<CreditCardEdit />} />
                <Route path="/credit-cards/create" element={<CreditCardCreate />} />
                <Route path="/password" element={<DashboardCliSettings />} />
            </Routes>

        </>
    )
}

export default DashboardCliSettings

