import React, { useEffect, useState } from 'react';
import { useHistory, useParams, withRouter } from 'react-router-dom';
import { Card, CardBody, Container, Row, Col, Button, FormGroup, Label, Input, NavLink, ListGroup, ListGroupItem, Badge, CardTitle, CardFooter, CardText, CardHeader, Alert } from 'reactstrap';

const InputForm = (props) => {
    const { name, text, type, set } = props;

    return (
        <Col>
            <FormGroup>
                <Label className="font-profile-head">
                    {text}
                    <Input
                        type={type}
                        name={name}
                        onChange={e => set(e.target.value)}
                        required
                    />
                </Label>
            </FormGroup>
        </Col>
    );
}

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

const Basket = (props) => {
    const isLogged = false;
    const [basket, setBasket] = useState([]);
    const [total, setTotal] = useState(0);

    useEffect(() => {
        const hasBasket = localStorage.getItem("hasBasket");

        if (!hasBasket) {
            localStorage.setItem("hasBasket", true);
        } else {
            setBasket(JSON.parse(localStorage.getItem("basket")));
        }
    }, []);

    useEffect(() => {
        setTotal(basket.reduce((sum, cur) => sum + cur.price, 0));
    }, [basket])

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
                                        <Button>Очистить</Button>
                                    </Col>
                                    {
                                        isLogged &&
                                        <Col>
                                            <Button>Заказать</Button>
                                        </Col>
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
