import React, { ChangeEvent, Component, useEffect, useState } from 'react'

import './UserRegister.css'
import { useNavigate } from 'react-router-dom';
import User from '../../../models/User';
import { userRegister } from '../../../services/service';

export default function UserRegister() {
  let navigate = useNavigate();

const [passwordConfirm, setPasswordConfirm] = useState<String>("");

const [user, setUser] = useState<User>({
  id: 0,
  email: "",
  password: "",
  password2: "",
});

const [userResult, setUserResult] = useState<User>({
  id: 0,
  email: "",
  password: "",
  password2: "",
});

useEffect(() => {
  if (userResult.id != 0) {
    navigate("/customer");
  }
}, [userResult]);

function handlePasswordConfirm(e: ChangeEvent<HTMLInputElement>) {
  setPasswordConfirm(e.target.value);
}

function updatedModel(e: ChangeEvent<HTMLInputElement>) {
  setUser({
    ...user,
    [e.target.name]: e.target.value,
  });
}

async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
  e.preventDefault()

  if (passwordConfirm === user.password && user.password.length >= 8) {
    try {
      await userRegister(`/users`, user, setUserResult)
    } catch (error) {
      console.log(`Error: ${error}`)
    }
    setUser({ ...user, password: "" })
    setPasswordConfirm("")
  }
}

  return (
    <>
        <form action="/cadastro/customer">
          <div className='login-info'>
            <h3>Informações de Login</h3>
            <label htmlFor="userEmail">Email: </label>
            <input type='email' id='userEmail' name='userEmail' />
            <div className='login-password'>
              <label htmlFor="userPassword">Senha: </label>
              <input type='password' id='userPassword' name='userPassword' />
              <label htmlFor="repeatPassword">Repetir senha: </label>
              <input type='password' id='repeatPassword' name='repeatPassword' />
            </div>
          </div>
          <button type='submit'>Cadastrar</button>
        </form>
      </>
  )
}
