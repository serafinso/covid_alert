import { useEffect, useState } from "react";
import { useKeycloak } from "@react-keycloak/web";
import "./css/Home.css";
import { getUserById, updateState, sendLocation } from "../Services/UserAPI";
import { getDistanceBetweenPoints } from "../Services/LocationService";

const Home = () => {
    const { keycloak } = useKeycloak();
    const [userState, setUserState] = useState("OK");
    const [currentPosition, setCurrentPosition] = useState({ latitude: 0, longitude: 0 });
    const [position, setPosition] = useState({ latitude: 0, longitude: 0 });

    useEffect(() => {
        setInterval(() => {
            if (keycloak.authenticated) {
                getLocation().then(() => {
                    if (position.latitude !== 0 && position.longitude !== 0) {
                        console.log(getDistanceBetweenPoints(position.latitude, position.longitude, currentPosition.latitude, currentPosition.longitude));
                        if (getDistanceBetweenPoints(position.latitude, position.longitude, currentPosition.latitude, currentPosition.longitude) > 10) {
                            sendLocation(
                                keycloak.tokenParsed.sub,
                                position.latitude,
                                position.longitude
                            ).then(() => {
                                console.log({
                                    latitude: position.latitude,
                                    longitude: position.longitude,
                                })
                                setCurrentPosition({
                                    latitude: position.latitude,
                                    longitude: position.longitude,
                                })
                                console.log(
                                    "Position sent : " +
                                    "{longitude : " +
                                    currentPosition.longitude +
                                    " , " +
                                    "latitude : " +
                                    currentPosition.latitude +
                                    " }"
                                );
                                console.log(
                                    "Position sent : " +
                                    "{longitude : " +
                                    position.longitude +
                                    " , " +
                                    "latitude : " +
                                    position.latitude +
                                    " }"
                                );
                            });
                        }
                    }
                });
            }
        }, 5000);
    });

    function showPosition(position) {
        setPosition({
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
        });
    }

    async function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("Location is not available in this browser !!");
        }
    }

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
