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

<h1 align="center">
  Overview
  <img src="https://github.com/yewin-mm/employee-sample-crud-system/blob/master/github/template/images/overview/employee_mgmt_system.png" /><br/>
</h1>

# employee-sample-crud-system
* This is the sample backend microservice project for Employee Management System by using Spring boot + Spring data jpa + MySQL. 

<!-- TABLE OF CONTENTS -->
## Table of Contents
- [About The Project](#about-the-project)
    - [Built With](#built-with)
- [Getting Started](#getting-started)
    - [Before you begin](#before-you-begin)
    - [Clone Project](#clone-project)
    - [Prerequisites](#prerequisites)
    - [Instruction](#instruction)
- [Business Logic](#business-logic)
- [System Goal](#system-goal)
- [Contact Me](#contact)
- [Contributing](#contributing)


<a name="about-the-project"></a>
## ⚡️ About The Project
This is the sample backend microservice project for Employee Management System which developed with Spring Boot + Spring Data JPA + MySQL.
You can learn Spring boot CRUD with sample employee management system.


<a name="built-with"></a>
### 🪓 Built With
This project is built with
* [Java](https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [MySQL Database](https://dev.mysql.com/downloads/installer/)


<a name="getting-started"></a>
## 🔥 Getting Started
This project is built with java, maven, postgresql and use `Project Lombok` as plugin.
So, please make sure all are installed in your machine.


<a name="before-you-begin"></a>
### 🔔 Before you begin
If you are new in Git, Github and new in Spring Boot configuration structure, <br>
You should see basic detail instructions first in here
[Spring Boot Application Instruction](https://github.com/yewin-mm/spring-boot-app-instruction) <br>
If you are not good enough in basic JPA CRUD knowledge with Java Spring Boot, you should learn below example projects first. <br>
Click below links.
* [Spring Boot Sample CRUD Application](https://github.com/yewin-mm/spring-boot-sample-crud)


If you already knew above links and you have good enough basic knowledge with Java Spring Boot, you can keep learn this project and see the "Instruction" below.


<a name="clone-project"></a>
### 🥡 Clone Project
* Clone the repo
   ```sh
   git clone https://github.com/yewin-mm/employee-sample-crud-system.git
   
   
<a name="prerequisites"></a>
### 🔑 Prerequisites
Prerequisites can be found in here [Spring Boot Application Instruction](https://github.com/yewin-mm/spring-boot-app-instruction).


<a name="instruction"></a>
### 📝 Instruction
* Create database with name as per your `application.properties` config name which follow by db url connection string by typing `create database {name};` by using database GUI tools or inside your database control.
* Change your database username and password in `application.properties`.
* Run the project in your IDE. Please make sure application was successfully running.
* You can check in your database is that there has 'Role and User' table were auto created or not, under employee_crud database by database GUI tools like ***DBeaver***.
* Insert some data into 'Role' table in your database by querying in database console.
* Find 'Role' table under postgres schema which will auto create by running application. 
* If you can't find, just click refresh and see again under your database.
* You can add by clicking '+' sign in your 'Role' table ***or*** you can run below query in DBeaver console (please go Execute SQL Statement in your DBeaver)
* If you use datagrip, you can add by clicking '+' sign in your 'Role' table ***or*** you can click `jump to query console` tab which is under `Database Explorer` tab, and you can type like below  
  ```sh 
  insert into role (id, role_name) values (1, 'admin'); 
  insert into role (id, role_name) values (2, 'normal user');

* Import `employee-crud-sample-postman-api-request.json` file under project directory (see that file in project directory) into your installed Postman application.
    * Click in your Postman (top left corner) import -> file -> upload files -> {choose that json file} and open/import.
    * After that, you can see the folder which you import from file in your Postman.
    
* Now, you can 'Test' this project by calling API from Postman.
    * Open your imported folder in postman and inside this folder, you will see total of 3 API requests and you can test it all by clicking `Send` button and see the response.
    * Before testing, please make sure some data should be in `Role` table first. 
    * You can check data in database too (here you can check data in tables (User, Role) by DBeaver tools or you can manually select query in your database console)
    
* After that you can see the code and check the code which you don't know. You can learn it and you can apply in your job or study fields.


<a name="business-logic"></a>
## 💡 Business Logic
* Imagine that there has one User call System Admin with admin role. (you can manually add new user into users table by insert query)
* There can be many role as per your requirement and now I set with two roles.
    1. Admin
        * A person who has Admin role can login into this system.
        * A person who has Admin role can do everything in this system (all permission will open). eg. change role, add user, delete user, update user information, etc.
        * A person who has Admin role can add user and change that user's role. eg. He can promote employee to Admin role from Normal User role. (eg. promote HR Manager to Admin role).
        * When HR Manager got Admin role, he can add new employees (when the employee was join the company, he need to add) into system and system will auto set new user to role id 2 (which is Normal User role).
        * When HR Manager got Admin role, he can delete employee from this system after the employee was resign and he can change (edit) his information too.
        * A person who has Admin role can view all employees information in this system and can search with by employee name or employee id or etc as well.
    2. Normal User
        * A person who has Normal User role can login into this system.
        * Normal User role can be get by default when new user was added in this system.
        * A person who has Normal User role cannot do much things (don't have much permission) (eg. he can't add new user or delete user)
        * A person who has Normal User role can view all employees information in this company(system) and can search with by employee name or employee id or etc and can edit his information too.
        
* Role can be control by frontEnd UI and this backend application will return user information including role when calling login api from frontend UI.
* Frontend will check the role id or role name when login into this system and frontend developer can show or hide some menu like add user as per login user role. eg. he can hide delete menu to login user who has just Normal User role.
* Here, you can add other roles like SuperAdmin, Head role, Manager role, etc. And every role can has different permission. That is as per your requirement and need to negotiate about role with frontend developer first.
* Imagine that System Admin will add HR manager as new employee and give him as admin role and imagine that HR manager will manage (create, update, delete) employees.
* System will auto generate employee id with prefix `EMP_` follow by 5 digit and it will be unique for each employee id. 
* When new employee was added, system will auto create new user with employee id as username and `employee` as default password.
* Imagine that HR manager will add new Employee and he can check employee id (which is auto generated by system) by searching (In frontend UI (eg. HTML Page) can have search button and call find all method after click search button) 
* So, we can assume that after HR manager added new Employee, he will give employee id and default password to new Employee.
* So, New Employee can login into this system by his employee id (which is give by HR Manager) and default password, after that he can change password (Assume, Frontend UI has change password page and call changePassword api from that page).
* After that, new employee can view all employees of this company.


<a name="system-goal"></a>
## 🎉 System Goal
The goal of this system is to manage employee for a company and it will easy to manage employee information and will help to HR department in a company like 
* Generating Unique Employee ID 
* Inserting, Deleting, updating new employee information
* Viewing employee created date (which will be employee join date) 
* Allowing to see all employees information in company

***Have Fun and Enjoy in Learning Code***


<a name="contact"></a>
## ✉️ Contact
Name - Ye Win <br> LinkedIn profile -  [Ye Win's LinkedIn](https://www.linkedin.com/in/ye-win-1a33a292/)  <br> Email Address - yewin.mmr@gmail.com

Project Link: [Spring Boot Employee Sample CRUD System](https://github.com/yewin-mm/employee-sample-crud-system)


<a name="contributing"></a>
## ⭐ Contributing
Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.
<br>If you want to contribute....
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/yourname`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push -u origin feature/yourname`)
5. Open a Pull Request
