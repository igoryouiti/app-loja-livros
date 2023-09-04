import React, { Component } from 'react'

import './DashboardCliComp.css'
import { Link } from 'react-router-dom'


export default function DashboardCliComp() {
  return (
    <>
      <div className="dashboard-container">
        <div className="transaction-list-container">
          <div className="header-list">
            <div>ID Compra</div>
            <div>Valor</div>
            <div>Data</div>
            <div>Status</div>
          </div>
          <ol>
            <li className='item-list'>
              <div>03</div>
              <div>R$91,48</div>
              <div>22/08/2023</div>
              <div>Aprovado</div>
              <Link to='transactions/3'>
                <button>Detalhar</button>
              </Link>
            </li>
          </ol>
        </div>
      </div>
    </>
  )
}
