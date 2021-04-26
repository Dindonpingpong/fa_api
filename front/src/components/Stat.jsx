import React, { useEffect, useState } from 'react';
import { useHistory, useParams, withRouter } from 'react-router-dom';
import { Card, CardBody, Container, Row, Col, Button, FormGroup, Label, Input, NavLink } from 'reactstrap';

import {
    XYPlot,
    XAxis,
    YAxis,
    VerticalGridLines,
    HorizontalGridLines,
    VerticalBarSeries,
    DiscreteColorLegend
} from 'react-vis';

const BarChar = ({ prices, weights }) => {
    return (
        <XYPlot margin={{ bottom: 70 }} xType="ordinal" width={1100} height={450}>
            <VerticalGridLines />
            <HorizontalGridLines />
            <DiscreteColorLegend
                style={{ position: 'absolute', left: '60px', top: '10px' }}
                orientation="horizontal"
                items={[
                    {
                        title: 'Цена',
                        color: '#12939A'
                    },
                    {
                        title: 'Вес',
                        color: '#79C7E3'
                    }
                ]}
            />
            <XAxis tickLabelAngle={-90} />
            <YAxis />
            <VerticalBarSeries
                data={prices}
            />
            <VerticalBarSeries
                data={weights}
            />
        </XYPlot>
    );
}

const Stat = () => {
    const [prices, setPrices] = useState([]);
    const [weights, setWeights] = useState([]);

    const mock = [
        { x: 'Apples', y: 12 },
        { x: 'Bananas', y: 2 },
        { x: 'Cranberries', y: 11 }
    ]

    useEffect(() => {
        const hasBasket = localStorage.getItem("hasBasket");

        if (!hasBasket) {
            localStorage.setItem("hasBasket", true);
        } else {
            // const basket = JSON.parse(localStorage.getItem("basket"));

            const mock = {
                "content": [
                    {
                        "id": 18,
                        "name": "Чиззи чеддер",
                        "price": 395,
                        "weight": 480,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 17,
                        "name": "Чизбургер-пицца",
                        "price": 395,
                        "weight": 660,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 8,
                        "name": "Четыре сыра",
                        "price": 445,
                        "weight": 550,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 13,
                        "name": "Четыре сезона",
                        "price": 395,
                        "weight": 640,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 6,
                        "name": "Цыпленок блю чиз",
                        "price": 445,
                        "weight": 610,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 7,
                        "name": "Цыпленок барбекю",
                        "price": 445,
                        "weight": 660,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 16,
                        "name": "Сырный цыпленок",
                        "price": 395,
                        "weight": 620,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 14,
                        "name": "Песто",
                        "price": 445,
                        "weight": 610,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 1,
                        "name": "Пепперони",
                        "price": 395,
                        "weight": 570,
                        "status": false,
                        "productResponseList": [
                            {
                                "id": 3,
                                "name": "Моцарелла"
                            },
                            {
                                "id": 6,
                                "name": "Итальянские травы"
                            },
                            {
                                "id": 2,
                                "name": "Ветчина"
                            }
                        ]
                    },
                    {
                        "id": 9,
                        "name": "Мясная",
                        "price": 445,
                        "weight": 600,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 12,
                        "name": "Мексиканская",
                        "price": 445,
                        "weight": 710,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 2,
                        "name": "Маргарита",
                        "price": 345,
                        "weight": 620,
                        "status": false,
                        "productResponseList": [
                            {
                                "id": 3,
                                "name": "Моцарелла"
                            },
                            {
                                "id": 6,
                                "name": "Итальянские травы"
                            },
                            {
                                "id": 1,
                                "name": "Пепперони"
                            }
                        ]
                    },
                    {
                        "id": 5,
                        "name": "Кисло-сладкий цыпленок",
                        "price": 295,
                        "weight": 540,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 11,
                        "name": "Карбонара",
                        "price": 395,
                        "weight": 610,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 10,
                        "name": "Гавайская",
                        "price": 395,
                        "weight": 650,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 4,
                        "name": "Ветчина и сыр",
                        "price": 295,
                        "weight": 500,
                        "status": false,
                        "productResponseList": [
                            {
                                "id": 3,
                                "name": "Моцарелла"
                            },
                            {
                                "id": 1,
                                "name": "Пепперони"
                            }
                        ]
                    },
                    {
                        "id": 3,
                        "name": "Ветчина и грибы",
                        "price": 345,
                        "weight": 600,
                        "status": false,
                        "productResponseList": []
                    },
                    {
                        "id": 15,
                        "name": "Аррива!",
                        "price": 395,
                        "weight": 590,
                        "status": false,
                        "productResponseList": []
                    }
                ],
                "page": 0,
                "size": 30,
                "totalElements": 18,
                "totalPages": 1,
                "last": true
            }

            let basket = mock.content;
            const prices = basket.map((item) => {
                return {
                    x: item.name,
                    y: item.price
                }
            })
            const weights = basket.map((item) => {
                return {
                    x: item.name,
                    y: item.weight
                }
            })
            setPrices(prices);
            setWeights(weights);
        }
    }, []);

    return (
        <section className="login">
            <Container>
                <Row>
                    <Col md={12} className="m-auto">
                        <Card className="mb-4 shadow-sm">
                            <CardBody>
                                <BarChar
                                    prices={prices}
                                    weights={weights}
                                />
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default Stat;
