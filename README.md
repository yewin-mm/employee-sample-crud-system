<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/yewin-mm/employee-sample-crud-system.svg?style=for-the-badge
[contributors-url]: https://github.com/yewin-mm/employee-sample-crud-system/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/yewin-mm/employee-sample-crud-system.svg?style=for-the-badge
[forks-url]: https://github.com/yewin-mm/employee-sample-crud-system/network/members
[stars-shield]: https://img.shields.io/github/stars/yewin-mm/employee-sample-crud-system.svg?style=for-the-badge
[stars-url]: https://github.com/yewin-mm/employee-sample-crud-system/stargazers
[issues-shield]: https://img.shields.io/github/issues/yewin-mm/employee-sample-crud-system.svg?style=for-the-badge
[issues-url]: https://github.com/yewin-mm/employee-sample-crud-system/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/ye-win-1a33a292/
[product-screenshot]: images/screenshot.png

# employee-sample-crud-system
* This is the sample backend microservice project for Employee Management System by using spring boot and spring data jpa.

<!-- TABLE OF CONTENTS -->
## Table of Contents
- [About The Project](#about-the-project)
    - [Built With](#built-with)
- [Getting Started](#getting-started)
    - [Before you begin](#before-you-begin)
    - [Clone Project](#clone-project)
    - [Prerequisites](#prerequisites)
    - [Instruction](#instruction)
- [Contact Me](#contact)
- [Contributing](#Contributing)


## About The Project
This is the sample backend microservice project for join table by using spring data jpa.
You can learn Spring boot CRUD with sample employee management system.


### Built With
This project is built with
* [Java](https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Postgresql Database](https://www.postgresql.org/download/)


## Getting Started
This project is built with java, maven, postgresql and use project lombok as plugin.
So, please make sure all are installed in you machine.

### Before you begin
If you are new in Git, Github and new in Spring Boot configuration structure, <br>
You should see basic details instruction first in here
[Spring Boot Application Instruction](https://github.com/yewin-mm/spring-boot-app-instruction) <br>
If you are not good enough in basic JPA CRUD knowledge with Java Spring Boot, you should learn below example projects first. <br>
Click below links.
* [Spring Boot Sample CRUD Application](https://github.com/yewin-mm/spring-boot-sample-crud)


If you already knew above links and you have good enough basic knowledge with Java Spring Boot, you can keep learn this project and see the "Instruction" below.


### Clone Project
* Clone the repo
   ```sh
   git clone https://github.com/yewin-mm/employee-sample-crud-system.git
  
### Prerequisites
Prerequisites can be found in here [Spring Boot Application Instruction](https://github.com/yewin-mm/spring-boot-app-instruction).


### Instruction
* Change your database username and password in `application.properties`.
* Run the project in your IDE. Please make sure application was successfully running.
* You can check in your database is that there has 'Role and User' table were auto created or not, under postgres schema by database GUI tools like ***DBeaver***.
* Insert some data into 'Role' table in your database by querying in database console.
* Find 'Role' table under postgres schema which will auto create by running application. 
* If you can't find, just click refresh and see again under postgres schema.
* You can add by clicking '+' sign in your 'Role' table ***or*** you can run below query in DBeaver console (please go Execute SQL Statement in your DBeaver) like
  ```sh 
  insert into role (id, role_name) values (1, 'admin'); insert into role (id, role_name) values (2, 'normal user');

* Import `employee-crud-sample-postman-api-request.json` file under project directory (see that file in project directory) into your installed Postman application.
    * Click in your Postman (top left corner) import -> file -> upload files -> {choose that json file} and open/import.
    * After that, you can see the folder which you import from file in your Postman.
    
* Now, you can 'Test' this project by calling API from Postman.
    * Open your imported folder in postman and inside this folder, you will see total of 3 API requests and you can test it all by clicking `Send` button and see the response.
    * Before testing, please make sure some data should be in `Role` table first. 
    * You can check data in database too (here you can check data in tables (User, Role) by DBeaver tools or you can manually select query in your database console)
    
* After that you can see the code and check the code which you don't know. You can learn it and you can apply in your job or study fields.

***Have Fun and Enjoy in Learning Code***

## Contact
Name - Ye Win <br> LinkedIn profile -  [Ye Win's LinkedIn](https://www.linkedin.com/in/ye-win-1a33a292/)  <br> Email Address - yewin.mmr@gmail.com

Project Link: [Spring Boot Employee Sample CRUD System](https://github.com/yewin-mm/employee-sample-crud-system)


## Contributing
Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.
<br>If you want to contribute....
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/yourname`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push -u origin feature/yourname`)
5. Open a Pull Request

