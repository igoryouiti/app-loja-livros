import React, { ChangeEvent, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import CreditCard from '../../../models/CreditCard'
import { deleteCreditCard, findCreditCardsByCustomerId, updateCreditCard } from '../../../services/service'

export default function CreditCardEdit() {

  const { customerId, creditCardId } = useParams()

  let navigate = useNavigate()


  const [creditCard, setCreditCard] = useState<CreditCard>({
    id: 0,
    cardNumber: "",
    holderName: "",
    expMonth: "",
    expYear: "",
    cvv: '',
    observation: "",
    creditCardBrand: "",
  })

  const [resultCreditCard, setResultCreditCard] = useState<CreditCard>({
    id: 0,
    cardNumber: "",
    holderName: "",
    expMonth: "",
    expYear: "",
    cvv: '',
    observation: "",
    creditCardBrand: "",
  })


  async function getCreditCardsById() {
    await findCreditCardsByCustomerId(`/customers/credit-cards/${creditCardId}`, setCreditCard, {});
  }


  useEffect(() => {
    getCreditCardsById();
  }, [creditCardId]);

  function handleCreditCardChange(e: ChangeEvent<HTMLInputElement | HTMLSelectElement>) {
    setCreditCard({
      ...creditCard,
      [e.target.name]: e.target.value
    });
  }

  async function creditCardDelete() {
    try {
      await deleteCreditCard(`/customers/credit-cards/${creditCard.id}`)

    } catch (error) {
      console.log(`Error: ${error}`)
    }

    navigate("../credit-cards")

  }

  async function creditCardUpdate(e: ChangeEvent<HTMLFormElement>) {

    e.preventDefault()

    try {
      await updateCreditCard(`/customers/credit-cards`, creditCard, setResultCreditCard)
      navigate("../credit-cards")
    } catch (error) {
      console.log(`Error: ${error}`)
    }

  }



  return (
    <>
      <form onSubmit={creditCardUpdate}>
        <div className="credit-card-container">
          <div className="card-info">
            <h3>Cartão de Crédito</h3>
            <div>
              <label htmlFor="holderName">Nome do portador: </label>
              <input type='text' id='holderName' name='holderName'
                onChange={(e: ChangeEvent<HTMLInputElement>) => handleCreditCardChange(e)}
                value={creditCard.holderName}
                required
              />
            </div>
            <div>
              <label htmlFor="cardNumber">Número do cartão: </label>
              <input type='text' id='cardNumber' name='cardNumber'
                onChange={(e: ChangeEvent<HTMLInputElement>) => handleCreditCardChange(e)}
                value={creditCard.cardNumber}
                required
              />
            </div>
            <div>
              <label htmlFor="expMonth">Mes da validade: </label>
              <input type='number' id='expMonth' name='expMonth'
                onChange={(e: ChangeEvent<HTMLInputElement>) => handleCreditCardChange(e)}
                value={creditCard.expMonth}
                required
              />
            </div>
            <div>
              <label htmlFor="expYear">Ano da Validade: </label>
              <input type='number' id='expYear' name='expYear'
                onChange={(e: ChangeEvent<HTMLInputElement>) => handleCreditCardChange(e)}
                value={creditCard.expYear}
                required
              />
            </div>
            <div>
              <label htmlFor="cvv">CVV: </label>
              <input type='password' id='cvv' name='cvv'
                onChange={(e: ChangeEvent<HTMLInputElement>) => handleCreditCardChange(e)}
                value={creditCard.cvv}
                required
              />
            </div>
            <div>
              <label htmlFor="observation">Observação/Apelido: </label>
              <input type='text' id='observation' name='observation'
                onChange={(e: ChangeEvent<HTMLInputElement>) => handleCreditCardChange(e)}
                value={creditCard.observation}
                required
              />
            </div>
            <div>
              <label htmlFor="creditCardBrand">Bandeira:</label>
              <select id="creditCardBrand" name="creditCardBrand"
                onChange={(e: ChangeEvent<HTMLSelectElement>) => handleCreditCardChange(e)}
                required
                defaultValue={creditCard.creditCardBrand}
              >
                <option value="MASTERCARD">Mastercard</option>
                <option value="VISA">Visa</option>
                <option value="ELO">American Express</option>
                <option value="HIPERCARD">Hipercard</option>
                <option value="DINERS">Diners</option>
                <option value="DISCOVER">Discover</option>
                <option value="AURA">Aura</option>
                <option value="JCB">JCB</option>
              </select>
            </div>
          </div>
        </div>
        <button type='submit'>Alterar</button>
      </form>
      <button onClick={creditCardDelete}>Deletar</button>
    </>
  )
}
