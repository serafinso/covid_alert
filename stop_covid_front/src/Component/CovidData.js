import React, { Component, useEffect, useState } from "react";
import "./css/CovidData.css";
import { getNumbers } from "../Services/CovidAPI";
import { Spinner } from "react-bootstrap";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

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
      <h1 className={"text title"} >En France : </h1>
      {dataCovid ? (
          <div>
          <Container className={"dataContainer"}>
            <Row>
              <Col xs={6}>
                <div className={"text"}>
                  <h6>Nombre de cas : </h6>
                  <h6>Hospitalisés hier : </h6>
                  <h6>Admis en réanimation hier : </h6>
                  <h6>Nombre de morts : </h6>
                </div>
              </Col>
              <Col xs={6}>
                <div className={"text"}>
                  <h6>{dataCovid.casConfirmes} personnes</h6>
                  <h6>{dataCovid.nouvellesHospitalisations} personnes</h6>
                  <h6>{dataCovid.nouvellesReanimations} personnes</h6>
                  <h6>{dataCovid.deces} personnes</h6>
                </div>
              </Col>
            </Row>
          </Container>
            <div className={"infos"}>
              <img
                  src={process.env.PUBLIC_URL + "/img/consignes.png"}
                  width={"100%"}
              />
            </div>
          </div>
        
      ) : (
        <Spinner animation="border" variant="primary" size="sm" />
      )}
    </div>
  );
};

export default CovidData;
