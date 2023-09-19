import React from 'react'

import './DashboardCliSettingsComp.css'
import { Link, useParams } from 'react-router-dom'

export default function DashboardCliSettingsComp() {

    const { customerId } = useParams()

    return (
        <>
            <Link to={`/dashboard/${customerId}/settings/customer`}>Gerenciar Dados Cadastrais</Link><br /><br />
            <Link to={`/dashboard/${customerId}/settings/billings`}>Gerenciar Endereços de Cobranças</Link><br /><br />
            <Link to={`/dashboard/${customerId}/settings/shippings`}>Gerenciar Endereços de Entregas</Link><br /><br />
            <Link to={`/dashboard/${customerId}/settings/credit-cards`}>Gerenciar Cartões de Créditos</Link><br /><br />
            <Link to={`/dashboard/${customerId}/settings/password`}>Trocar Senha</Link><br /><br />
            <Link to={`/dashboard/${customerId}/settings/inactivate`}>Inativar Conta</Link><br /><br />
        </>
    )
}
