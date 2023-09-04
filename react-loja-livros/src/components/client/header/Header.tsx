import React, { Component } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import './Header.css'


export default function Header() {
  return (
    <>
      <div className='client-header'>
        <div className="client-header-container">
          <Link to="/home">
            <div className="client-header-logo">Fantasy Books</div>
          </Link>
          <div className="client-header-search-box">
            <form className='client-header-search-form' action="">
              <input className='client-header-search-form-input' type="text" placeholder="Search.." name="search" />
              <button className='client-header-search-form-button' type="submit">Procurar</button>
            </form>
          </div>
          <div className="client-header-carrinho">carrinho</div>
          <div className="client-header-user">User</div>
        </div>
      </div>
    </>
  )
}
