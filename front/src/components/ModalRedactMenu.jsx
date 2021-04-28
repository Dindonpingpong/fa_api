import React, { useEffect, useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Col, Row, Card, CardBody, NavLink, Input, Label } from 'reactstrap';
import InputForm from './InputForm';

const mock = [
    {
        "id": 1,
        "name": "помидор"
    },
    {
        "id": 2,
        "name": "тесто"
    }
]


const ModalAddRedactMenu = ({ defaultName, defaultPrice, defaultWeight, defaultProducts, clearBasket }) => {
    const products = mock;
    const [modal, setModal] = useState(false);
    const [chosenProducts, setProducts] = useState();
    const [defaultChosen, setChosen] = useState();
    const [name, setName] = useState(defaultName);
    const [price, setPrice] = useState(defaultPrice);
    const [weight, setWeight] = useState(defaultWeight);
    const toggle = () => setModal(!modal);

    const order = () => {
        toggle();
        clearBasket();
    }
    
    useEffect(() => {
        setChosen(defaultProducts.map((product) => product.id.toString()));
    }, [])

    const listProducts = products.map((product, item) =>
        <option key={item} value={product.id}>
            {product.name}
        </option>
    )
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
        <Col>
            <Button onClick={toggle} color="success" block>Редактировать</Button>
            <Modal isOpen={modal} toggle={toggle}>
                <ModalHeader toggle={toggle} />
                <ModalBody>
                    <InputForm name="name" text="Название" type="text" set={setName} defaultValue={defaultName} horizontal />
                    <InputForm name="price" text="Стоимость" type="number" set={setPrice} defaultValue={defaultPrice} horizontal />
                    <InputForm name="weight" text="Вес" type="number" set={setWeight} defaultValue={defaultWeight} horizontal />
                    <Label className="font-profile-head">Ингридиенты</Label>
                    <Input type='select' defaultValue={defaultChosen} multiple className="text-capitalize" onChange={e => multipleHandle(e, products, chosenProducts)}>
                        {listProducts}
                    </Input>

                </ModalBody>
                <ModalFooter>
                    <Button color="success" onClick={order}>Сохранить</Button>{' '}
                </ModalFooter>
            </Modal>
        </Col>
    );
}

export default ModalAddRedactMenu;