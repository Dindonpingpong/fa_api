import React, { useEffect, useState } from 'react';
import { useHistory, useParams, withRouter } from 'react-router-dom';
import { Card, CardBody, Container, Row, Col, Button, NavLink } from 'reactstrap';
import InputForm from './InputForm';

const Login = () => {
    const { username, hash } = useParams();
    const [msg, setMsg] = useState(null);
    const names = ["github", "intra"];
    const [login, setLogin] = useState();
    const [password, setPassword] = useState();

    const submit = () => {

        const user = {
            usernameOrEmail: login,
            password: password
        }

        let opts = {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(user)
        }

        fetch("http://localhost:8080/api/auth/signin", opts)
            .then(response => response.json())
    };

    return (
        <section className="login">
            <Container>
                <Row>
                    <Col md={6} className="m-auto">
                        <Card className="mb-4 shadow-sm">
                            <CardBody>
                                <InputForm name="Login" text="Логин или Почта" type="text" set={setLogin} />
                                <InputForm name="Password" text="Пароль" type="password" set={setPassword} />

                                <Col>
                                    <Button className="login-btn" onClick={submit}>Войти</Button>
                                </Col>
                                <Col className="login-btn__link">
                                    <div className="dropdown-divider"></div>
                                    <NavLink href='/register'>Зарегистрироваться</NavLink>
                                </Col>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default Login;
