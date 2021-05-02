import React, { useEffect, useState } from 'react';
import { Container, Row, Col, Button, Table, Input } from 'reactstrap';
import { getAllProducts } from './../utils/api';
import Info from './Info';
import { createProduct } from './../utils/api';

const ProductAddRow = ({ products, setMessage, setSuccess }) => {
    const [name, setName] = useState();

    const create = async () => {
        if (name.length > 0) {
            const checkExist = products.filter((product) => product.name === name);

            if (checkExist.length > 0) {
                setMessage("Такой продукт уже есть в наличии");
                setSuccess(false);
            } else {
                const res = await createProduct(name);

                if (res.status === 201) {
                    setMessage("Продукт добавлен");
                    setSuccess(true);
                }
            }
        }
    }

    return (
        <tr>
            <th>Новый</th>
            <th><Input onChange={(e) => setName(e.target.value)} /></th>
            <th><Button color='success' onClick={create}>Добавить</Button></th>
        </tr>
    );
}

const Products = () => {
    const [products, setProducts] = useState([]);
    const [message, setMessage] = useState();
    const [success, setSuccess] = useState(true);

    useEffect(() => {

        const getProducts = async () => {
            const res = await getAllProducts();

            if (res.status === 200) {
                setProducts(res.data);
            }
        };

        getProducts();
    }, [message]);

    const listProducts = products.map((products) => <tr>
        <th scope="row">{products.id}</th>
        <td>{products.name}</td>
        <td><Button color='danger'>Удалить</Button></td>
    </tr>)

    return (
        <section className="login">
            <Container>
                <Row>
                    <Col md={12} className="m-auto">
                        {
                            message &&
                            <Info
                                styled={false}
                                isSuccess={success}
                                message={message}
                                setMessage={setMessage}
                            />
                        }
                        <Table hover dark>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Product name</th>

                                    <th>
                                        <Button>Выгрузить в excel</Button>
                                    </th>
                                </tr>
                                <ProductAddRow products={products} setMessage={setMessage} setSuccess={setSuccess} />
                            </thead>
                            <tbody>
                                {listProducts}
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default Products;
