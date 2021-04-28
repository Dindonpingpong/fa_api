import React, { useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Col, Row, Card, CardBody, NavLink, Input } from 'reactstrap';
import InputForm from './InputForm';

const ModalAddOrder = ({setName, setPhone, setOrderTime, clearBasket}) => {
    const [modal, setModal] = useState(false);
    const toggle = () => setModal(!modal);
    const order = () => {
        toggle();
        clearBasket();
    }

    return (
        <Col>
            <Button onClick={toggle} color="success">Оформить заказ</Button>
            <Modal isOpen={modal} toggle={toggle}>
                <ModalHeader toggle={toggle} />
                <ModalBody>
                    <InputForm name="address" text="Адрес доставки" type="text" set={setName} horizontal />
                    <InputForm name="phone" text="Номер телефона" type="tel" set={setPhone} horizontal />
                    <InputForm name="orderTime" text="Время доставки" type="datetime-local" set={setOrderTime} horizontal />

                </ModalBody>
                <ModalFooter>
                    <Button color="success" onClick={order}>Заказать</Button>{' '}
                </ModalFooter>
            </Modal>
        </Col>
    );
}

export default ModalAddOrder;