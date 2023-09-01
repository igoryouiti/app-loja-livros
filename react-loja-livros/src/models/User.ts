import Customer from "./Customer"

interface User {
    id: number,
    email: string,
    password: string,
    password2:string,
    customer?: Customer
}

export default User