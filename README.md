# Application Covid Alert

## Projet IWA - IG5 2020

Debeir Luca, Haas Matthew, Serafin Solène, Temil Clément

Application Spring Boot multi services.

Technologies utilisées :
- Backend : Java, Spring boot, Gradle, Docker
- Frontend : React, Redux


Pour lancer l'application :
- dans Gradle, dans le dossier Tasks de covid_alert, lancer `build`
- ouvrez un terminal, et à la racine, lancer la commande `docker-compose up --build`
- une fois tous les conteneurs Docker lancés, vous pourrez accéder à l'application via cette URL : `localhost:3000`

Pour accéder ensuite à la plateforme Keycloak en tant qu'administrateur : `localhost:8080/auth`. 
Ensuite tapez le username/password suivant : `admin/admin`. Vous aurez alors accès au domaine *covid-alert* !
