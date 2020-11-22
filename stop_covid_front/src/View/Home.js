import React, { useEffect, useState } from "react";
import { useKeycloak } from "@react-keycloak/web";
import { connect } from "react-redux";
import "./css/Home.css";
import {sendLocation} from '../Services/UserAPI'

const Home = (props) => {
  const { keycloak } = useKeycloak();

    const [position, setPosition] = useState({latitude:0 , longitude:0});
  
    useEffect(() => {
        setInterval(() => {
            if (keycloak.authenticated) {
                getLocation().then(() => {
                    if (position.latitude !== 0 && position.longitude !== 0) {
                        sendLocation(keycloak.tokenParsed.username,position.latitude,position.longitude).then(() => {
                            console.log("Position sent : " +
                                "{longitude : " + position.longitude + " , " +
                                "latitude : " + position.latitude + ' }'
                            )
                        })
                    }
                })
            }
        }, 5000 )
    })
    
    
    function showPosition(position) {
        setPosition({latitude: position.coords.latitude, longitude:position.coords.longitude})
    }
    
    async function getLocation () {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition)
        }
        else {
            alert("Location is not available in this browser !!")
        }
    }
    

  //tmp variable waiting for positive parameter
  //const userState = "Contact"
  //const userState = "Positive"



  return (
    <div>
      <h3>
        Bienvenue{" "}
        {keycloak.tokenParsed.given_name +
          " " +
          keycloak.tokenParsed.family_name}
      </h3>
      <div className={"logo"}>
        <img
          src={process.env.PUBLIC_URL + "/img/logo_activated.png"}
          width={"50%"}
        />
        <h6 align={"center"}>Alert covid activé</h6>
      </div>
      {userState === "Ok" ? (
        <div>
          <div className={"myHealthOk"}>
            <h6 align={"center"}>Pas d'exposition à risque détectée</h6>
          </div>
          <div id={"positive"} className={"declareMe"}>
            <div className={"declareMeTitle"}>
              <strong>Me déclarer</strong>
            </div>
            <img
              src={process.env.PUBLIC_URL + "/img/declare.png"}
              width={"50%"}
            />
            <div className={"declareMeText"} align={"center"}>
              Vous avez effectué un test COVID-19 et il est positif ?
            </div>
          </div>

          <button className="btn declareMeButton" onClick={()=>setUserState("Positive")}>Me déclarer positif au Covid-19</button>
        </div>
      ) : userState === "Contact" ? (
        <div>
          <div className={"myHealthContact"}>
            <h6 align={"center"}>
              Vous avez été localisé proche d'une personne positive, veuillez
              vous faire tester
            </h6>
          </div>
          <div className={"declareMe"} id={"contact"}>
            <div className={"declareMeTitle"}>
              <strong>Me déclarer</strong>
            </div>
            <img
              src={process.env.PUBLIC_URL + "/img/declare.png"}
              width={"50%"}
            />
            <div className={"declareMeText"} align={"center"}>
              Vous avez effectué un test COVID-19 et il est positif ?
            </div>
          </div>

          <button className="btn declareMeButton" onClick={()=>setUserState("Positive")}>Me déclarer positif au Covid-19</button>
          <button className="btn declareMeButton" onClick={()=>setUserState("Ok")}>Me déclarer négatif au Covid-19</button>
        </div>
      ) : (
        <div>
          <div className={"myHealthPositive"}>
            <h6 align={"center"}>
              Vous êtes positif au COVID-19, restez chez vous
            </h6>
          </div>
          <div className={"declareMe"} id={"negative"}>
            <div className={"declareMeTitle"}>
              <strong>Me déclarer</strong>
            </div>
            <img
              src={process.env.PUBLIC_URL + "/img/declare.png"}
              width={"50%"}
            />
            <div className={"declareMeText"} align={"center"}>
              Vous avez effectué un test COVID-19 et il est négatif ?
            </div>
          </div>

          <button className="btn declareMeButton" onClick={()=>setUserState("Ok")}>Me déclarer négatif au Covid-19</button>
        </div>
      )}
    </div>
  );
  
};

const mapStateToProps = (state) => {
  console.log(state);
  return {
    connectedUser: state.users.connectedUser,
  };
};

export default connect(mapStateToProps)(Home);
