import React from 'react'

import './DashboardCliSettingsComp.css'
import { Link } from 'react-router-dom'

export default function DashboardCliSettingsComp() {
    return (
        <>
            <Link to="/dashboard/11/settings/customer">Gerenciar Dados Cadastrais</Link><br /><br />
            <Link to="/dashboard/11/settings/billings">Gerenciar Endereços de Cobranças</Link><br /><br />
            <Link to="/dashboard/11/settings/shippings">Gerenciar Endereços de Entregas</Link><br /><br />
            <Link to="/dashboard/11/settings/credit-cards">Gerenciar Cartões de Créditos</Link><br /><br />
            <Link to="/dashboard/11/settings/password">Trocar Senha</Link><br /><br />
        </>
    )
}
