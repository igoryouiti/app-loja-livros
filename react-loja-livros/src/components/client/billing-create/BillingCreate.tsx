import React, { ChangeEvent, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import BillingAddress from '../../../models/BillingAddress'
import { createBilling } from '../../../services/service'

export default function BillingCreate() {

    const { customerId } = useParams()

    let navigate = useNavigate();

    const [billing, setBilling] = useState<BillingAddress>({
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

    const [resultBilling, setResultBilling] = useState<BillingAddress>({
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
        setBilling({
            ...billing,
            customer: {
                id: Number(customerId)
            }
        })
    }, [customerId]);

    function handleBillingChange(e: ChangeEvent<HTMLInputElement>) {
        setBilling({
            ...billing,
            [e.target.name]: e.target.value
        });
    }

    async function billingCreate(e: ChangeEvent<HTMLFormElement>) {

        e.preventDefault()

        try {
            await createBilling(`/customers/billing-addresses`, billing, setResultBilling)

            navigate("../billings");

        } catch (error) {
            console.log(`Error: ${error}`)
        }

    }

    return (
        <>
            <form onSubmit={billingCreate}>
                <div className="adresses-info-container">
                    <div className="address-info">
                        <h3>Adicionar Endereço de Cobrança</h3>
                        <div className="address-container">
                            <div>
                                <label htmlFor="typeResidence">Tipo Residência: </label>
                                <input type='text' id='typeResidence' name='typeResidence'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="typePublicPlace">Tipo Logradouro: </label>
                                <input type='text' id='typePublicPlace' name='typePublicPlace'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="publicPlace">Logradouro: </label>
                                <input type='text' id='publicPlace' name='publicPlace'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="number">Número: </label>
                                <input type='text' id='number' name='number'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="neighborhood">Bairro: </label>
                                <input type='text' id='neighborhood' name='neighborhood'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="cep">CEP: </label>
                                <input type='text' id='cep' name='cep'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="city">Cidade: </label>
                                <input type='text' id='city' name='city'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="state">Estado: </label>
                                <input type='text' id='state' name='state'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="country">País: </label>
                                <input type='text' id='country' name='country' autoComplete="off"
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
                                    required
                                />
                            </div>
                            <div>
                                <label htmlFor="observation">Observação/Apelido: </label>
                                <input type='text' id='observation' name='observation'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingChange(e)}
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
