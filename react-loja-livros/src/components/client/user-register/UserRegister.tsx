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
        </form>
      </>
    )
  }
}

