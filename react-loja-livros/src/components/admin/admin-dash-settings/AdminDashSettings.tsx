import React from 'react'
import { Link } from 'react-router-dom'

export default function AdminDashSettings() {
  return (
    <>
    <Link to="customers">Gerenciar Clientes</Link><br /><br />
    <Link to="/dashboard/transactions">Gerenciar Transações</Link><br /><br />
    <Link to="/dashboard/11/settings/shippings">Gerenciar Endereços de Entregas</Link><br /><br />
    <Link to="/dashboard/11/settings/credit-cards">Gerenciar Cartões de Créditos</Link><br /><br />
    <Link to="/dashboard/11/settings/password">Trocar Senha</Link><br /><br />
    <Link to="/dashboard/11/settings/inactivate">Inativar Conta</Link><br /><br />
</>
  )
}
