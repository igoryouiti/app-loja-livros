import React, { Component } from 'react'

import './Home.css'

import "./Home.css";
import ShelfProduct from '../../../components/client/shelf-product/ShelfProduct';
import { Link } from 'react-router-dom';

export default class Home extends Component {
  render() {
    return (
      <>
        <div className="client-home">
          <div className="client-home-content">
            <div className="client-home-shelf">
              <div className="client-home-shelf-item">
                <ShelfProduct />
                <ShelfProduct />
                <ShelfProduct />
                <ShelfProduct />
              </div>
            </div>
          </div>
        </div>
      </>
    )
  }
}
