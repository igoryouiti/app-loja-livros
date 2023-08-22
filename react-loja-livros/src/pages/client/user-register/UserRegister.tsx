import React, { Component } from 'react'

import './UserRegister.css'

export default class UserRegister extends Component {
  render() {
    return (
      <>
        <form action="">
            <div className='login-info'>
                <h3>Informações de Login</h3>
                <label htmlFor="userEmail">Email: </label>
                <input type='email' id='userEmail' name='userEmail'/>
                <div className='login-password'>
                    <label htmlFor="userPassword">Senha: </label>
                    <input type='password' id='userPassword' name='userPassword'/>
                    <label htmlFor="repeatPassword">Repetir senha: </label>
                    <input type='password' id='repeatPassword' name='repeatPassword'/>
                </div>
            </div>
            <div className="user-info">
                <h3>Informações de Usuario</h3>
                <label htmlFor="userName">Nome: </label>
                <input type='text' id='userName' name='userName'/>
                <label htmlFor="userGender">Genero: </label>
                <input type='text' id='userGender' name='userGender'/>
                <label htmlFor="userBirthday">Data Nascimento: </label>
                <input type='text' id='userBirthday' name='userBirthday' placeholder='dd/MM/aaa'/>
                <label htmlFor="userCPF">CPF: </label>
                <input type='text' id='userCPF' name='userCPF'/>
                <div className="telephone-info">
                    <label htmlFor="userTelephoneType">Tipo: </label>
                    <input type='text' id='userTelephoneType' name='userTelephoneType'/>
                    <label htmlFor="userTelephoneDDD">DDD: </label>
                    <input type='text' id='userTelephoneDDD' name='userTelephoneDDD'/>
                    <label htmlFor="userTelephone">Numero Telefone: </label>
                    <input type='text' id='userTelephone' name='userTelephone'/>
                </div>
            </div>
            <div className="adresses-info">
                <div className="address-info">
                    <h3>Endereço Residencial/Cobrança</h3>
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
                </div>
                <h3>Endereço de Entrega</h3>
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
            <div className="card-info">
                <h3>Cartão de Crédito</h3>
                <div>
                    <label htmlFor="holderName">Nome do portador: </label>
                    <input type='text' id='holderName' name='holderName'/>
                </div>
                <div>
                    <label htmlFor="cardNumber">Número do cartão: </label>
                    <input type='text' id='cardNumber' name='cardNumber'/>
                </div>
                <div>
                    <label htmlFor="expMonth">Mes da validade: </label>
                    <input type='number' id='expMonth' name='expMonth'/>
                </div>
                <div>
                    <label htmlFor="expYear">Ano da Validade: </label>
                    <input type='number' id='expYear' name='expYear'/>
                </div>
                <div>
                    <label htmlFor="cvv">CVV: </label>
                    <input type='password' id='cvv' name='cvv'/>
                </div>
                <div>
                    <label htmlFor="brand">Bandeira:</label>
                    <select id="brand" name="brand">
                        <option value="Mastercard">Mastercard</option>
                        <option value="Visa">Visa</option>
                        <option value="Elo">American Express</option>
                        <option value="Hipercard">Hipercard</option>
                        <option value="Diners">Diners</option>
                        <option value="Discovery">Discovery</option>
                        <option value="Aura">Aura</option>
                        <option value="JCB">JCB</option>
                    </select>
                </div>
            </div>
        </form>
      </>
    )
  }
}
