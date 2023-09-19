import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import BillingAddress from '../../../models/BillingAddress';
import { findBillingsByCustomerId } from '../../../services/service';

export default function BillingList() {

    const { customerId } = useParams()

    let navigate = useNavigate();


    const [billings, setBillings] = useState<BillingAddress[]>([])


    async function getBillingsByCustomerId() {
        await findBillingsByCustomerId(`/customers/${customerId}/billing-addresses`, setBillings, {});
    }


    useEffect(() => {
        getBillingsByCustomerId();
    }, [customerId]);

    function goToCreateBilling(){
        navigate("create")
    }

    function goBack(){
        navigate("../")
    }

    return (
        <>
            <div className="billing-list-container">
                <h1>Endereços de Cobrança</h1>
                <button onClick={goBack}>Voltar</button>
                <div className="billing-add">
                    <button onClick={goToCreateBilling}>Adicionar um endereço de cobrança</button>
                </div>
                <div className="billing-list">
                    <ul className="billing-itens">
                        {
                            billings.map(billing => (
                                <li className="observation-nickname" key={billing.id}>
                                    <h3><label htmlFor="observation">{billing.observation} </label></h3>
                                    <label htmlFor='address'>
                                        {billing.typePublicPlace} {billing.publicPlace} - 
                                    </label>
                                    <label htmlFor='neighborhood'> {billing.neighborhood} </label>
                                    <label htmlFor='city'>{billing.city} </label>
                                    <Link to={`${billing.id}`}>
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

