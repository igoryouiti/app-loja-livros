import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import Customer from '../../../models/Customer';
import { activateUser, findAllCustomers, inactivateUserNoData } from '../../../services/service';

export default function CustomersList() {

    const { customerId } = useParams()

    let navigate = useNavigate();


    const [customers, setCustomers] = useState<Customer[]>([])

    async function getCustomers() {
        await findAllCustomers(`/customers`, setCustomers, {});
    }


    useEffect(() => {
        getCustomers();
    }, [customerId]);

    async function handleInactivate(customer: Customer) {

        try {
            await inactivateUserNoData(`/users/inactive/${customer.user?.id}`)

            window.location.reload();

        } catch (error) {
            console.log(`Error: ${error}`)
        }

    }

    async function handleActivate(customer: Customer) {

        try {
            await activateUser(`/users/active/${customer.user?.id}`)

            window.location.reload();

        } catch (error) {
            console.log(`Error: ${error}`)
        }

    }


    return (
        <>
            <div className="customers-list-container">
                <h3>Lista de Clientes Cadastrados</h3>
                <ol className="customers-itens">
                    {
                        customers.map(customer => (
                            <li className="customer-item" key={customer.id}>
                                <h3><label htmlFor="id">{customer.id} </label></h3>
                                <label htmlFor='name'>
                                    {customer.name} -
                                </label>
                                <label htmlFor='email'> {customer.user?.email} - </label>
                                <label htmlFor='city'> {customer.address?.city} -</label>
                                <label htmlFor='status'> {customer.user?.active ? "Ativo" : "Desativado"} </label>
                                {customer.user?.active ? (
                                    <button onClick={() => handleInactivate(customer)}>Desativar</button>
                                ) : (
                                    <button onClick={() => handleActivate(customer)}>Ativar</button>
                                )}
                                <Link to={`${customer.id}`}>
                                    <button>Detalhar</button>
                                </Link>
                            </li>
                        ))
                    }
                </ol>
            </div>
        </>
    )
}
