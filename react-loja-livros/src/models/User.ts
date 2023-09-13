import Customer from "./Customer"

interface User {
    id: number,
    email: string,
    password: string,
    password2:string,
    active?: boolean,
    customer: Customer|null
}

export default User