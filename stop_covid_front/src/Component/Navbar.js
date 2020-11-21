import React from "react";
import "./css/Navbar.css";
import { Link } from "react-router-dom";
import { useKeycloak } from "@react-keycloak/web";
import AuthorizedFunction from "../Component/AuthorizedFunction";
import { Button } from "react-bootstrap";

const Navbar = () => {
  const { keycloak } = useKeycloak();
  return (
      <nav className="navbar navbar-expand-lg navbar-light fixed-top">
        <div className="container">
          <Link className="navbar-brand" to={"/"}>
            <img src={process.env.PUBLIC_URL + "/img/logo.jpg"} height={"100%"} />
          </Link>
          <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul className="navbar-nav ml-auto">
              {AuthorizedFunction(["user"]) && (
                  <li>
                    <Button variant="link">
                      <Link to="/users">All users</Link>
                    </Button>
                  </li>
              )}
              {AuthorizedFunction(["user"]) && (
                  <li>
                    <Button variant="link">
                      <Link to="/">public component</Link>
                    </Button>
                  </li>
              )}
              {AuthorizedFunction(["admin"]) && (
                  <li>
                    <Button variant="link">
                      <Link to="/secured">secured component</Link>
                    </Button>
                  </li>
              )}
              {keycloak && !keycloak.authenticated && (
                  <li>
                    <Button
                        className="btn-link-custom"
                        onClick={() => keycloak.init({ onLoad: "login-required" })}
                    >
                      Se connecter / S'inscrire
                    </Button>
                  </li>
              )}
              {keycloak && keycloak.authenticated && (
                  <Button
                      className="btn-link-custom"
                      onClick={() => keycloak.logout()}
                  >
                    Se déconnecter ({keycloak.tokenParsed.preferred_username})
                  </Button>
              )}
            </ul>
          </div>
        </div>
      </nav>
  );
};

export default Navbar;
