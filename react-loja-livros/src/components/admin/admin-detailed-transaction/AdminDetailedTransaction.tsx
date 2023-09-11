import React from 'react'


export default function AdminDetailedTransaction() {
    return (
        <>
            <div className="chart-summary-content">
                <div className="chart-items-list">
                    <div className="chart-item">
                        <img className="image-chart-item" src="https://lojasaraivanew.vtexassets.com/arquivos/ids/159014-1200-auto?v=638000884796000000&width=1200&height=auto&aspect=true" alt="" />
                        <text className="name-chart-item">Harry Potter e o Cálice de Fogo</text>
                        <text className="price-chart-item">RS99,99</text>
                        <div className="quantity-chart-item">
                            <input type="text" className="input-quantity-chart-item" placeholder="1" name="quantity" />
                        </div>
                        <div className="status">
                            <label htmlFor="status" />
                            <select id="status" name="status">
                                <option value="1">EM TROCA</option>
                                <option value="2">TROCA AUTORIZADA</option>
                                <option value="3">TROCA NEGADA</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div className="chart-items-list">
                    <div className="chart-item">
                        <img className="image-chart-item" src="https://lojasaraivanew.vtexassets.com/arquivos/ids/159014-1200-auto?v=638000884796000000&width=1200&height=auto&aspect=true" alt="" />
                        <text className="name-chart-item">Harry Potter e o Cálice de Fogo</text>
                        <text className="price-chart-item">RS99,99</text>
                        <div className="quantity-chart-item">
                            <input type="text" className="input-quantity-chart-item" placeholder="1" name="quantity" />
                        </div>
                        <div>EM PROCESSAMENTO</div>
                    </div>
                </div>
                <div className="chart-options">
                    <div className="freight-chart-options">
                        <div>
                            <div>CEP</div>
                            <div>08775-530</div>
                        </div>
                        <div className="summary-freight-calc">
                            <div className="estimated-time-chart-option">Até 3 dias úteis</div>
                            <div className="price-freight-chart-option">R$11,49</div>
                        </div>
                    </div>
                    <div className="summary-chart-option">
                        <div className="summary-chart">
                            <div className="sub-total">R$99,99</div>
                            <div className="freight-price">R$11,49</div>
                            <div className="promo-coupons-chart">Cupom20</div>
                            <div className="coupon-value">R$20,00</div>
                            <div className="total-price-itens">Total: R$91,48</div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
