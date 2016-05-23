1. The names, email IDs, and students IDs of the members
Yutao Hou, chrishou118@gmail.com, 010042799
Xiaofeng Li, xiaofenglijohn@gmail.com, 010720554
Jia Wang, jiawang.li@sjsu.edu, 010772853
Fangjia Li, fangjia.li@sjsu.edu, 010248641

2. The URL to access your app, and the admin account username/password, so that the user can test the provisioning features
URL: http://54.153.39.74:8080/
admin account: admin@sjsu.edu
password: password

3. Any other instruction necessary for the grading of the app

4. Build instructions
    a. make sure java 8, mysql installed
    b. mysql, make the password of root is empty. start mysql. create database "cmpe275_term_project"
    c. for upload image, create folder: /tmp/cmpe275/upload/upload_images
    d. cd to root of project
    e. run "./mvnw spring-boot:run"
    f. open http://localhost:8080