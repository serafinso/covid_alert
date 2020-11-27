import React, {useEffect} from 'react';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { Router, Switch, Route } from "react-router-dom";
import history from "./history";
import { Container, Row, Col } from "react-bootstrap";

import Home from "./View/Home";
import Navbar from "./Component/Navbar";
import HomeGuest from "./View/HomeGuest";
import CovidData from "./Component/CovidData";
import Secured from "./View/Secured";
import { PrivateRoute } from "./utilities/PrivateRoute";
import { useKeycloak } from "@react-keycloak/web";

const App = () => {

  useEffect(() => {
    document.title = 'Covid Alert';
  })
  
  const { keycloak } = useKeycloak();
  return (
      <Router history={history}>
        <div className="App">
          <Navbar />
          <div className="viewContainer">
            <Container className="mainContainer">
              <Row>
                <Col xs={12} sm={6}>
                  <div>
                    <div className="auth-wrapper">
                      <div className="auth-inner">
                        <Switch>
                          {keycloak.authenticated && (
                              <Route exact path="/">
                                <Home />
                              </Route>
                          )}
                          {!keycloak.authenticated && (
                              <Route exact path="/">
                                <HomeGuest />
                              </Route>
                          )}
                          <PrivateRoute
                              roles={["admin"]}
                              path="/secured"
                              component={Secured}
                          />
                        </Switch>
                      </div>
                    </div>
                  </div>
                </Col>
                <Col xs={12} sm={6}>
                  <div>
                    <CovidData />
                  </div>
                </Col>
              </Row>
            </Container>
          </div>
        </div>
      </Router>
  );
};

export default App;
