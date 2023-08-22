import React, { Component } from 'react'
import './CheckoutComp.css'
import { Link } from 'react-router-dom'

export default class CheckoutComp extends Component {
  render() {
    return (
      <>
        <div className="checkout-container">
          <div className="checkout-billing">
            <div className="checkout-billing-main">
              <h2>Endereço de Cobrança</h2>
              <div>
                <div>Tipo Residência: </div>
                <div>Apartamento</div>
              </div>
              <div>
                <div>Tipo Logradouro: </div>
                <div>Privado</div>
              </div>
              <div>
                <div>Logradouro: </div>
                <div>Rua Azelino da Cunha Glória, bloco C, apartamento 14</div>
              </div>
              <div>
                <div>Numero: </div>
                <div>358</div>
              </div>
              <div>
                <div>Bairro: </div>
                <div>Jardim Maricá</div>
              </div>
              <div>
                <div>CEP: </div>
                <div>08775-531</div>
              </div>
              <div>
                <div>Cidade: </div>
                <div>Mogi das Cruzes</div>
              </div>
              <div>
                <div>Estado: </div>
                <div>SP</div>
              </div>
              <div>
                <div>País: </div>
                <div>Brasil</div>
              </div>
              <div>
                <div>Observação: </div>
                <div>Minha casa</div>
              </div>
            </div>
            <div className="checkout-billing-new">
              <div>
                <label htmlFor="typeResidence">Tipo Residência: </label>
                <input type='text' id='typeResidence' name='typeResidence'/>
              </div>
              <div>
                <label htmlFor="typePublicPlace">Tipo Logradouro: </label>
                <input type='text' id='typePublicPlace' name='typePublicPlace'/>
              </div>
              <div>
                <label htmlFor="publicPlace">Logradouro: </label>
                <input type='text' id='publicPlace' name='publicPlace'/>
              </div>
              <div>
                <label htmlFor="number">Número: </label>
                <input type='text' id='number' name='number'/>
              </div>
              <div>
                <label htmlFor="neighbor">Bairro: </label>
                <input type='text' id='neighbor' name='neighbor'/>
              </div>
              <div>
                <label htmlFor="postalCode">CEP: </label>
                <input type='text' id='postalCode' name='postalCode'/>
              </div>
              <div>
                <label htmlFor="city">Cidade: </label>
                <input type='text' id='city' name='city'/>
              </div>
              <div>
                <label htmlFor="state">Estado: </label>
                <input type='text' id='state' name='state'/>
              </div>
              <div>
                <label htmlFor="country">País: </label>
                <input type='text' id='country' name='country'/>
              </div>
              <div>
                <label htmlFor="observation">Observação: </label>
                <input type='text' id='observation' name='observation'/>
              </div>
            </div>
          </div>
          <div className="checkout-shipping">
            <div className="checkout-shipping-main">
              <h2>Endereço de Entrega</h2>
            <div>
                <div>Tipo Residência: </div>
                <div>Apartamento</div>
              </div>
              <div>
                <div>Tipo Logradouro: </div>
                <div>Privado</div>
              </div>
              <div>
                <div>Logradouro: </div>
                <div>Rua Azelino da Cunha Glória, bloco C, apartamento 14</div>
              </div>
              <div>
                <div>Numero: </div>
                <div>358</div>
              </div>
              <div>
                <div>Bairro: </div>
                <div>Jardim Maricá</div>
              </div>
              <div>
                <div>CEP: </div>
                <div>08775-531</div>
              </div>
              <div>
                <div>Cidade: </div>
                <div>Mogi das Cruzes</div>
              </div>
              <div>
                <div>Estado: </div>
                <div>SP</div>
              </div>
              <div>
                <div>País: </div>
                <div>Brasil</div>
              </div>
              <div>
                <div>Observação: </div>
                <div>Minha casa</div>
              </div>
            </div>
            <div className="checkout-shipping-new">
            <div>
                <label htmlFor="typeResidence">Tipo Residência: </label>
                <input type='text' id='typeResidence' name='typeResidence'/>
              </div>
              <div>
                <label htmlFor="typePublicPlace">Tipo Logradouro: </label>
                <input type='text' id='typePublicPlace' name='typePublicPlace'/>
              </div>
              <div>
                <label htmlFor="publicPlace">Logradouro: </label>
                <input type='text' id='publicPlace' name='publicPlace'/>
              </div>
              <div>
                <label htmlFor="number">Número: </label>
                <input type='text' id='number' name='number'/>
              </div>
              <div>
                <label htmlFor="neighbor">Bairro: </label>
                <input type='text' id='neighbor' name='neighbor'/>
              </div>
              <div>
                <label htmlFor="postalCode">CEP: </label>
                <input type='text' id='postalCode' name='postalCode'/>
              </div>
              <div>
                <label htmlFor="city">Cidade: </label>
                <input type='text' id='city' name='city'/>
              </div>
              <div>
                <label htmlFor="state">Estado: </label>
                <input type='text' id='state' name='state'/>
              </div>
              <div>
                <label htmlFor="country">País: </label>
                <input type='text' id='country' name='country'/>
              </div>
              <div>
                <label htmlFor="observation">Observação: </label>
                <input type='text' id='observation' name='observation'/>
              </div>
            </div>
            <div className="checkout-freight">
            </div>
          </div>
          <div className="checkout-payment-method">
            <div className="checkout-coupons">
              <div className='promo-coupom-checkout'>
                <label htmlFor="promoCoupom">Cupom Promocional: </label>
                <input type='text' id='promoCoupom' name='promoCoupom' placeholder='Cupom20'/>
              </div>
              <div className='trade-coupons-checkout'>
                  <label htmlFor="trade-coupons">Cupom de Troca:</label>
                  <select id="trade-coupon" name="trade-coupon">
                    <option value=""></option>
                    <option value="20,00">R$20,00</option>
                    <option value="35,00">R$35,00</option>
                  </select>
                  <button className="btn-plus-coupon">+</button>
                </div>
            </div>
            <div className="one-card">
              <div className="checkout-credit-card">
                <div className="saved-credit-card">
                  <label htmlFor="savedCard">Cartão de Credito:</label>
                  <select id="savedCard" name="savedCard">
                    <option value="1">Nubank</option>
                    <option value="2">Itaú</option>
                  </select>
                <div className="saved-data-card">
                  <div>Numero: ####2541#####</div>
                  <div>Vencimento: 12/32</div>
                  <div>Bandeira: Mastercard</div>
                </div>
                </div>
                <div className="new-credit-card"> Novo cartao
                  <div>
                    <label htmlFor="numberCard">Número Cartão</label>
                    <input type='text' id='numberCard' name='numberCard'/>
                  </div>
                  <div>
                    <label htmlFor="nameCard">Nome do titular: </label>
                    <input type='text' id='nameCard' name='nameCard'/>
                  </div>
                  <div>
                    <label htmlFor="cvvCard">CVV: </label>
                    <input type='text' id='cvvCard' name='cvvCard'/>
                  </div>
                  <div>
                    <label htmlFor="expiredDate">Data vencimento (mes/ano): </label>
                    <input type='text' id='expiredDate' name='expiredDate'/>
                  </div>
                </div>
              </div>
              <div className="installments">
                <label htmlFor="installments">Parcelas:</label>
                <select id="installments" name="installments">
                  <option value="1">1x</option>
                  <option value="2">2x</option>
                  <option value="3">3x</option>
                </select>
              </div>
            </div>
            <div className="more-cards">
              <div className="checkout-credit-card">
                <div className="saved-credit-card">Cartao salvo</div>
                <div className="new-credit-card">Cartao novo</div>
              </div>
              <div className="amount">Valor a ser pago no cartao</div>
              <div className="installments">Parcelas</div>
            </div>
          </div>
          <div className="checkout-summary">
            <div className="chart-itens-subtotal">99.99</div>
            <div className="freight-amount">+ 11,49</div>
            <div className="coupons-subtotal">- 20,00</div>
            <div className="total">91,48</div>
            <div className="credit-cards">
              <div className="amount-total">91,48</div>
              <div className="installments">1 x 91,48</div>
            </div>
            <Link to="/dashboard">
               <button className="btn-buy">Comprar</button>
            </Link>
          </div>
        </div>
      </>
    )
  }
}
