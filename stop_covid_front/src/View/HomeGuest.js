import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import { getNumbers } from "../Services/CovidAPI";

const HomeGuest = (props) => {
  return (
    <div>
      <h3>Stop Covid</h3>
      <h6>
        Bienvenue sur l'application Stop Covid, veuillez vous connecter pour le
        bon fonctionnement de l'application. Cette dernière nécessite
        d'autoriser le partage de votre position.
      </h6>
    </div>
  );
};

const mapStateToProps = (state) => {
  console.log(state);
  return {
    connectedUser: state.users.connectedUser,
  };
};

export default connect(mapStateToProps)(HomeGuest);
