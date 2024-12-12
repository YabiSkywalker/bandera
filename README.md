
<h1>Bandera<br/><a href="https://github.com/YabiSkywalker"></a> <a href="https://www.linkedin.com/in/yabi/">Server Side API</a>
<h2>Overview</h2>
<p>Greetings and salutations,
Bandera is a Java based microservice constructed to serve as a central API for CRUD operations against a MongoDB as well as various AWS services.</p>

<h2>Directory Structure</h2>
src/ ├── main/ │ ├── java/ │ │ ├── com/ │ │ │ └── bandera/ # Main application package │ │ │ ├── controllers/ # Handles HTTP requests and API logic │ │ │ ├── models/ # Data models and entities │ │ │ ├── services/ # Core business logic │ │ │ └── utils/ # Helper classes and utilities │ ├── resources/ │ │ ├── application.properties # Configuration file │ │ └── static/ # Static resources (e.g., HTML, CSS, JS) │ └── webapp/ # Optional: for web-based resources ├── test/ │ ├── java/ │ │ ├── com/ │ │ │ └── bandera/ # Unit and integration tests └── pom.xml (or build.gradle) # Build tool configuration
