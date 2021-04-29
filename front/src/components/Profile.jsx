import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import { Card, CardBody, Container, Row, Col, Button, FormGroup, Label, Input } from 'reactstrap';

const InputForm = (props) => {
    const { name, text, type, set } = props;

    return (
        <Col>
            <FormGroup>
                <Label className="font-profile-head">
                    <Row>
                        <Col>
                            {text}
                        </Col>
                        <Col>
                            <Input
                                type={type}
                                name={name}
                                onChange={e => set(e.target.value)}
                                defaultValue="fds"
                                required
                            />
                        </Col>
                    </Row>
                </Label>
            </FormGroup>
        </Col>
    );
}

const Profile = () => {
    const { username, hash } = useParams();
    const [login, setLogin] = useState();
    const [password, setPassword] = useState();

    const submit = () => { };

    if (username && hash) {
        const data = {
            username: username,
            hash: hash
        };

    }

    return (
        <section className="login">
            <Container>
                <Row>
                    <Col md={6} className="m-auto">
                        <Card className="mb-4 shadow-sm">
                            <CardBody>
                                <InputForm name="Login" text="Логин" type="text" set={setLogin} />
                                <InputForm name="Password" text="Почта" type="email" set={setPassword} />
                                <InputForm name="Password" text="Фамилия" type="text" set={setPassword} />
                                <InputForm name="Password" text="Имя" type="text" set={setPassword} />
                                <InputForm name="Password" text="Телефон" type="tel" set={setPassword} />
                                <InputForm name="Password" text="Новый пароль" type="password" set={setPassword} />

                                <Col>
                                    <Button className="login-btn" onClick={submit}>Редактировать</Button>
                                </Col>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default Profile;
