import React, { Component } from 'react'
import './ChartSummary.css'
import { Link } from 'react-router-dom'
import Checkout from '../../../pages/client/checkout/Checkout'

export default class ChartSummary extends Component {
  render() {
    return (
      <>
        <div className="chart-summary-content">
          <div className="chart-items-list">
            <div className="chart-item">
              <img className="image-chart-item" src="https://lojasaraivanew.vtexassets.com/arquivos/ids/159014-1200-auto?v=638000884796000000&width=1200&height=auto&aspect=true" alt="" />
              <text className="name-chart-item">Harry Potter e o Cálice de Fogo</text>
              <text className="price-chart-item">RS99,99</text>
              <div className="quantity-chart-item">
                <button className="minus-quantity-chart-item">-</button>
                <input type="text" className="input-quantity-chart-item" placeholder="1" name="quantity" />
                <button className="plus-quantity-chart-item">+</button>
              </div>
              <text className="total-chart-item">RS99,99</text>
              <button className="btn-remove-chart-item">remover</button>
            </div>
          </div>
          <div className="chart-options">
            <div className="freight-chart-options">
              <form className="freight-calc-chart-option">
                <input className="cep-calc-chart-option" placeholder="08775-530" name="cep" />
                <button className="btn-calc-chart-option">calcular</button>
              </form>
              <div className="summary-freight-calc">
                <div className="estimated-time-chart-option">Até 3 dias úteis</div>
                <div className="price-freight-chart-option">R$11,49</div>
              </div>
            </div>
            <div className="summary-chart-option">
              <div className="summary-chart">
                <div className="sub-total">R$99,99</div>
                <div className="freight-price">R$11,49</div>
                <div className="promo-coupons-chart">
                  <input type="text" className="coupons-chart" placeholder="Cupom20" />
                  <button className="btn-plus-coupon">+</button>
                </div>
                <div className="coupon-value">R$20,00</div>
                <div className='trade-coupons-chart'>
                  <label htmlFor="trade-coupons">Cupom de Troca:</label>
                  <select id="trade-coupon" name="trade-coupon">
                    <option value=""></option>
                    <option value="20,00">R$20,00</option>
                    <option value="35,00">R$35,00</option>
                  </select>
                  <button className="btn-plus-coupon">+</button>
                </div>
                <div className="total-price-itens">Total: R$91,48</div>
              </div>
              <Link to="/checkout">
              <button className="btn-buy">Comprar</button>
              </Link>
            </div>
          </div>
        </div>
      </>
    )
  }
}
