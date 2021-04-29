import React, { useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Col, Row, Card, CardBody, NavLink, Input } from 'reactstrap';
import InputForm from './InputForm';


const ModalAddMenu = ({ buttonLabel, products }) => {
    const [modal, setModal] = useState(false);
    const [name, setName] = useState();
    const [chosenProducts, setProducts] = useState();
    let listProducts;

    const toggle = () => setModal(!modal);

    if (products) {
        listProducts = products.map((product, item) =>
            <option key={item} value={product.id}>
                {product.name}
            </option>
        )
    }

    const multipleHandle = (e) => {
        let opts = [],
            opt;
        for (let i = 0, len = e.target.options.length; i < len; i++) {
            opt = e.target.options[i];
            if (opt.selected) {
                opts = [...opts, opt.value];
            }
        }
        setProducts(opts);
    }

    return (
        <Col xs='2'>
            <Button onClick={toggle} block color="success">{buttonLabel}</Button>
            <Modal isOpen={modal} toggle={toggle}>
                <ModalHeader toggle={toggle} />
                <ModalBody>
                    <InputForm name="Name" text="Название" type="text" set={setName} horizontal />
                    <InputForm name="Price" text="Цена" type="number" set={setName} horizontal />
                    <InputForm name="Weight" text="Вес" type="number" set={setName} horizontal />
                    <Input type='select' multiple className="text-capitalize" onChange={e => multipleHandle(e, products, chosenProducts)}>
                        {listProducts}
                    </Input>
                </ModalBody>
                <ModalFooter>
                    <Button color="success" onClick={toggle}>Добавить</Button>{' '}
                </ModalFooter>
            </Modal>
        </Col>
    );
}

export default ModalAddMenu;