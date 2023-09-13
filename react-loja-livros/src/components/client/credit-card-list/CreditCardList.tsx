import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import CreditCard from '../../../models/CreditCard';
import { findCreditCardsByCustomerId } from '../../../services/service';

export default function CreditCardList() {

    const { customerId } = useParams()

    let navigate = useNavigate();


    const [creditCards, setCreditCards] = useState<CreditCard[]>([])


    async function getCreditCardsByCustomerId() {
        await findCreditCardsByCustomerId(`/customers/${customerId}/credit-cards`, setCreditCards, {});
    }


    useEffect(() => {
        getCreditCardsByCustomerId();
    }, [customerId]);

    function goToCreateCreditCard() {
        navigate("create")
    }

    return (
        <>
            <div className="creditcard-list-container">
                <h1>Cartões de Créditos</h1>
                <div className="creditcard-add">
                    <button onClick={goToCreateCreditCard}>Adicionar um Cartão de Crédito</button>
                </div>
                <div className="creditcard-list">
                    <ul className="creditcard-itens">
                        {
                            creditCards.map(creditCard => (
                                <li className="observation-nickname" key={creditCard.id}>
                                    <h3><label htmlFor="observation">{creditCard.observation} </label></h3>
                                    <label htmlFor='expiring-date'>
                                        {creditCard.expMonth}/{creditCard.expYear} -
                                    </label>
                                    <label htmlFor='brand'> {creditCard.creditCardBrand} </label>
                                    <Link to={`${creditCard.id}`}>
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
