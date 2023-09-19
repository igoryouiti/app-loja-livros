import React, { ChangeEvent, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import User from '../../../models/User';
import { findUserByCustomerId, updateUser } from '../../../services/service';
import PasswordValidator from '../../../utils/validators/PasswordValidator';

export default function PasswordEdit() {

    const { customerId } = useParams()

    let navigate = useNavigate()

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

    async function getUserByCustomerId() {
        await findUserByCustomerId(`/users/customer/${customerId}`, setUser, {});
    }


    useEffect(() => {
        getUserByCustomerId();
    }, [customerId]);


    function updatedModel(e: ChangeEvent<HTMLInputElement>) {
        setUser({
            ...user,
            [e.target.name]: e.target.value,
        });
    }

    async function updatePassword(e: ChangeEvent<HTMLFormElement>) {

        e.preventDefault()

        if (PasswordValidator(user.password, user.password2)) {
            console.log("Estoy en el validacao correct")
            try {
                await updateUser(`/users/password`, user, setUserResult)

                navigate("../")

            } catch (error) {
                console.log(`Error: ${error}`)
            }
            
            setUser({ ...user, password: "" })
            setUser({ ...user, password2: "" })

            setPasswordConfirm("")
        }
    }

    function goBack(){
        navigate("../")
    }

    return (
        <>
            <button onClick={goBack}>Voltar</button>
            <form onSubmit={updatePassword}>
                <div className='login-info'>
                    <h3>Atualizar Senha</h3>
                    <label htmlFor="email">Email: {user.email}</label>
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
                <button type='submit'>Redefinir Senha</button>
            </form>
        </>
    )
}
