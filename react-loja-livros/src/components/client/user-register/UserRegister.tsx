import React, { ChangeEvent, useEffect, useState } from 'react'

import './UserRegister.css'
import { useNavigate } from 'react-router-dom';
import User from '../../../models/User';
import { postUser } from '../../../services/service';
import PasswordValidator from '../../../utils/validators/PasswordValidator';

export default function UserRegister() {

  let navigate = useNavigate();

  const [passwordConfirm, setPasswordConfirm] = useState<String>("");

  const [user, setUser] = useState<User>({
    id: 0,
    email: "",
    password: "",
    password2: "",
    customer: null
  });

  const [userResult, setUserResult] = useState<User>({
    id: 0,
    email: "",
    password: "",
    password2: "",
    customer: null
  });

  useEffect(() => {
    if (userResult.id !== 0) {
      navigate(`/cadastro/cliente/${userResult.id}`);
    }
  }, [userResult]);


  function updatedModel(e: ChangeEvent<HTMLInputElement>) {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  }

  async function userRegister(e: ChangeEvent<HTMLFormElement>) {

    e.preventDefault()

    if (PasswordValidator(user.password, user.password2)) {

      try {
        await postUser(`/users`, user, setUserResult)

        console.log(user.id)

      } catch (error) {
        console.log(`Error: ${error}`)
      }
      setUser({ ...user, password: "" })
      setUser({ ...user, password2: "" })

      setPasswordConfirm("")
    }
  }

  return (
    <>
      <form onSubmit={userRegister}>
        <div className='login-info'>
          <h3>Informações de Login</h3>
          <label htmlFor="email">Email: </label>
          <input
            onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)}
            type='email' id='email' name='email'
          />
          <div className='login-password'>
            <label htmlFor="password">Senha: </label>
            <input
              onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)}
              type='password' id='password' name='password'
            />
            <label htmlFor="password2">Repetir senha: </label>
            <input
              onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)}
              type='password' id='password2' name='password2'
            />
          </div>
        </div>
        <button type='submit'>Cadastrar</button>
      </form>
    </>
  )
}
