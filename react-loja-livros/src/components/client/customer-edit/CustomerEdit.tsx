import React, { ChangeEvent, useEffect, useState } from 'react'
import { findById, updateCustomer } from '../../../services/service'
import Telephone from '../../../models/Telephone'
import Address from '../../../models/Address'
import CreateCustomerDTO from '../../../models/DTO/CreateCustomerDTO'
import { useNavigate, useParams } from 'react-router-dom'
import EditCustomerDTO from '../../../models/DTO/EditCustomerDTO'

export default function CustomerEdit() {

    let navigate = useNavigate()

    const { customerId } = useParams()

    const [customer, setCustomer] = useState<EditCustomerDTO>({
        id: 0,
        name: "",
        gender: "",
        birthday: "",
        cpf: "",
        telephone: {
            id: 0,
            type: "",
            number: "",
            ddd: ""
        },
        address: {
            id: 0,
            typeResidence: "",
            typePublicPlace: "",
            publicPlace: "",
            number: "",
            neighborhood: "",
            cep: "",
            city: "",
            state: '',
            country: "",
            observation: ''
        }
    });

    const [customerResult, setResultCustomer] = useState<CreateCustomerDTO>({
        id: 0,
        name: "",
        gender: "",
        birthday: "",
        cpf: "",
        telephone: null,
        address: null
    })

    async function getCustomerById() {
        await findById(`customers/${customerId}`, setCustomer, {});
    }
 
    useEffect(() => {
        getCustomerById();
    }, [customerId]);

    // useEffect(() => {
    //     if(customer.telephone)
    //         setTelephone(customer.telephone);

    //     console.log("AUA")
    // }, [customerId]);

    function handleCustomerChange(e: ChangeEvent<HTMLInputElement>) {
        setCustomer({
            ...customer,
            [e.target.name]: e.target.value
        });
    }

    function handleTelephoneChange(e: ChangeEvent<HTMLInputElement>) {
        setCustomer({
            ...customer,
            telephone: {
                ...customer.telephone,
                [e.target.name]: e.target.value
            }
        });
    }

    function handleAddressChange(e: ChangeEvent<HTMLInputElement>) {
        setCustomer({
            ...customer,
            address: {
                ...customer.address,
                [e.target.name]: e.target.value
            }
        });
    }


    async function customerUpdate(e: ChangeEvent<HTMLFormElement>) {

        e.preventDefault()

        try {
            await updateCustomer(`/customers`, customer, setResultCustomer)

        } catch (error) {
            console.log(`Error: ${error}`)
        }


        try {
            await updateCustomer(`/customers/address`, customer.address, setResultCustomer)

        } catch (error) {
            console.log(`Error: ${error}`)
        }


        try {
            await updateCustomer(`/customers/telephone`, customer.telephone, setResultCustomer)

        } catch (error) {
            console.log(`Error: ${error}`)
        }
    }



    return (
        <>
            <form onSubmit={customerUpdate}>
                <div className="user-info">
                    <h3>Informações de Usuario</h3>
                    <label htmlFor="name">Nome: </label>
                    <input type='text' id='name' name='name'
                        onChange={(e: ChangeEvent<HTMLInputElement>) => handleCustomerChange(e)}
                        required
                        autoComplete="off"
                        value={customer.name}
                    />
                    <label htmlFor="gender">Genero: </label>
                    <input type='text' id='gender' name='gender'
                        onChange={(e: ChangeEvent<HTMLInputElement>) => handleCustomerChange(e)}
                        required
                        value={customer.gender}
                    />
                    <label htmlFor="birthday">Data Nascimento: </label>
                    <input type='text' id='birthday' name='birthday'
                        onChange={(e: ChangeEvent<HTMLInputElement>) => handleCustomerChange(e)}
                        required
                        placeholder='aaaa-MM-dd'
                        value={customer.birthday}
                    />
                    <label htmlFor="cpf">CPF: </label>
                    <input type='text' id='cpf' name='cpf'
                        onChange={(e: ChangeEvent<HTMLInputElement>) => handleCustomerChange(e)}
                        required
                        value={customer.cpf}
                    />
                    <div className="telephone-info">
                        
                        <label htmlFor="type">Tipo: </label>
                        <input type='text' id='type' name='type'
                            onChange={(e: ChangeEvent<HTMLInputElement>) => handleTelephoneChange(e)}
                            required
                            value={customer.telephone?.type}
                        />
                        <label htmlFor="ddd">DDD: </label>
                        <input type='text' id='ddd' name='ddd'
                            onChange={(e: ChangeEvent<HTMLInputElement>) => handleTelephoneChange(e)}
                            required
                            value={customer.telephone?.ddd}
                        />
                        <label htmlFor="number">Numero Telefone: </label>
                        <input type='text' id='number' name='number'
                            onChange={(e: ChangeEvent<HTMLInputElement>) => handleTelephoneChange(e)}
                            required
                            value={customer.telephone?.number || ''}
                        />
                    </div>
                </div>
                <div className="adresses-info-container">
                    <div className="address-info">
                        <h3>Endereço Residencial</h3>
                        <div className="address-container">
                            <div>
                                <label htmlFor="typeResidence">Tipo Residência: </label>
                                <input type='text' id='typeResidence' name='typeResidence'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address?.typeResidence}
                                />
                            </div>
                            <div>
                                <label htmlFor="typePublicPlace">Tipo Logradouro: </label>
                                <input type='text' id='typePublicPlace' name='typePublicPlace'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address?.typePublicPlace}
                                />
                            </div>
                            <div>
                                <label htmlFor="publicPlace">Logradouro: </label>
                                <input type='text' id='publicPlace' name='publicPlace'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address?.publicPlace}
                                />
                            </div>
                            <div>
                                <label htmlFor="number">Número: </label>
                                <input type='text' id='number' name='number'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address.number}
                                />
                            </div>
                            <div>
                                <label htmlFor="neighborhood">Bairro: </label>
                                <input type='text' id='neighborhood' name='neighborhood'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address?.neighborhood}
                                />
                            </div>
                            <div>
                                <label htmlFor="cep">CEP: </label>
                                <input type='text' id='cep' name='cep'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address?.cep}
                                />
                            </div>
                            <div>
                                <label htmlFor="city">Cidade: </label>
                                <input type='text' id='city' name='city'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address?.city}
                                />
                            </div>
                            <div>
                                <label htmlFor="state">Estado: </label>
                                <input type='text' id='state' name='state'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address?.state}
                                />
                            </div>
                            <div>
                                <label htmlFor="country">País: </label>
                                <input type='text' id='country' name='country' autoComplete="off"
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    required
                                    value={customer.address?.country}
                                />
                            </div>
                            <div>
                                <label htmlFor="observation">Observação/Apelido: </label>
                                <input type='text' id='observation' name='observation'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleAddressChange(e)}
                                    value={customer.address?.observation}
                                />
                            </div>
                        </div>
                    </div>
                </div>
                <button type='submit'>Alterar</button>
            </form>
        </>
    )
}
