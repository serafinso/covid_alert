import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import history from "../history";
import { create } from "../Services/UserAPI";
import Keycloak from "keycloak-js";

const Register = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [keycloak, setKeycloak] = useState(null);
  const [authenticated, setAuthenticated] = useState(false);

  function validateForm() {
    return (
      email.length > 0 &&
      password.length > 0 &&
      password === confirmPassword &&
      firstName.length > 0 &&
      lastName.length > 0 &&
      phoneNumber.length > 0
    );
  }

  const register = () => {
    if (validateForm()) {
      //DO Register
      //DO LOGIN

      const user = {
        first_name: firstName,
        last_name: lastName,
        email: email,
        phone_number: phoneNumber,
        password: password,
      };

      create(user)
        .then((user) => {
          if (user != null) {
            alert("Account has been created");
            history.push("/");
          } else {
            alert("ERROR : Account has not been created");
          }
        })
        .catch((err) => {
          alert("ERROR : Account has not been created");
          console.log(err);
        });
    }
  };

  useEffect(() => {
    const keycloak = Keycloak("/keycloak.json");
    keycloak.init({ onLoad: "login-required" }).then((authenticated) => {
      setKeycloak(keycloak);
      setAuthenticated(authenticated);
    });
  });

  if (keycloak) {
    if (authenticated)
      return (
        <div>
          <h3>S'enregistrer</h3>

          <div className="form-group">
            <label>Prénom</label>
            <input
              onChange={(event) => setFirstName(event.target.value)}
              type="text"
              className="form-control"
              placeholder="Saisir votre prénom"
              required
            />
          </div>

          <div className="form-group">
            <label>Nom</label>
            <input
              onChange={(event) => setLastName(event.target.value)}
              type="text"
              className="form-control"
              placeholder="Saisir votre nom"
              required
            />
          </div>

          <div className="form-group">
            <label>Téléphone</label>
            <input
              onChange={(event) => setPhoneNumber(event.target.value)}
              type="tel"
              className="form-control"
              placeholder="Saisir votre téléphone"
              required
            />
          </div>

          <div className="form-group">
            <label>Email</label>
            <input
              onChange={(event) => setEmail(event.target.value)}
              type="email"
              className="form-control"
              placeholder="Saisir votre email"
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
            <label>Confirmation mot de passe</label>
            <input
              onChange={(event) => setConfirmPassword(event.target.value)}
              type="password"
              className="form-control"
              placeholder="Confirmer votre mot de passe"
              required
            />
          </div>

          <button onClick={register} className="btn btn-primary btn-block">
            Créer mon compte
          </button>
          <p className="forgot-password text-right">
            Déjà un compte{" "}
            <Link className="sign-in-link" to={"/sign-in"}>
              se connecter ?
            </Link>
          </p>
        </div>
      );
    else return <div>Unable to authenticate!</div>;
  }
  return <div>Initializing Keycloak...</div>;
};

const mapStateToProps = (state) => {
  console.log(state);
  return {
    connectedUser: state.users.connectedUser,
  };
};
export default connect(mapStateToProps)(Register);
