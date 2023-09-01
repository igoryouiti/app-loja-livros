import Address from "./Address"
import BillingAddress from "./BillingAddress"
import CreditCard from "./CreditCard"
import ShippingAddress from "./ShippingAddress"
import Telephone from "./Telephone"

interface Customer{
    id: string,
    name: string,
    gender: string,
    birthday: string,
    cpf: string,
    address?: Address,
    telephone?: Telephone,
    billingAddresses?: BillingAddress[],
    shippingAddresses?: ShippingAddress[],
    creditCards?: CreditCard[]
}

export default Customer