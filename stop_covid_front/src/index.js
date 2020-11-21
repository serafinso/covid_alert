import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import "bootstrap/dist/css/bootstrap.min.css";

// Config file
import * as serviceWorker from "./serviceWorker";

import { ReactKeycloakProvider } from "@react-keycloak/web";
import { keycloak } from "./keycloak";
import { store } from "./Store/store";
import { Provider } from "react-redux";

ReactDOM.render(
  <React.StrictMode>
    <ReactKeycloakProvider
      authClient={keycloak.instance}
    >
      <Provider store={store}>
        <App />
      </Provider>
    </ReactKeycloakProvider>
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
