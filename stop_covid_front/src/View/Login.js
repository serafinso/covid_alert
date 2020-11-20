import React, { useEffect, useState } from "react";
import "./css/Login.css";
import { connect } from "react-redux";
import { connect } from "react-redux";
import { useKeycloak } from "@react-keycloak/web";

const Login = (props) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [position, setPosition] = useState({ latitude: 0, longitude: 0 });

  useEffect(() => {
    getLocation();
  }, []);

  function validateForm() {
    return username.length > 0 && password.length > 0;
  }

  function showPosition(position) {
    setPosition({
      latitude: position.coords.latitude,
      longitude: position.coords.longitude,
    });
  }

  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(showPosition);
    } else {
      alert("Location is not available in this browser !!");
    }
  }

  const loginAction = () => {
    if (validateForm()) {
      //DO LOGIN
      // TO IMPLEMENT
      const user = {
        username: username,
        password: password,
        client_id: "client_01",
        grant_type: "password",
        client_secret: "33e4efff-18ef-4a70-b5aa-d087e81429fc",
        scope: "openid",
      };
      /*login(user).then((user) => {
        const action = { type: "TOGGLE_CONNECTED_USER", connectedUser: user };
        props.dispatch(action);
        history.push("/");
      });*/

      /* TEMP */
      /*const user = {
        email: email,
        firstName: "Clément",
        lastName: "Temil",
        phoneNumber: "0670220473",
      };
      const action = { type: "TOGGLE_CONNECTED_USER", connectedUser: user };
      props.dispatch(action);
      history.push("/");*/
    } else {
      alert("Error");
    }
  };

  const { keycloak } = useKeycloak();
  if (!keycloak.authenticated) {
    console.log(keycloak);
    return <div>loading</div>;
  }
  return (
    <div>
      <div>
        {keycloak && keycloak.authenticated && (
          <button className="btn-link" onClick={() => keycloak.logout()}>
            Logout ({keycloak.tokenParsed.preferred_username})
          </button>
        )}
      </div>
      <h3>Se connecter</h3>

      <div className="form-group">
        <label>Username</label>
        <input
          onChange={(event) => setUsername(event.target.value)}
          type="text"
          className="form-control"
          placeholder="Saisir votre pseudo"
          required
        />
      </div>

      <div className="form-group">
        <label>Mot de passe</label>
        <input
          onChange={(event) => setPassword(event.target.value)}
          type="password"
          className="form-control"
          placeholder="Saisir votre mot de passe"
          required
        />
      </div>

      <div className="form-group">
        <div className="custom-control custom-checkbox">
          <input
            type="checkbox"
            className="custom-control-input"
            id="customCheck1"
          />
          <label className="custom-control-label" htmlFor="customCheck1">
            Se souvenir de moi
          </label>
        </div>
      </div>

      <button onClick={loginAction} className="btn btn-primary btn-block">
        Se connecter
      </button>
      <p className="forgot-password text-right">
        Mot de passe <a href="#">oublié ?</a>
      </p>
    </div>
  );
};

const mapStateToProps = (state) => {
  console.log(state);
  return {
    connectedUser: state.users.connectedUser,
  };
};

export default connect(mapStateToProps)(Login);
