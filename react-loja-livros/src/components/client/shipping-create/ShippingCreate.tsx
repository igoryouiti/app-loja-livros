import React, { ChangeEvent, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import ShippingAddress from '../../../models/ShippingAddress'
import { createShipping } from '../../../services/service'

export default function ShippingCreate() {

    const { customerId } = useParams()

    let navigate = useNavigate();

    const [shipping, setShipping] = useState<ShippingAddress>({
        id: 0,
        typeResidence: "",
        typePublicPlace: "",
        publicPlace: "",
        number: "",
        neighborhood: "",
        cep: "",
        city: "",
        state: "",
        country: "",
        observation: ""
    })

    const [resultShipping, setResultShipping] = useState<ShippingAddress>({
        id: 0,
        typeResidence: "",
        typePublicPlace: "",
        publicPlace: "",
        number: "",
        neighborhood: "",
        cep: "",
        city: "",
        state: "",
        country: "",
        observation: ""
    })


    useEffect(() => {
        setShipping({
            ...shipping,
            customer: {
                id: Number(customerId)
            }
        })
    }, [customerId]);

    function handleShippingChange(e: ChangeEvent<HTMLInputElement>) {
        setShipping({
            ...shipping,
            [e.target.name]: e.target.value
        });
    }

    async function shippingCreate(e: ChangeEvent<HTMLFormElement>) {

        e.preventDefault()

        try {
            await createShipping(`/customers/shipping-addresses`, shipping, setResultShipping)

            navigate("../shippings");

        } catch (error) {
            console.log(`Error: ${error}`)
        }

    }

    return (
        <>
            <form onSubmit={shippingCreate}>
                <div className="adresses-info-container">
                    <div className="address-info">
                        <h3>Adicionar Endereço de Cobrança</h3>
                        <div className="address-container">
                            <div>
                                <label htmlFor="typeResidence">Tipo Residência: </label>
                                <input type='text' id='typeResidence' name='typeResidence'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="typePublicPlace">Tipo Logradouro: </label>
                                <input type='text' id='typePublicPlace' name='typePublicPlace'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="publicPlace">Logradouro: </label>
                                <input type='text' id='publicPlace' name='publicPlace'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="number">Número: </label>
                                <input type='text' id='number' name='number'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="neighborhood">Bairro: </label>
                                <input type='text' id='neighborhood' name='neighborhood'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="cep">CEP: </label>
                                <input type='text' id='cep' name='cep'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="city">Cidade: </label>
                                <input type='text' id='city' name='city'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="state">Estado: </label>
                                <input type='text' id='state' name='state'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="country">País: </label>
                                <input type='text' id='country' name='country' autoComplete="off"
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="observation">Observação/Apelido: </label>
                                <input type='text' id='observation' name='observation'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                />
                            </div>
                        </div>
                    </div>
                </div>
                <button type='submit'>Adicionar</button>
            </form>
        </>
    )
}
