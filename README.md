# ReqRes API Test Automation Framework

## Overview

This framework is designed to test the **ReqRes API** using **Cucumber**, **JUnit**, and **RestAssured**. It validates various operations related to user management operations. The tests are written using Cucumber's Gherkin syntax, and the framework is organized to facilitate running API tests efficiently.

## Features

- **User API**:
    - User registration, retrieval, and update functionalities
    - Support for different HTTP methods (GET, POST, PUT)
    - Validation of response structures and status codes
    - Error handling for invalid inputs and missing fields

- **Tags**: Each scenario is tagged with specific categories like `@create`, `@get`, `@update`, `@positive`, and `@negative` to enable selective execution of tests

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Setup](#setup)
3. [Directory Structure](#directory-structure)
4. [Running the Tests](#running-the-tests)
5. [Test Structure](#test-structure)
6. [Configuration](#configuration)
7. [Tagging Convention](#tagging-convention)
8. [Reporting](#reporting)
9. [License](#license)

## Prerequisites

Before running the tests, ensure that the following dependencies are installed:

- **Java 11+**
- **Maven** (to manage dependencies and build the project)
- **Cucumber** (for behavior-driven testing)
- **JUnit** (for running tests)
- **RestAssured** (for making API requests and validating responses)
- **IDE** (e.g., IntelliJ IDEA, Eclipse) for development and test execution

## Setup

1. **Clone the repository:**
    ```bash
    git clone https://github.com/nchadha28107/ReqRes_API_Automation.git
    cd ReqRes_API_Automation
    ```

2. **Install Maven dependencies:**
    ```bash
    mvn clean install
    ```

3. **If you use an IDE, import the project as a Maven project.**

## Directory Structure

```
.
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── reqres
│   │               ├── api
│   │               │   └── UserApi.java
│   │               ├── enums
│   │               │   └── Context.java
│   │               ├── model
│   │               │   └── User.java
│   │               └── utils
│   │                   ├── ConfigReader.java
│   │                   └── ScenarioContext.java
│   │   └── resources
│   │       └── endpoints.yml
│   ├── test
│   │   └── java
│   │       └── com
│   │           └── reqres
│   │               ├── features
│   │               │   └── User.feature
│   │               ├── steps
│   │               │   └── UserSteps.java
│   │               └── runner
│   │                   └── TestRunner.java
│   │   └── resources
│   │       └── extent.properties
```

## Running the Tests

### Using Maven

To run the tests via Maven, use the following command:

```bash
mvn test
```

This command will trigger Cucumber to run all feature files in the project, and the results will be displayed in the terminal.

### Generating Test Reports

You can generate a test report by running:

```bash
mvn clean test
```

This will create a detailed test report under the `target` directory.

## Test Structure

The test cases are written in Cucumber using Gherkin syntax. Each scenario corresponds to a specific API test, with `Given`, `When`, `Then` steps to define the actions and validations.

### Example Scenario

```gherkin
@reqres @api @create @positive
Scenario: TC_1 Successfully create a new user and validate response
  Given I create a user with username "JohnDoe" email "John_Doe@gmail.com" and password "Qwerty@123"
  Then the user creation should be successful
  And the response should have all required fields
  And I store the created user ID
```

- **Given**: Sets up the preconditions (e.g., user creation data)
- **When**: Specifies the action to perform (e.g., making an API call)
- **Then**: Validates the expected outcome (e.g., success or error)

## Configuration

The `endpoints.yml` file allows you to configure environment-specific settings such as the API base URL for each environment and the API endpoints for different resources.

### Example Configuration

```yaml
environments:
  local:
    base_url: "http://localhost:9090"
  mock:
    base_url: "https://mockapi.example.com"
  qa:
    base_url: "https://reqres.in/api"

api:
  key: "YOUR_API_KEY_HERE"  # Replace with your actual API key

User:
  create: "/users"
  get: "/users/"
  update: "/users/"
```

You can switch between environments by passing the environment parameter:

```bash
mvn test -Denv=qa
```

## Tagging Convention

Tests are organized using comprehensive tagging:

### API-Specific Tags
- **@reqres**: All ReqRes API tests
- **@create**: User creation operations
- **@get**: User retrieval operations
- **@update**: User update operations

### Test Type Tags
- **@positive**: Tests expecting successful API responses
- **@negative**: Tests expecting API errors or failures

## Reporting

The test results are generated at `target/cucumber-reports`

- **Cucumber HTML Report:**
  The Cucumber HTML report is generated at `target/cucumber-reports/cucumber.html`. This report provides detailed information about the test execution, including the steps executed, their statuses, and any errors that occurred.

- **Extent Reports:**
    - **Spark HTML Report:** `target/extentReports/SparkReport/Reports/Spark.html`
    - **PDF Report:** `target/extentReports/SparkReport/PdfReport/ExtentPdf.pdf`

These reports provide comprehensive insights into test execution, including pass/fail statistics, execution time, and detailed logs for each test scenario.

## License

This project is licensed under the MIT License.