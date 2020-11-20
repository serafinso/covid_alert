import React, { Component, useEffect, useState } from "react";
import "./css/CovidData.css";
import { getNumbers } from "../Services/CovidAPI";
import { Spinner } from "react-bootstrap";

const CovidData = () => {
  const [dataCovid, setDataCovid] = useState({
    casConfirmes: 0,
    nouvellesHospitalisations: 0,
    nouvellesReanimations: 0,
    deces: 0,
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getNumbers()
      .then((data) => {
        setDataCovid(data.FranceGlobalLiveData[0]);
        console.log(data);
        setLoading(false);
      })
      .catch((error) => {
        alert("Impossible de récupérer les données");
        console.log(error);
      });
  }, []);

  return (
    <div>
      <h1 className={"text"}>En France : </h1>
      {dataCovid ? (
        <div className={"text"}>
          <h6>Nombre de cas : {dataCovid.casConfirmes}</h6>
          <h6>Hospitalisés hier : {dataCovid.nouvellesHospitalisations}</h6>
          <h6>Admis en réanimation hier : {dataCovid.nouvellesReanimations}</h6>
          <h6>Nombre de morts : {dataCovid.deces}</h6>

          <div className={"graphCovid"}>
            <iframe
              width={"100%"}
              height={"100%"}
              scrolling={"no"}
              src="https://infogram.com/coronavirus-france-english-1h0r6r5995mw4ek"
              allowFullScreen={true}
            ></iframe>
          </div>
        </div>
      ) : (
        <Spinner animation="border" variant="primary" size="sm" />
      )}
    </div>
  );
};

export default CovidData;
