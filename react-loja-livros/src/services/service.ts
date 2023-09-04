import axios from "axios"

export const api = axios.create({baseURL: "http://localhost:3000"})

export const userRegister = async (url: any, data: any, setData: any) => {
    const resposta = await api.post(url, data);
    setData(resposta.data);
};