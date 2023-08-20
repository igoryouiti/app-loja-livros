import React, { Component } from 'react'
import './DetailProduct.css'

export default class DetailProduct extends Component {
  render() {
    return (
      <div className="detail-product-container">
        <div className="detail-product">
          <div className="detail-product-image">
            <img className='product-image' src="https://lojasaraivanew.vtexassets.com/arquivos/ids/159014-800-auto?v=638000884796000000&width=800&height=auto&aspect=true"
              alt="Duas mãos estão quase alcançando o calice de fogo" />
          </div>
          <form className="detail-product-buy">
            <div className="product-name">Harry Potter</div>
            <div className="detail-product-to-chart">
              <div className="product-price">R$99,99</div>
              <div className="quantity-product">
                <button className="minus-btn">-</button>
                <div className="quantity">
                  <input className='quantity-input' type="text" placeholder="1" name="quantity" />
                </div>
                <button className="plus-btn">+</button>
              </div>
            </div>
            <button className="add-to-chart">Adicionar ao carrinho</button>
          </form>
        </div>
        <div className="detail-product-description">
          <h3>Descricão</h3>
          <text>Nesta aventura, o feiticeiro cresceu e está com 14 anos. O início do ano
            letivo de Harry Potter reserva muitas emoções, mágicas, e acontecimentos inesperados,
            além de um novo torneio em que os alunos de Hogwarts terão de demonstrar todas as
            habilidade mágicas e nãomágicas que vêm adquirindo ao longo de suas vidas. Harry é escolhido
            pelo Cálice de Fogo para competir como um dos campeões de Hogwarts, tendo ao lado seus
            fiéis amigos. Muitos desafios, feitiços, poções e confusões estão reservados para Harry. Além disso,
            ele terá que lidar ainda com os problemas comuns da adolescência amor, amizade, aceitação e rejeição.
          </text>
        </div>
        <div className="detail-product-specification">
          <h3>Especificação</h3>
          <div className="list-specification">
            <div className="item">
              <div className="specification">Ano da Edição</div>
              <div className="specification-data">2017</div>
            </div>
            <div className="item">
              <div className="specification">Autor</div>
              <div className="specification-data">Rowling, J.K.</div>
            </div>
            <div className="item">
              <div className="specification">Editora</div>
              <div className="specification-data">Editora Rocco Ltda</div>
            </div>
            <div className="item">
              <div className="specification">Edição</div>
              <div className="specification-data">Primeira</div>
            </div>
            <div className="item">
              <div className="specification">Ano</div>
              <div className="specification-data">2000</div>
            </div>
            <div className="item">
              <div className="specification">ISBN</div>
              <div className="specification-data">978-85-325-3081-3</div>
            </div>
            <div className="item">
              <div className="specification">Número de páginas</div>
              <div className="specification-data">480</div>
            </div>
            <div className="item">
              <div className="specification">Dimensões</div>
              <div className="specification-data">23 x 15.4 x 3.2 cm</div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}
