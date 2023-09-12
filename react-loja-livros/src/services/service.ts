import axios from "axios"

export const api = axios.create({baseURL: "http://localhost:8080"})

export const postUser = async (url: any, data: any, setData: any) => {
    const resposta = await api.post(url, data);
    setData(resposta.data);
};

export const postCustomer = async (url: any, data: any, setData: any) => {
    const resposta = await api.post(url, data);
    setData(resposta.data);
};

export const updateCustomer = async (url: any, data: any, setData: any) => {
    const resposta = await api.put(url, data);
    setData(resposta.data);
};

export const findById = async (url: any, setDado: any, header: any) => {
    const resposta = await api.get(url, header);
    setDado(resposta.data);
};

export const findBillingsByCustomerId = async (url: any, setDado: any, header: any) => {
    const resposta = await api.get(url, header);
    setDado(resposta.data);
};

export const findBillingById = async (url: any, setDado: any, header: any) => {
    const resposta = await api.get(url, header);
    setDado(resposta.data);
};

export const updateBilling = async (url: any, data: any, setData: any) => {
    const resposta = await api.put(url, data);
    setData(resposta.data);
};

export const deleteBilling = async (url: any) => {
    const resposta = await api.delete(url);
};

export const createBilling = async (url: any, data: any, setData: any) => {
    const resposta = await api.post(url, data);
    setData(resposta.data);
};

export const findShippingsByCustomerId = async (url: any, setDado: any, header: any) => {
    const resposta = await api.get(url, header);
    setDado(resposta.data);
};

export const findShippingById = async (url: any, setDado: any, header: any) => {
    const resposta = await api.get(url, header);
    setDado(resposta.data);
};

export const updateShipping = async (url: any, data: any, setData: any) => {
    const resposta = await api.put(url, data);
    setData(resposta.data);
};

export const deleteShipping = async (url: any) => {
    const resposta = await api.delete(url);
};

export const createShipping = async (url: any, data: any, setData: any) => {
    const resposta = await api.post(url, data);
    setData(resposta.data);
};