import React, { Component } from 'react'
import { Link } from 'react-router-dom'

export default class Transaction extends Component {
    render() {
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
                                <div className="status">
                                    <label htmlFor="status"/>
                                    <select id="status" name="status">
                                        <option value="1">EM PROCESSAMENTO</option>
                                        <option value="2">EM TRANSITO</option>
                                        <option value="3">ENTREGUE</option>
                                        <option value="4">EM TROCA</option>
                                        <option value="5">TROCA AUTORIZADA</option>
                                    </select>
                                </div>
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
}
