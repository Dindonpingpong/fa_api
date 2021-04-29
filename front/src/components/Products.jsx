import React, { useEffect, useState } from 'react';
import { Container, Row, Col, Button, Table } from 'reactstrap';
import { getAllProducts } from './../utils/api';

const Products = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {

        const getProducts = async () => {
            const res = await getAllProducts();

            if (res.status === 200) {
                setProducts(res.data);
            }
        };

        getProducts();
    }, []);

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
                        <Table hover dark>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Product name</th>
                                    <th><Button color='success'>Добавить</Button></th>
                                </tr>
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
