## ☕️ Playground for learning Java and Spring Boot

This is my playground for learning and practicing Java and Spring Boot.

---
## ✍︎ Description of the API

- users can sign up and create their own profile.
- users can add interests to their profile.
  - interests are composed of a game and a level for that specific game.
- search functionalities
  - search for all games
  - search for top games

---
## ⌖ ER model

- `Account` has zero or many `Interest`.
  - name, nickname, email, password.
- The same `Interest` can be associated with many `Account` and it has:
  - game, level of that game.


![](src/main/resources/static/diagrams/er-diagram/er-diagram.svg)


## ⎔ Logical model

- An `Account` has many-to-many relationship with`Interest`, so an association table is created.
- An `Interest` has one `Game` and one `Level`.
- A `Game` belongs to one-to-many `Interest`.
- A `Level` belongs to one-to-many `Interest`.

![](src/main/resources/static/diagrams/logical/database.svg)


## ⚙️ Running the API locally

```shell
git clone https://github.com/asterixcode/spring-rest-gamers-api.git
```
```shell
cd spring-rest-gamers-api
```
```shell
mvn clean install
```
```shell
mvn spring-boot:run
```

---
The OpenAPI Swagger documentation can be access at:

- http://localhost:8080/swagger-ui/index.html

---
H2 in-memory database console can be accessed at:

- http://localhost:8080/h2-console/

H2 Login details
```yaml
JDBC URL: jdbc:h2:mem:gamers-db
Username: sa
Password: <empty>
```

## ⚙️ Running the test

```shell
mvn clean test
```


