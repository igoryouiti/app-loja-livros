import React, { ChangeEvent, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import User from '../../../models/User';
import { findUserByCustomerId, inactivateUser } from '../../../services/service';

export default function AccountInactivate() {

  const { customerId } = useParams()

  let navigate = useNavigate()

  const [user, setUser] = useState<User>({
    id: 0,
    active: true,
    email: "",
    password: "",
    password2: "",
    customer: null
  });

  const [userResult, setUserResult] = useState<User>({
    id: 0,
    active: true,
    email: "",
    password: "",
    password2: "",
    customer: null
  });

  async function getUserByCustomerId() {
    await findUserByCustomerId(`/users/customer/${customerId}`, setUser, {});
  }


  useEffect(() => {
    getUserByCustomerId();
  }, [customerId]);

  async function inactivateAccount() {

    try {
      await inactivateUser(`/users/inactive/${user.id}`, setUserResult)

      navigate("../")

    } catch (error) {
      console.log(`Error: ${error}`)
    }

  }

  async function returnPage() {
    navigate("../")
  }


  return (
    <>
      <h3>Desativar conta?</h3>
      <button onClick={inactivateAccount}>Desativar conta</button>
      <button onClick={returnPage}>Voltar</button>
    </>
  )
}
