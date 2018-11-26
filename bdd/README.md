Blood Pressure Category API BDD (Behavior-Driven Development) Testing

Intro
--------
BDD tests for the [bp-category-api](https://github.com/galvo/msc-devops-bp-category-api/tree/master/app) service. 

Running the Tests
--------

### Command Line Properties

| Command Line Property  | Required | Description |
| -----------------------|----------|----------|
| host                   | Yes      | Host Name of the bp-category-api service |
| port                   | Yes      | Port number of the bp-category-api service |
| basePath               | Yes      | Base Path (URL) of the bp-category-api service |

### Run Tests
```bash
mvn clean verify -Dhost=localhost -Dport=80 -DbasePath=/blah
```

The tests can be run against any bp-category-api service by configuring the required properties defined above.



