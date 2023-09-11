import Customer from "./Customer"

interface CreditCard {
    id: number,
    cardNumber: string,
    holderName: string,
    expMonth: string,
    expYear: string,
    cvv: string,
    creditCardBrand: string,
    customer?: Customer|null
}

export default CreditCard