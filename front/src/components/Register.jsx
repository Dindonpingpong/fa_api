import React, { useState } from 'react';
import { Card, CardBody, Container, Row, Col, Button, FormGroup, Label, Input, FormFeedback } from 'reactstrap';
import { validateEmail, validateFirstName, validateLastName, validateLogin, validatePassword, validatePhone } from './../utils/validators';

const InputForm = ({ name, text, type, set, validate }) => {
    const [isValid, toggleValid] = useState('');
    const [feedback, setFeedback] = useState('');

    const onChange = async (e) => {
        const value = e.target.value;
        const result = await validate(value);
        
        if (result === 'ok') {
            toggleValid('is-valid');
            set(value);
        } else {
            toggleValid('is-invalid');
            setFeedback(result);
        }
    }

    return (
        <Col>
            <FormGroup>
                <Label className="font-profile-head">
                    {text}
                    <Input
                        type={type}
                        name={name}
                        onChange={onChange}
                        className={isValid}
                        required
                    />
                    <FormFeedback>{feedback}</FormFeedback>
                </Label>
            </FormGroup>
        </Col>
    );
}

const Register = (props) => {
    const [login, setLogin] = useState();
    const [password, setPassword] = useState();


    const submit = () => { 

    };

    return (
        <section className="login">
            <Container>
                <Row>
                    <Col md={6} className="m-auto">
                        <Card className="mb-4 shadow-sm">
                            <CardBody>
                                <InputForm name="Login" text="Логин" type="text" set={setLogin} validate={validateLogin}/>
                                <InputForm name="Email" text="Почта" type="email" set={setPassword} validate={validateEmail}/>
                                <InputForm name="lastName" text="Фамилия" type="text" set={setPassword} validate={validateLastName}/>
                                <InputForm name="firstName" text="Имя" type="text" set={setPassword} validate={validateFirstName}/>
                                <InputForm name="phone" text="Телефон" type="tel" set={setPassword} validate={validatePhone}/>
                                <InputForm name="Password" text="Пароль" type="password" set={setPassword} validate={validatePassword}/>

                                <Col>
                                    <Button className="login-btn" onClick={submit}>Зарегистрироваться</Button>
                                </Col>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default Register;
