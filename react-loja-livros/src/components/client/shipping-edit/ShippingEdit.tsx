import React, { ChangeEvent, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { deleteShipping, findShippingById, updateShipping } from '../../../services/service';
import ShippingAddress from '../../../models/ShippingAddress';

export default function ShippingEdit() {

    const { customerId, shippingAddressId } = useParams()

    let navigate = useNavigate()


    const [shipping, setShipping] = useState<ShippingAddress>({
        id: 0,
        typeResidence: "",
        typePublicPlace: "",
        publicPlace: "",
        number: "",
        neighborhood:"",
        cep:"",
        city:"",
        state:"",
        country:"",
        observation: ""
    })

    const [resultShipping, setResultShipping] = useState<ShippingAddress>({
        id: 0,
        typeResidence: "",
        typePublicPlace: "",
        publicPlace: "",
        number: "",
        neighborhood:"",
        cep:"",
        city:"",
        state:"",
        country:"",
        observation: ""
    })


    async function getShippingsById() {
        await findShippingById(`/customers/shipping-addresses/${shippingAddressId}`, setShipping, {});
    }


    useEffect(() => {
        getShippingsById();
    }, [shippingAddressId]);

    function handleShippingChange(e: ChangeEvent<HTMLInputElement>) {
        setShipping({
            ...shipping,
            [e.target.name]: e.target.value
        });
    }

    async function shippingDelete(){
        try {
            await deleteShipping(`/customers/shipping-addresses/${shipping.id}`)

        } catch (error) {
            console.log(`Error: ${error}`)
        }
        
        navigate("../shippings")

    }

    async function shippingUpdate(e: ChangeEvent<HTMLFormElement>) {

        e.preventDefault()

        try {
            await updateShipping(`/customers/shipping-addresses`, shipping, setResultShipping)
            navigate("../shippings")
        } catch (error) {
            console.log(`Error: ${error}`)
        }

    }


    return (
        <>
            <form onSubmit={shippingUpdate}>
                <div className="adresses-info-container">
                    <div className="address-info">
                        <h3>Endereço de Cobrança</h3>
                        <div className="address-container">
                            <div>
                                <label htmlFor="typeResidence">Tipo Residência: </label>
                                <input type='text' id='typeResidence' name='typeResidence'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.typeResidence}
                                />
                            </div>
                            <div>
                                <label htmlFor="typePublicPlace">Tipo Logradouro: </label>
                                <input type='text' id='typePublicPlace' name='typePublicPlace'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.typePublicPlace}
                                />
                            </div>
                            <div>
                                <label htmlFor="publicPlace">Logradouro: </label>
                                <input type='text' id='publicPlace' name='publicPlace'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.publicPlace}
                                />
                            </div>
                            <div>
                                <label htmlFor="number">Número: </label>
                                <input type='text' id='number' name='number'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.number}
                                />
                            </div>
                            <div>
                                <label htmlFor="neighborhood">Bairro: </label>
                                <input type='text' id='neighborhood' name='neighborhood'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.neighborhood}
                                />
                            </div>
                            <div>
                                <label htmlFor="cep">CEP: </label>
                                <input type='text' id='cep' name='cep'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.cep}
                                />
                            </div>
                            <div>
                                <label htmlFor="city">Cidade: </label>
                                <input type='text' id='city' name='city'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.city}
                                />
                            </div>
                            <div>
                                <label htmlFor="state">Estado: </label>
                                <input type='text' id='state' name='state'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.state}
                                />
                            </div>
                            <div>
                                <label htmlFor="country">País: </label>
                                <input type='text' id='country' name='country' autoComplete="off"
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    required
                                    value={shipping.country}
                                />
                            </div>
                            <div>
                                <label htmlFor="observation">Observação/Apelido: </label>
                                <input type='text' id='observation' name='observation'
                                    onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingChange(e)}
                                    value={shipping.observation}
                                />
                            </div>
                        </div>
                    </div>
                </div>
                <button type='submit'>Alterar</button>
            </form>
            <button onClick={shippingDelete}>Deletar</button>
        </>
    )
}
