import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import ShippingAddress from '../../../models/ShippingAddress';
import { findShippingsByCustomerId } from '../../../services/service';

export default function ShippingList() {

    const { customerId } = useParams()

    let navigate = useNavigate();


    const [shippings, setShippings] = useState<ShippingAddress[]>([])


    async function getShippingsByCustomerId() {
        await findShippingsByCustomerId(`/customers/${customerId}/shipping-addresses`, setShippings, {});
    }


    useEffect(() => {
        getShippingsByCustomerId();
    }, [customerId]);

    function goToCreateShipping(){
        navigate("create")
    }

    return (
        <>
            <div className="shipping-list-container">
                <h1>Endereços de Entrega</h1>
                <div className="shipping-add">
                    <button onClick={goToCreateShipping}>Adicionar um endereço de entrega</button>
                </div>
                <div className="shipping-list">
                    <ul className="shipping-itens">
                        {
                            shippings.map(shipping => (
                                <li className="observation-nickname" key={shipping.id}>
                                    <h3><label htmlFor="observation">{shipping.observation} </label></h3>
                                    <label htmlFor='address'>
                                        {shipping.typePublicPlace} {shipping.publicPlace} - 
                                    </label>
                                    <label htmlFor='neighborhood'> {shipping.neighborhood} </label>
                                    <label htmlFor='city'>{shipping.city} </label>
                                    <Link to={`${shipping.id}`}>
                                        <button>Detalhar</button>
                                    </Link>
                                </li>
                            ))
                        }
                    </ul>
                </div>
            </div>
        </>
    )
}

