export default interface EditCustomerDTO {
    id: number,
    name: string,
    gender: string,
    birthday: string,
    cpf: string,
  telephone: {
    id?: number,
    type?: string,
    number?: string,
    ddd?: string,
  };
  address: {
    id?: number,
    typeResidence?: string,
    typePublicPlace?: string,
    publicPlace?: string,
    number?: string,
    neighborhood?:string,
    cep?:string,
    city?:string,
    state?:string,
    country?:string,
    observation?: string,
  };
}
