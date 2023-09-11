import Customer from "./Customer"

interface Telephone {
    id: number,
    type: string,
    number: string,
    ddd: string,
    customer?: Customer|null 
}

export default Telephone