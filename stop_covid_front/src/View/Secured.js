import { useKeycloak } from "@react-keycloak/web";

const Secured = () => {
  const { keycloak } = useKeycloak();

  if (!keycloak.authenticated) {
    console.log(keycloak);

    return <div>Loading...</div>;
  }
  return (
    <div>
      {keycloak && keycloak.authenticated && (
        <p>
          This is a Keycloak-secured component of your application. You
          shouldn't be able to see this unless you've authenticated with
          Keycloak.
        </p>
      )}
    </div>
  );
};

export default Secured;
