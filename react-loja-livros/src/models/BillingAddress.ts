import Customer from "./Customer"

interface BillingAddress {
    id: number,
    typeResidence: string,
    typePublicPlace: string,
    publicPlace: string,
    number: string,
    neighborhood:string,
    cep:string,
    city:string,
    state:string,
    country:string,
    observation?: string,
    customer?: Customer|null
}

export default BillingAddress