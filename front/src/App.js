import React, { useState } from 'react';
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import Header from './components/Header';
import Login from './components/Login';
import Register from './components/Register';
import Menu from './components/Menu';
import Basket from './components/Basket';
import Profile from './components/Profile';
import Stat from './components/Stat';

const App = () => {
  const [isLogged, changeLogged] = useState(false);

  return (
    <div>
      <Router>
        <Header />
        <Switch>
          <Route path='/login' component={Login} />
          <Route path='/register' component={Register} />
          <Route path='/basket' component={Basket} />
          <Route path='/profile' component={Profile} />
          <Route path='/stat' component={Stat} />
          <Route path='*' component={Menu} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
