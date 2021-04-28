import React, { useEffect, useState } from 'react';
import { Card, CardBody, Container, Row, Col, Button, FormGroup, Label, Input,
     ListGroup, ListGroupItem, Badge, CardTitle, CardFooter, CardText, CardHeader, Alert } from 'reactstrap';
import ModalAddOrder from './ModalAddOrder';

const RemoveFromBasketButton = ({ id, basket, setBasket }) => {
    const handle = () => {
        let removeIndex = basket.map((item) => item.id).indexOf(id);

        let tmp = basket;
        tmp.splice(removeIndex, 1)
        console.log(tmp);
        setBasket(tmp);
        localStorage.setItem("basket", JSON.stringify(tmp));
        window.location.reload();
    }

    return (
        <Button onClick={handle}>Убрать</Button>
    );
}

const BasketBody = ({ basket, setBasket }) => {
    let listItems = [];
    let mapItems = new Map();

    if (basket) {
        basket.forEach(menu => {
            const { id, name, price } = menu;
            let item;

            if (mapItems.has(id)) {
                item = mapItems.get(id);
                item.subtotal += price;
                item.quantity += 1;
            } else {
                item = new Object();
                item.name = name;
                item.subtotal = price;
                item.quantity = 1;
            }
            mapItems.set(id, item);
        });

        mapItems.forEach(({ name, quantity, subtotal }, key) =>
            listItems.push(<ListGroupItem key={key}>
                <Row>
                    <Col md={8}>
                        {name}
                    </Col>
                    <Col md={2}>
                        <Badge pill>{quantity}</Badge> {subtotal}р
                    </Col>
                    <Col md={2}>
                        <RemoveFromBasketButton
                            id={key}
                            basket={basket}
                            setBasket={setBasket}
                        />
                    </Col>
                </Row>
            </ListGroupItem>)
        );
    }

    return (
        <CardBody>
            <ListGroup>
                {listItems}
            </ListGroup>
        </CardBody>
    )
};

const Basket = () => {
    const [isLogged, setLogged] = useState();
    const [basket, setBasket] = useState([]);
    const [total, setTotal] = useState(0);
    const [name, setName] = useState();
    const [phone, setPhone] = useState();
    const [orderTime, setOrderTime] = useState();

    useEffect(() => {
        const logged = (localStorage.getItem('isLogged') === 'true') ? true : false;
        let basket = localStorage.getItem("basket");

        try {
            basket = (basket) ? JSON.parse(basket) : [];
        } catch {
            basket = [];
        }

        setLogged(logged);
        setBasket(basket);
    }, []);

    useEffect(() => {
        setTotal(basket.reduce((sum, cur) => sum + cur.price, 0));
    }, [basket])

    const clearBasket = () => {
        setBasket([]);
        localStorage.setItem("basket", []);
        window.location.reload();
    }

    return (
        <section className="login">
            <Container>
                <Row>
                    <Col md={12} className="m-auto">
                        <Card className="mb-4 shadow-sm">
                            <CardHeader>
                                {
                                    !isLogged &&
                                    <Alert>Чтобы заказать авторизуйтесь или зарегистрируйтесь</Alert>
                                }
                                <CardTitle tag="h5">Корзина</CardTitle>
                                <CardText>Сумма заказа: {total}</CardText>
                            </CardHeader>
                            <BasketBody
                                basket={basket}
                                setBasket={setBasket}
                            />
                            <CardFooter className="text-muted">
                                <Row>
                                    <Col>
                                        <Button onClick={clearBasket}>Очистить</Button>
                                    </Col>
                                    {
                                        isLogged && basket.length > 0 &&
                                        <ModalAddOrder
                                            setName={setName}
                                            setPhone={setPhone}
                                            setOrderTime={setOrderTime}
                                            clearBasket={clearBasket}
                                        />
                                    }
                                </Row>
                            </CardFooter>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default Basket;
