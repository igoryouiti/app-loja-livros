import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import BillingAddress from '../../../models/BillingAddress';
import { findBillingsByCustomerId } from '../../../services/service';

export default function BillingList() {

    const { customerId } = useParams()


    const [billings, setBillings] = useState<BillingAddress[]>([])


    async function getBillingsByCustomerId() {
        await findBillingsByCustomerId(`/customers/${customerId}/billing-addresses`, setBillings, {});
    }


    useEffect(() => {
        getBillingsByCustomerId();
    }, [customerId]);

  return (
    <>
        <div className="billing-list-container">
                <div className="billing-list">
                    <ul className="billing-itens">
                        {
                            billings.map(billing => (
                                <li className="observation-nickname" key={billing.id}>
                                    <label htmlFor="observation">{billing.observation}</label>
                                </li>
                            ))
                        }
                    </ul>
                </div>
            </div>
    </>
  )
}
