import React, { useEffect, useState } from 'react';
import { Container, Row, Col, Button, Table, Input, Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import { getAllProducts } from './../utils/api';
import moment from 'moment';
import 'moment/locale/ru';

const mock_orders = [
    {
        "orderId": 14,
        "firstName": "tesfat",
        "lastName": "pafdfas",
        "phone": "+7999-528-4991",
        "address": "Newyota11",
        "orderDate": "2021-04-13T20:08:00",
        "employee": {
            "firstName": null,
            "lastName": null,
            "telephone": null,
            "hireDate": null
        },
        "orderMenuResponseList": [
            {
                "name": "Маргарита",
                "quantity": 3,
                "subtotal": 2700
            },
            {
                "name": "Ветчина и грибы",
                "quantity": 1,
                "subtotal": 445
            }
        ]
    },
    {
        "orderId": 15,
        "firstName": "tesfat",
        "lastName": "pafdfas",
        "phone": "+7999-528-4991",
        "address": "Newyota11",
        "orderDate": "2021-04-13T20:08:00",
        "employee": {
            "firstName": null,
            "lastName": null,
            "telephone": null,
            "hireDate": null
        },
        "orderMenuResponseList": [
            {
                "name": "Маргарита",
                "quantity": 3,
                "subtotal": 2700
            },
            {
                "name": "Ветчина и грибы",
                "quantity": 1,
                "subtotal": 445
            }
        ]
    },
    {
        "orderId": 16,
        "firstName": "tesfat",
        "lastName": "pafdfas",
        "phone": "+7999-528-4991",
        "address": "fdsa",
        "orderDate": "2021-04-29T00:03:00",
        "employee": {
            "firstName": null,
            "lastName": null,
            "telephone": null,
            "hireDate": null
        },
        "orderMenuResponseList": [
            {
                "name": "Пепперони",
                "quantity": 1,
                "subtotal": 395
            },
            {
                "name": "Ветчина и грибы",
                "quantity": 1,
                "subtotal": 345
            }
        ]
    },
    {
        "orderId": 17,
        "firstName": "tesfat",
        "lastName": "pafdfas",
        "phone": "+7999-528-4991",
        "address": "fdsa",
        "orderDate": "2021-04-29T00:03:00",
        "employee": {
            "firstName": null,
            "lastName": null,
            "telephone": null,
            "hireDate": null
        },
        "orderMenuResponseList": [
            {
                "name": "Пепперони",
                "quantity": 1,
                "subtotal": 395
            },
            {
                "name": "Ветчина и грибы",
                "quantity": 1,
                "subtotal": 345
            }
        ]
    }
]

const EmployeeDropdown = ({ employee }) => {
    const { firstName, lastName, telephone } = employee;

    const [dropdownOpen, setDropdownOpen] = useState(false);

    const toggle = () => setDropdownOpen(prevState => !prevState);

    if (firstName === null && lastName === null && telephone === null) {
        return (
            <Button>Назначить курьера</Button>
        );
    }

    return (
        <Dropdown isOpen={dropdownOpen} toggle={toggle}>
            <DropdownToggle>
                {telephone}
            </DropdownToggle>
            <DropdownMenu>
                <DropdownItem text>{`${lastName} ${firstName}`}</DropdownItem>
            </DropdownMenu>
        </Dropdown>
    );
}

const ClientDropdown = ({ firstName, lastName, phone }) => {
    const [dropdownOpen, setDropdownOpen] = useState(false);

    const toggle = () => setDropdownOpen(prevState => !prevState);

    return (
        <Dropdown isOpen={dropdownOpen} toggle={toggle}>
            <DropdownToggle>
                {phone}
            </DropdownToggle>
            <DropdownMenu>
                <DropdownItem text>{`${lastName} ${firstName}`}</DropdownItem>
            </DropdownMenu>
        </Dropdown>
    );
}

const OrderDropdown = ({ orderMenuResponseList }) => {
    const total = orderMenuResponseList.reduce((acc, cur) => acc.subtotal + cur.subtotal);
    const [dropdownOpen, setDropdownOpen] = useState(false);

    const toggle = () => setDropdownOpen(prevState => !prevState);

    const menuList = orderMenuResponseList.map((menu, item) =>
        <DropdownItem key={item} text>{menu.name} x {menu.quantity}</DropdownItem>
    )

    return (
        <Dropdown isOpen={dropdownOpen} toggle={toggle}>
            <DropdownToggle>
                {total} р.
            </DropdownToggle>
            <DropdownMenu>
                {menuList}
            </DropdownMenu>
        </Dropdown>
    );
}

const Orders = () => {
    const [orders, setOrders] = useState(mock_orders);

    useEffect(() => {

        const getOrders = async () => {

        };

        getOrders();
    }, []);

    const listOrders = orders.map(({ orderId, firstName, lastName, phone, address, orderDate, employee, orderMenuResponseList }) => {

        return (
            <tr>
                <th scope="row">{orderId}</th>
                <td>{moment(orderDate).locale('ru').format('DD MMMM, h:mm:ss')}</td>
                <td><ClientDropdown firstName={firstName} lastName={lastName} phone={phone} /></td>
                <td><OrderDropdown orderMenuResponseList={orderMenuResponseList} /></td>
                <td><EmployeeDropdown employee={employee} /></td>
                <td>{address}</td>
                <td></td>
                <td><Button color='danger'>Удалить</Button></td>
            </tr>
        );
    })

    return (
        <section className="login">
            <Container>
                <Row>
                    <Col className="m-auto">
                        <Table hover dark>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Дата заказа</th>
                                    <th>Клиент</th>
                                    <th>Детали заказа</th>
                                    <th>Курьер</th>
                                    <th>Адрес заказа</th>
                                    <th>Статус</th>
                                    <th><Button>Выгрузить в excel</Button></th>
                                </tr>
                            </thead>
                            <tbody>
                                {listOrders}
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default Orders;
