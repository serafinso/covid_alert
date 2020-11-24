import { useEffect, useState } from "react";
import { useKeycloak } from "@react-keycloak/web";
import "./css/Home.css";
import { getUserById, updateState, sendLocation } from "../Services/UserAPI";
import { getDistanceBetweenPoints } from "../Services/LocationService";

const Home = () => {
    const { keycloak } = useKeycloak();
    const [userState, setUserState] = useState("OK");
    let position = {
        long: 0,
        lat: 0
    };

    useEffect(() => {

        setInterval(() => {
            if (keycloak.authenticated) {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function(positionNav) {
                        console.log(getDistanceBetweenPoints(position.lat, position.long, positionNav.coords.latitude, positionNav.coords.longitude));
                        if (getDistanceBetweenPoints(position.lat, position.long, positionNav.coords.latitude, positionNav.coords.longitude) > 12) {
                            if (positionNav.coords.latitude !== 0 && positionNav.coords.longitude !== 0) {
                                sendLocation(
                                    keycloak.tokenParsed.sub,
                                    positionNav.coords.latitude,
                                    positionNav.coords.longitude
                                ).then(() => {
                                    position = {
                                        long: positionNav.coords.longitude,
                                        lat: positionNav.coords.latitude
                                    };
                                    console.log(
                                        "{longitude : " +
                                        position.long +
                                        " , " +
                                        "latitude : " +
                                        position.lat +
                                        " }"
                                    );
                                });

                            }
                        }
                    })
                    console.log("lol")
                } else {
                    alert("Location is not available in this browser !!");
                }
            }
        }, 30000);
    })

    useEffect(() => {
        getUserById(keycloak.tokenParsed.sub).then((data) => {
            console.log(data.state);
            setUserState(data.state);
        });
    }, [keycloak]);

    useEffect(() => {
        console.log(userState);
        updateState(keycloak.tokenParsed.sub, userState);
    }, [userState]);

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
            {userState === "OK" ? (
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

                    <button
                        className="btn declareMeButton"
                        onClick={() => setUserState("Positive")}
                    >
                        Me déclarer positif au Covid-19
                    </button>
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

                    <button
                        className="btn declareMeButton"
                        onClick={() => setUserState("Positive")}
                    >
                        Me déclarer positif au Covid-19
                    </button>
                    <button
                        className="btn declareMeButton"
                        onClick={() => setUserState("OK")}
                    >
                        Me déclarer négatif au Covid-19
                    </button>
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

                    <button
                        className="btn declareMeButton"
                        onClick={() => setUserState("OK")}
                    >
                        Me déclarer négatif au Covid-19
                    </button>
                </div>
            )}
        </div>
    );
};

export default Home;
