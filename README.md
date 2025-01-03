# Music Theory Testing Application
## A web application for testing music theory

### Overview

This application consists of a frontend and a backend API. Frontend application is a React SPA using abcjs to render musical notation, and an open api client generator to communicate with the backend. Backend application is a Spring Boot application written in Kotlin.

### Getting started

The top level (i.e. here) directory contains numerous scripts to build the solution. To build this application from fresh, please ensure that you have node version 20 installed, and install `yarn`. Then issue the following commands.

```sh
yarn # Install dependencies needed at the top level
yarn build # Build both front and backend
```

### Running the application

`yarn build` will enable the backend to serve the frontend application, so if you want to run the application without making dev changes, issuing a `yarn start:backend` will start the application running on port 3001: click [here](http://localhost:3001) to start.

### Development

You will need the following installed:
* `nodejs` version 20 (tested with v20.17.0)
* `java` version 21 (tested with Temurin-21.0.4+7)
* Database TODO