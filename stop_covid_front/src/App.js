import React from "react";
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
import Welcome from "./View/Welcome";
import { PrivateRoute } from "./utilities/PrivateRoute";
import { useKeycloak } from "@react-keycloak/web";

const App = (props) => {
  const { keycloak } = useKeycloak();
  if (!keycloak.authenticated) {
    console.log(keycloak);
    return <div>Loading...</div>;
  } else {
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
                          <Route exact path="/">
                            <Home />
                          </Route>
                          <Route exact path="/">
                            <HomeGuest />
                          </Route>
                          <PrivateRoute
                            exact
                            roles={["user"]}
                            path="/users"
                            component={Welcome}
                          />
                          <PrivateRoute
                            exact
                            roles={["user"]}
                            path="/"
                            component={Welcome}
                          />
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
  }
};

export default App;
