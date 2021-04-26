import React, { useEffect } from "react";
import { withRouter, useHistory } from 'react-router-dom';
import { Container, Navbar, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';
import "./../index.css";

const Header = (props) => {
    const isLogged = true;
    const isAdmin = false;

    return (
        <header className="header">
            <Container>
                <Navbar color="light" light expand="xs">
                    <NavbarBrand href="/">Pizza</NavbarBrand>
                    <Nav className="ml-auto" navbar>
                        <Navbar>
                            <NavItem>
                                <NavLink href="/">Меню</NavLink>
                            </NavItem>
                            {
                                !isAdmin &&
                                <NavItem>
                                    <NavLink href="/basket/">Корзина</NavLink>
                                </NavItem>
                            }
                            {
                                isLogged && !isAdmin &&
                                <NavItem>
                                    <NavLink href="/profile/">Профиль</NavLink>
                                </NavItem>
                            }
                            {
                                isLogged  &&
                                <NavItem>
                                    <NavLink href="/stat/">Статистика</NavLink>
                                </NavItem>
                            }
                            <NavItem>
                                <NavLink href="/about/">Об авторе</NavLink>
                            </NavItem>
                            {
                                isAdmin &&
                                <NavItem>
                                    <NavLink href='/products'>
                                        Продукты
                                    </NavLink>
                                </NavItem>
                            }
                            {
                                isAdmin &&
                                <NavItem>
                                    <NavLink href='/products'>
                                        Заказы
                                    </NavLink>
                                </NavItem>
                            }
                            {
                                isLogged &&
                                <NavItem>
                                    <NavLink href='/login'>
                                        Выход
                                    </NavLink>
                                </NavItem>
                            }
                            {
                                !isLogged &&
                                <NavItem>
                                    <NavLink href='/login'>
                                        Войти
                                    </NavLink>
                                </NavItem>
                            }
                        </Navbar>
                    </Nav>
                </Navbar>
            </Container>
        </header>
    );
}

export default Header;
