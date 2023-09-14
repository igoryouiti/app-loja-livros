import React, { ChangeEvent, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import Customer from '../../../models/Customer';
import Telephone from '../../../models/Telephone';
import Address from '../../../models/Address';
import BillingAddress from '../../../models/BillingAddress';
import ShippingAddress from '../../../models/ShippingAddress';
import CreditCard from '../../../models/CreditCard';
import { postCustomer } from '../../../services/service';
import CreateCustomerDTO from '../../../models/DTO/CreateCustomerDTO';

interface BillingSame {
	"active": boolean;
}

interface ShippingSame {
	"active": boolean;
}

export default function CustomerRegisterComp() {

	let navigate = useNavigate()

	const { userId } = useParams()

	const [cpfConfirm, setCpfConfirm] = useState<String>("")

	const [customer, setCustomer] = useState<CreateCustomerDTO>({
		id: 0,
		name: "",
		gender: "",
		birthday: "",
		cpf: "",
		address: null,
		telephone: null,
		billingAddresses: null,
		shippingAddresses: null,
		creditCards: null,
		user: {
			id: userId !== undefined ? parseInt(userId, 10) : 0,
			email: "",
			password: "",
			password2: "",
			customer: null
		}
	})

	const [customerResult, setResultCustomer] = useState<CreateCustomerDTO>({
		id: 0,
		name: "",
		gender: "",
		birthday: "",
		cpf: "",
		address: null,
		telephone: null,
		billingAddresses: null,
		shippingAddresses: null,
		creditCards: null
	})

	const [telephone, setTelephone] = useState<Telephone>({
		id: 0,
		type: "",
		number: "",
		ddd: "",
		customer: null
	})

	const [address, setAddress] = useState<Address>({
		id: 0,
		typeResidence: "",
		typePublicPlace: "",
		publicPlace: "",
		number: "",
		neighborhood: "",
		cep: "",
		city: "",
		state: '',
		country: "",
		observation: '',
		customer: null
	})

	const [billingAddress, setBillingAddress] = useState<BillingAddress>({
		id: 0,
		typeResidence: "",
		typePublicPlace: "",
		publicPlace: "",
		number: "",
		neighborhood: "",
		cep: "",
		city: "",
		state: '',
		country: "",
		observation: '',
		customer: null
	})

	const [shippingAddress, setShippingAddress] = useState<ShippingAddress>({
		id: 0,
		typeResidence: "",
		typePublicPlace: "",
		publicPlace: "",
		number: "",
		neighborhood: "",
		cep: "",
		city: "",
		state: '',
		country: "",
		observation: '',
		customer: null
	})

	const [creditCard, setCreditCard] = useState<CreditCard>({
		id: 0,
		cardNumber: "",
		holderName: "",
		expMonth: "",
		expYear: "",
		cvv: '',
		observation: "",
		creditCardBrand: "MASTERCARD",
		customer: null
	})



	const [billingSame, setBillingSame] = useState<BillingSame>({
		active: false
	})

	const [shippingSame, setShippingSame] = useState<ShippingSame>({
		active: false
	})

	useEffect(() => {
		if (customerResult.id != 0) {
			navigate(`/dashboard/${customerResult.id}/settings`) 
		}
	}, [customerResult])


	function updatedCustomer(e: ChangeEvent<HTMLInputElement>) {
		setCustomer({
			...customer,
			[e.target.name]: e.target.value
		});
	}

	function updatedTelephone(e: ChangeEvent<HTMLInputElement>) {
		setTelephone({
			...telephone,
			[e.target.name]: e.target.value
		})
	}

	function updatedAddress(e: ChangeEvent<HTMLInputElement>) {
		setAddress({
			...address,
			[e.target.name]: e.target.value
		})
	}

	function updatedBillingAddress(e: ChangeEvent<HTMLInputElement>) {
		setBillingAddress({
			...billingAddress,
			[e.target.name]: e.target.value
		})
	}


	function updatedShippingAddress(e: ChangeEvent<HTMLInputElement>) {
		setShippingAddress({
			...shippingAddress,
			[e.target.name]: e.target.value
		})
	}

	function updatedCreditCard(e: ChangeEvent<HTMLInputElement | HTMLSelectElement>) {
		setCreditCard({
			...creditCard,
			[e.target.name]: e.target.value
		})
	}

	function handleBillingSame(e: ChangeEvent<HTMLInputElement>) {
		const element = document.getElementById("show-billing-address");
		if (element) {
			if (e.target.value === "true") {
				element.style.display = "none";

				const inputs = element.querySelectorAll('input');
				inputs.forEach(input => {
					input.removeAttribute('required');
				});

				setBillingSame({
					active: true
				})
			} else {
				element.style.display = "block";

				const inputs = element.querySelectorAll('input');
				inputs.forEach(input => {
					input.setAttribute('required', 'true');
				});

				setBillingSame({
					active: false
				})
			}
		}
	}

	function handleShippingSame(e: ChangeEvent<HTMLInputElement>) {
		const element = document.getElementById("show-shipping-address");
		if (element) {
			const requiredInputs = document.querySelectorAll('.required');
			if (e.target.value === "true") {
				element.style.display = "none";
				requiredInputs.forEach(input => {
					input.removeAttribute('required');
				});
				setShippingSame({
					active: true
				})
			} else {
				element.style.display = "block";
				requiredInputs.forEach(input => {
					input.setAttribute('required', 'true');
				});
				setShippingSame({
					active: false
				})
			}
		}
	}


	async function customerRegister(e: ChangeEvent<HTMLFormElement>) {

		e.preventDefault()

		let updatedCustomer = { ...customer };

		if (billingSame.active && shippingSame.active) {
			updatedCustomer = {
				...updatedCustomer,
				address: address,
				billingAddresses: address,
				shippingAddresses: address,
				telephone: telephone,
				creditCards: creditCard,
			};
		} else if (!billingSame.active && shippingSame.active) {
			updatedCustomer = {
				...updatedCustomer,
				address: address,
				billingAddresses: billingAddress,
				shippingAddresses: address,
				telephone: telephone,
				creditCards: creditCard,
			};
		} else if (billingSame.active && !shippingSame.active) {
			updatedCustomer = {
				...updatedCustomer,
				address: address,
				billingAddresses: address,
				shippingAddresses: shippingAddress,
				telephone: telephone,
				creditCards: creditCard,
			};
		} else {
			updatedCustomer = {
				...updatedCustomer,
				address: address,
				billingAddresses: billingAddress,
				shippingAddresses: shippingAddress,
				telephone: telephone,
				creditCards: creditCard,
			};
		}

		console.log(updatedCustomer)


		try {
			await postCustomer(`/customers`, updatedCustomer, setResultCustomer)

		} catch (error) {
			console.log(`Error: ${error}`)
		}
	}



	return (
		<>
			<form onSubmit={customerRegister}>
				<div className="user-info">
					<h3>Informações de Usuario</h3>
					<label htmlFor="name">Nome: </label>
					<input type='text' id='name' name='name'
						onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCustomer(e)}
						required
						autoComplete="off"
					/>
					<label htmlFor="gender">Genero: </label>
					<input type='text' id='gender' name='gender'
						onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCustomer(e)}
						required
					/>
					<label htmlFor="birthday">Data Nascimento: </label>
					<input type='text' id='birthday' name='birthday'
						onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCustomer(e)}
						required
						placeholder='aaaa-MM-dd'
					/>
					<label htmlFor="cpf">CPF: </label>
					<input type='text' id='cpf' name='cpf'
						onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCustomer(e)}
						required
					/>
					<div className="telephone-info">
						<label htmlFor="type">Tipo: </label>
						<input type='text' id='type' name='type'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedTelephone(e)}
							required
						/>
						<label htmlFor="ddd">DDD: </label>
						<input type='text' id='ddd' name='ddd'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedTelephone(e)}
							required
						/>
						<label htmlFor="number">Numero Telefone: </label>
						<input type='text' id='number' name='number'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedTelephone(e)}
							required
						/>
					</div>
				</div>
				<div className="adresses-info-container">
					<div className="address-info">
						<h3>Endereço Residencial</h3>
						<div className="address-container">
							<div>
								<label htmlFor="typeResidence">Tipo Residência: </label>
								<input type='text' id='typeResidence' name='typeResidence'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="typePublicPlace">Tipo Logradouro: </label>
								<input type='text' id='typePublicPlace' name='typePublicPlace'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="publicPlace">Logradouro: </label>
								<input type='text' id='publicPlace' name='publicPlace'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="number">Número: </label>
								<input type='text' id='number' name='number'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="neighborhood">Bairro: </label>
								<input type='text' id='neighborhood' name='neighborhood'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="cep">CEP: </label>
								<input type='text' id='cep' name='cep'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="city">Cidade: </label>
								<input type='text' id='city' name='city'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="state">Estado: </label>
								<input type='text' id='state' name='state'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="country">País: </label>
								<input type='text' id='country' name='country' autoComplete="off"
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
									required
								/>
							</div>
							<div>
								<label htmlFor="observation">Observação/Apelido: </label>
								<input type='text' id='observation' name='observation'
									onChange={(e: ChangeEvent<HTMLInputElement>) => updatedAddress(e)}
								/>
							</div>
						</div>
					</div>
					<h3>Endereço de Cobrança</h3>
					<div className="radio-btn">
						<p>Deseja utilizar o endereço residencial?</p>
						<input type="radio" id="true"
							name="billingSame" value="true"
							onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingSame(e)}
						/>
						<label htmlFor="yes">sim</label>
						<input type="radio" id="false"
							name="billingSame" value="false"
							defaultChecked
							onChange={(e: ChangeEvent<HTMLInputElement>) => handleBillingSame(e)}
						/>
						<label htmlFor="no">não</label>
					</div>
					<div className="billing-address-container" id='show-billing-address'>
						<div>
							<label htmlFor="typeResidence">Tipo Residência: </label>
							<input type='text' id='typeResidence' name='typeResidence'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="typePublicPlace">Tipo Logradouro: </label>
							<input type='text' id='typePublicPlace' name='typePublicPlace'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="publicPlace">Logradouro: </label>
							<input type='text' id='publicPlace' name='publicPlace'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="number">Número: </label>
							<input type='text' id='number' name='number'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="neighborhood">Bairro: </label>
							<input type='text' id='neighborhood' name='neighborhood'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="cep">CEP: </label>
							<input type='text' id='cep' name='cep'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="city">Cidade: </label>
							<input type='text' id='city' name='city'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="state">Estado: </label>
							<input type='text' id='state' name='state'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="country">País: </label>
							<input type='text' id='country' name='country' autoComplete="off"
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="observation">Observação/Apelido: </label>
							<input type='text' id='observation' name='observation'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedBillingAddress(e)}
							/>
						</div>
					</div>
					<h3>Endereço de Entrega</h3>
					<div className="radio-btn">
						<p>Deseja utilizar o endereço residencial?</p>
						<input type="radio" id="yes"
							name="shippingSame" value="true"
							onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingSame(e)}
						/>
						<label htmlFor="yes">sim</label>
						<input type="radio" id="no"
							name="shippingSame" value="false"
							defaultChecked
							onChange={(e: ChangeEvent<HTMLInputElement>) => handleShippingSame(e)}
						/>
						<label htmlFor="no">não</label>
					</div>
					<div className="shipping-address-container" id='show-shipping-address'>
						<div>
							<label htmlFor="typeResidence">Tipo Residência: </label>
							<input type='text' id='typeResidence' name='typeResidence'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="typePublicPlace">Tipo Logradouro: </label>
							<input type='text' id='typePublicPlace' name='typePublicPlace'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="publicPlace">Logradouro: </label>
							<input type='text' id='publicPlace' name='publicPlace'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="number">Número: </label>
							<input type='text' id='number' name='number'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="neighborhood">Bairro: </label>
							<input type='text' id='neighborhood' name='neighborhood'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="cep">CEP: </label>
							<input type='text' id='cep' name='cep'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="city">Cidade: </label>
							<input type='text' id='city' name='city'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="state">Estado: </label>
							<input type='text' id='state' name='state'
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="country">País: </label>
							<input type='text' id='country' name='country' autoComplete="off"
								className='required'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
								required
							/>
						</div>
						<div>
							<label htmlFor="observation">Observação/Apelido: </label>
							<input type='text' id='observation' name='observation'
								onChange={(e: ChangeEvent<HTMLInputElement>) => updatedShippingAddress(e)}
							/>
						</div>
					</div>
				</div>
				<div className="card-info">
					<h3>Cartão de Crédito</h3>
					<div>
						<label htmlFor="holderName">Nome do portador: </label>
						<input type='text' id='holderName' name='holderName'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCreditCard(e)}
							required
						/>
					</div>
					<div>
						<label htmlFor="cardNumber">Número do cartão: </label>
						<input type='text' id='cardNumber' name='cardNumber'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCreditCard(e)}
							required
						/>
					</div>
					<div>
						<label htmlFor="expMonth">Mes da validade: </label>
						<input type='number' id='expMonth' name='expMonth'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCreditCard(e)}
							required
						/>
					</div>
					<div>
						<label htmlFor="expYear">Ano da Validade: </label>
						<input type='number' id='expYear' name='expYear'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCreditCard(e)}
							required
						/>
					</div>
					<div>
						<label htmlFor="cvv">CVV: </label>
						<input type='password' id='cvv' name='cvv'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCreditCard(e)}
							required
						/>
					</div>
					<div>
						<label htmlFor="observation">Observação/Apelido: </label>
						<input type='text' id='observation' name='observation'
							onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCreditCard(e)}
							required
						/>
					</div>
					<div>
						<label htmlFor="creditCardBrand">Bandeira:</label>
						<select id="creditCardBrand" name="creditCardBrand"
							onChange={(e: ChangeEvent<HTMLSelectElement>) => updatedCreditCard(e)}
							required
							defaultValue={"VISA"}
						>
							<option value="MASTERCARD">Mastercard</option>
							<option value="VISA">Visa</option>
							<option value="ELO">American Express</option>
							<option value="HIPERCARD">Hipercard</option>
							<option value="DINERS">Diners</option>
							<option value="DISCOVER">Discover</option>
							<option value="AURA">Aura</option>
							<option value="JCB">JCB</option>
						</select>
					</div>
				</div>
				<button type='submit'>Cadastrar</button>
			</form>
		</>
	)

}