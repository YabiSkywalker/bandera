
<h1>Bandera<br/><a href="https://github.com/YabiSkywalker"></a> <a href="https://6814-2601-241-8500-5f30-147d-4518-427d-e364.ngrok-free.app/swagger-ui/index.html#/">Server Side API</a>
<h2>Overview</h2>
<p>Greetings and salutations,
Bandera is a Java based microservice constructed to serve as a central API for CRUD operations against a MongoDB as well as various AWS services.</p>

<h2>Directory Structure</h2>


                      └ 📂 bandera
                        └ 📂 .mvn
                        │ └──── 📂 wrapper
                        │         └──── ⚙️ maven-wrapper.properties
                        └ 📂 src
                          └ 📂 main
                            └ 📂 java
                            │   └ 📦 com.example.bandera
                            │               ├──── 🧩 Configuration   ───────────────────────────────────────────────────────────────────────────   # OpenAPI/Swagger, MongoDB and Application config files
                            │               │         └─────────────── ☕ MongoConfig.java
                            │               │         └─────────────── ☕ SwaggerConfig.java
                            │               │         └─────────────── ☕ TestDataInitializer.java
                            │               │         └─────────────── ☕ TicketEvent.java
                            │               │         └─────────────── ☕ TicketStatus.java
                            │               ├──── 🧩 Controllers     ───────────────────────────────────────────────────────────────────────────   # API Endpoint definitions 
                            │               │         └─────────────── ☕ AuthenticationController.java
                            │               │         └─────────────── ☕ CustomerController.java
                            │               │         └─────────────── ☕ EmployeesController.java
                            │               │         └─────────────── ☕ QueryController.java
                            │               │         └─────────────── ☕ TicketController.java
                            │               ├──── 🧩 Entities        ───────────────────────────────────────────────────────────────────────────   # Backend definition of all Entities for Mongo
                            │               │         └─────────────── ☕ AuthorizationEntity.java
                            │               │         └─────────────── ☕ CustomersEntity.java
                            │               │         └─────────────── ☕ EmployeesEntity.java
                            │               │         └─────────────── ☕ ServiceEntity.java
                            │               │         └─────────────── ☕ TicketEntity.java
                            │               │         └─────────────── ☕ VehicleEntity.java
                            │               ├────── 🧩 Repositories    ─────────────────────────────────────────────────────────────────────────   # MongoDB repositories 
                            │               │         └─────────────── ☕ AuthorizationRepository.java
                            │               │         └─────────────── ☕ CustomersRepository.java
                            │               │         └─────────────── ☕ EmployeesRepository.java
                            │               │         └─────────────── ☕ TicketRepository.java
                            │               │         └─────────────── ☕ VehicleRepository.java
                            │               ├──── 🧩 RequestModels   ───────────────────────────────────────────────────────────────────────────   # Data Transfer Objects 
                            │               │         └─────────────── ☕ AuthDTO.java
                            │               │         └─────────────── ☕ CustomerDTO.java
                            │               │         └─────────────── ☕ EmployeeDTO.java
                            │               │         └─────────────── ☕ TicketDTO.java
                            │               │         └─────────────── ☕ VehicleDTO.java
                            │               ├──── 🧩 Secrets         ───────────────────────────────────────────────────────────────────────────   # JWT config classes    
                            │               │         └─────────────── ☕ customOpenAPI.java
                            │               │         └─────────────── ☕ JwtAuthenticationFilter.java
                            │               │         └─────────────── ☕ JwtTokenUtil.java
                            │               │         └─────────────── ☕ PasswordUtil.java
                            │               │         └─────────────── ☕ SwaggerSecurity.java
                            │               ├──── 🧩 Services        ───────────────────────────────────────────────────────────────────────────   # Service classes 
                            │               │         └─────────────── ☕ AuthenticationService.java
                            │               │         └─────────────── ☕ BillingService.java
                            │               │         └─────────────── ☕ CustomerMapper.java
                            │               │         └─────────────── ☕ CustomerService.java
                            │               │         └─────────────── ☕ EmployeeService.java
                            │               │         └─────────────── ☕ Mapper.java
                            │               │         └─────────────── ☕ QueryService.java
                            │               │         └─────────────── ☕ TicketService.java
                            │               │         └─────────────── ☕ VehicleService.java
                            │               └──── ☕ BanderaApplication.java       ────────────────────────────────────────────────────────────────   # Main Method
                            │               └──── ☕ NotificationEventListner.java ────────────────────────────────────────────────────────────────   # Event listener for `status` changes
                            │               └──── ☕ NotificationService.java      ────────────────────────────────────────────────────────────────   # AWS connection for publishing events 
                            └── 📂 resources
                            │   └── 📂 static
                            │   └── 📂 templates
                            │   └── ⚙️ application.properties
