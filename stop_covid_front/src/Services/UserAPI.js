/*################################################################################################
 *
 * This file is the API, the link between Views and our server
 * For each function, we do a request by URL with a specific method :
 *   - GET : To get some informations (list...) with params in the URL
 *   - POST : To post new values with params in the body
 *   - PUT : To modify some values with params in the body
 *   - DELETE : To delete items
 *
 * For tests, use the localhost URL
 * ################################################################################################*/

/* This function is to create a user with POST */

export function create(user) {
  const url = "https://covid-alert-dhst.herokuapp.com/api/v1/users";
  const data = JSON.stringify(user);
  return fetch(url, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: data,
  })
    .then((response) => response.json())
    .catch((error) => console.log(error));
}

/* This function is to get one user from the database by his ID with GET */

export function getUserById(id) {
  const url = "http://localhost:8000/users/" + id;
  return fetch(url, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  })
      .then((response) => response.json())
      .catch((error) => console.log(error));
}

/* This function is to get all users from the database with GET */

export function getAllUsers() {
  const url = "http://localhost:8000/api/v1/users";
  let headers = new Headers();

  headers.append("Content-Type", "application/json");
  headers.append("Accept", "application/json");
  headers.append("Origin", "http://localhost:3000");
  return fetch(url, {
    mode: "cors",
    method: "GET",
    headers: headers,
  })
    .then((response) => response.json())
    .catch((error) => console.log(error));
}

/* This function is to login with POST
 * We use a post to secure datas as password */

export function login(user) {
  const url =
    "http://localhost:8080/auth/realms/covid-alert/protocol/openid-connect/token";
  const data = JSON.stringify(user);
  console.log(data);

  return fetch(url, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: data,
  })
    .then((response) => console.log(response))
    .catch((error) => console.log(error));
}

/* This function is to update a branch with PUT */

export function updateState(id, state) {
  const url = "http://localhost:8000/users/" + id + "?state=" + state;
  return fetch(url, {
    method: "PUT",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      "Access-Control-Origin": "*",
    },
  })
      .then((response) => console.log(response))
      .catch((error) => console.log(error));
}

/* This function is to delete a branch with DELETE */

export function deleteUser(user) {
  const url = "http://localhost:5000/users/delete";
  const data = JSON.stringify(user);
  return fetch(url, {
    method: "DELETE",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: data,
  })
    .then((response) => response.json())
    .catch((error) => console.log(error));
}

/* This function is to reset the password of a user
 * His new password is sent by mail */

export function resetPassword(user) {
  const url = "http://localhost:5000/users/reset-password";
  const data = JSON.stringify(user);
  return fetch(url, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: data,
  })
    .then((response) => response.json())
    .catch((error) => console.log(error));
}




/* This function is to send location to Kafka */

export function sendLocation(userId, latitude, longitude) {
  const url = "http://localhost:8083/publish";
  console.log(JSON.stringify({ userId: userId, latitude: latitude, longitude: longitude }));
  return fetch(url, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      "Access-Control-Origin": "*",
    },
    body: JSON.stringify({ userId: userId, latitude: latitude, longitude: longitude }),
  })
      .then((response) => {
        if(response.status === 200){
          console.log("Localisation envoyée !")
        }else{
          throw "error"
        }
      });
}
