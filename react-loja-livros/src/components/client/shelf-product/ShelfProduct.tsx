import React, { Component } from 'react'
import './ShelfProduct.css'
import { Link } from 'react-router-dom'


export default function ShelfProduct() {
    return (
        <>
            <Link to='/products' className="card-shelf-container">
                <div className="card-shelf-image">
                    <img className='shelf-image' src="https://lojasaraivanew.vtexassets.com/arquivos/ids/159014-800-auto?v=638000884796000000&width=800&height=auto&aspect=true"
                        alt="Duas mãos estão quase alcançando o calice de fogo" />
                </div>
                <div className="card-shelf-name"> Harry Potter e o Cálice de Fogo </div>
                <div className="card-shelf-price"> R$99,99 </div>
            </Link>
        </>
    )
}
