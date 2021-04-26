import React, { useEffect, useState } from 'react';
import {
    Container, Row, Col, Button, Card, CardBody,
    CardImg, CardTitle, Badge, ListGroup, ListGroupItem, Input, FormGroup
} from 'reactstrap';

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

const ProductList = ({ products }) => {
    let listItems;

    if (products && products.length > 0) {
        listItems = products.map((product, item) =>
            <span key={item}>{product.name}{' '}</span>
        );
    }
    return (
        <ListGroupItem className="product-list">{listItems}</ListGroupItem>
    );
}

const Filter = ({ content, setFilteredContent }) => {
    const [search, setSearch] = useState("");
    const [sortOption, setOption] = useState("priceDesc");

    useEffect(() => {
        let filtered;
        let sortFunc;

        filtered = content.filter((item) => item.name.toLowerCase().includes(search));

        switch (sortOption) {
            case "priceAsc":
                sortFunc = (a, b) => {
                    if (a.price > b.price)
                        return 1;
                    if (a.price < b.price)
                        return -1;
                    return 0;
                };
                break
            case "weightDesc":
                sortFunc = (a, b) => {
                    if (a.weight < b.weight)
                        return 1;
                    if (a.weight > b.weight)
                        return -1;
                    return 0;
                };
                break
            case "weightAsc":
                sortFunc = (a, b) => {
                    if (a.weight > b.weight)
                        return 1;
                    if (a.weight < b.weight)
                        return -1;
                    return 0;
                };
                break
            case "nameAsc":
                sortFunc = (a, b) => {
                    if (a.name > b.name)
                        return 1;
                    if (a.name < b.name)
                        return -1;
                    return 0;
                };
                break
            case "nameAsc":
                sortFunc = (a, b) => {
                    if (a.name < b.name)
                        return 1;
                    if (a.name > b.name)
                        return -1;
                    return 0;
                };
                break
            default:
                sortFunc = (a, b) => {
                    if (a.price < b.price)
                        return 1;
                    if (a.price > b.price)
                        return -1;
                    return 0;
                };
                break
        }

        filtered.sort(sortFunc);

        setFilteredContent(filtered);
    }, [search, sortOption])

    return (
        <Row className="catalog-sort-filter">
            <Col xs='9'>
                <Input
                    type="text"
                    placeholder="search"
                    name="search"
                    onChange={(e) => setSearch(e.target.value)}
                />
            </Col>

            <Col xs='3'>
                <FormGroup>
                    <Input
                        type="select"
                        onChange={(e) => setOption(e.target.value)}
                    >
                        <option value="priceDesc">Цена ↓</option>
                        <option value="priceAsc">Цена ↑</option>
                        <option value="weightDesc">Вес ↓</option>
                        <option value="weightAsc">Вес ↑</option>
                        <option value="nameDesc">Название ↓</option>
                        <option value="nameAsc">Название ↑</option>
                    </Input>
                </FormGroup>
            </Col>
        </Row>
    );
}

const AdditionalInfo = ({ basket, setBasket, id, name, price, weight, status, isAdmin }) => {
    let additionalInfo;

    const addToBasket = () => {
        let orderItem = new Object();
        orderItem.id = id;
        orderItem.name = name;
        orderItem.price = price;
        orderItem.weight = weight;

        setBasket([...basket, orderItem]);
    }

    if (isAdmin && !status) {
        additionalInfo = <Button>Изъять с продажи</Button>;
    } else if (isAdmin && status) {
        additionalInfo = <Button>Вернуть в продажу</Button>;
    } else if (!status) {
        additionalInfo = <Button onClick={addToBasket}>Добавить в корзину</Button>;
    } else {
        additionalInfo = <p>Товар закончился :(</p>
    }

    return (
        <ListGroupItem>
            {additionalInfo}
        </ListGroupItem>
    )
}

const MenuCards = ({ content, basket, setBasket, isAdmin }) => {
    let listItems;

    if (content && content.length > 0) {
        listItems = content.map((menu, item) => {
            const { id, name, price, weight, status, image, productResponseList } = menu;

            const mock_img = "https://img.freepik.com/free-vector/cute-pizza-cartoon-vector-icon-illustration-fast-food-icon-concept-flat-cartoon-style_138676-2588.jpg?size=338&ext=jpg";
            return (
                <Col md={3} key={item}>
                    <Card className="mb-4 text-center">
                        <CardImg top src={mock_img} alt={name} />
                        <CardBody>
                            <CardTitle>
                                {name} <Badge color="danger" pill> {price}р </Badge>
                            </CardTitle>
                            <ListGroup flush>
                                <ListGroupItem>Вес: {weight} гр</ListGroupItem>
                                <ProductList
                                    products={productResponseList}
                                />
                                <AdditionalInfo
                                    id={id}
                                    name={name}
                                    price={price}
                                    weight={weight}
                                    basket={basket}
                                    setBasket={setBasket}
                                    isAdmin={isAdmin}
                                    status={status}
                                />
                            </ListGroup>
                        </CardBody>
                    </Card >
                </Col >)
        });
    }
    return (
        <Row>{listItems}</Row>
    );
}

const Menu = (props) => {
    props = mock;
    const isAdmin = false;

    const [basket, setBasket] = useState([]);
    const [filteredContent, setFilteredContent] = useState();

    useEffect(() => {
        const hasBasket = localStorage.getItem("hasBasket");

        if (hasBasket === 'false') {
            localStorage.setItem("hasBasket", true);
            localStorage.setItem("basket", []);
        } else {
            setBasket(JSON.parse(localStorage.getItem("basket")));
        }
    }, []);

    useEffect(() => {
        localStorage.setItem("basket", JSON.stringify(basket));
    }, [basket])

    return (
        <section className="catalog">
            <Container>
                <Filter
                    content={mock.content}
                    setFilteredContent={setFilteredContent}
                />
                <MenuCards
                    content={filteredContent || mock.content}
                    basket={basket}
                    setBasket={setBasket}
                    isAdmin={isAdmin}
                />
            </Container>
        </section>
    )
}

export default Menu;