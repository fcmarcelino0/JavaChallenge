# CalculatorChallenge

A simple and flexible RESTful API that provides the basic functionalities of a calculator.  
This project was solely created as a personal challenge and for learning uses only.


## Features

- Basic arithmetic operations: sum, subtraction, multiplication, division
- 2 modules implemented: "rest" and "calculator"
- Docker(with Kafka and Zookeeper) used.
- Easily extensible for new operations and features



## Getting Started

### Prerequisites

- Maven (Java 17)


### Installation

1. Clone this repository:
   ```sh
   git clone https://github.com/fcmarcelino0/CalculatorChallenge.git
   cd CalculatorChallenge
   ```

2. Install docker container with file 'docker-compose.yml'
   ```sh
   # Commands to use
   docker-compose up -d  -> create container
   docker ps -> check status of container
   docker-compose down -> shut down container
   docker logs kafka -> view logs of kafka
   ```

### Usage

Open your desired IDE  
Run 1st module "CalculatorApplication.java"  
Run 2nd module "RestApplication.java"  

Open Postman   
Copy the API URL "http://localhost:8080/api/calculate/sum?a=1&b=2"   
Change "sum" with the desired operation("sum", "subtraction", "multiplication", or "division")  
Change the values "a" and "b" with your values to use



The result of the API should return 200 OK with the result of the calculation




## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

Created by [@fcmarcelino0](https://github.com/fcmarcelino0). Feel free to reach out for questions or suggestions!
