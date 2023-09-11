import Address from "../Address";
import BillingAddress from "../BillingAddress";
import CreditCard from "../CreditCard";
import ShippingAddress from "../ShippingAddress";
import Telephone from "../Telephone";
import User from "../User";

export default interface CreateCustomerDTO{
    id: number,
    name: string,
    gender: string,
    birthday: string,
    cpf: string,
    address: Address|null,
    telephone: Telephone|null,
    billingAddresses?: BillingAddress|null,
    shippingAddresses?: ShippingAddress|null,
    creditCards?: CreditCard|null,
    user?: User|null
}