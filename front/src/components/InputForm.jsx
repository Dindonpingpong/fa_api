import React from 'react';
import { Col, FormGroup, Label, Input, Row } from 'reactstrap';

const Horizontal = ({ name, text, type, set }) => {
    return (
        <Row>
            <Col xs='4'>
                {text}
            </Col>
            <Col xs='8'>
                <Input
                    type={type}
                    name={name}
                    onChange={e => set(e.target.value)}
                    required
                />
            </Col>
        </Row>
    )
}

const Vertical = ({ name, text, type, set }) => {
    return (
        <div>
            { text}
            < Input
                type={type}
                name={name}
                onChange={e => set(e.target.value)}
                required
            />
        </div>
    )
}

const InputForm = ({ name, text, type, set, horizontal }) => {


    return (
        <Col>
            <FormGroup>
                <Label className="font-profile-head">
                    {
                        (horizontal) ?
                            <Horizontal
                                name={name}
                                text={text}
                                type={type}
                                set={set}
                            />
                            :
                            <Vertical
                                name={name}
                                text={text}
                                type={type}
                                set={set}
                            />
                    }
                </Label>
            </FormGroup>
        </Col>
    );
}

export default InputForm;