# GitLogViewer

GitLogViewer is a client-server application displaying Git repository logs in a web browser.

## Server Setup

cd src/main/kotlin/com/gitRepository

### Command to build the Docker image

docker build -t gitlogviewer-mariadb .

### Command to run the Docker container

docker run -d -p 3307:3306 --name gitlogviewer-mariadb-container gitlogviewer-mariadb

### To run

```bash
mvn spring-boot:run
```

## Client Setup

cd src/app

```bash
npm install
```

### To run

```bash
ng serve
```

![GitLogViewer Screenshot 1](<screenshots/Screenshot%20(1).png>)
![GitLogViewer Screenshot 2](<screenshots/Screenshot%20(2).png>)
![GitLogViewer Screenshot 3](<screenshots/Screenshot%20(3).png>)
