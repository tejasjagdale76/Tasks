RPMS JAVA ECLIPSE PROJECT
=========================

IMPORT INTO ECLIPSE
-------------------
1. Extract RPMS_Java_Eclipse_Project.zip
2. Open Eclipse
3. Go to File > Import
4. Select General > Existing Projects into Workspace
5. Click Next
6. Select the extracted folder: RPMS_Java_Eclipse
7. Eclipse should detect project: RPMS_Java_Eclipse
8. Click Finish

DATABASE SETUP
--------------
1. Open MySQL Workbench
2. Run: sql/rpms_full_setup.sql
3. Open:
   src/com/kiranacademy/rpms/util/DBConnection.java
4. Change USER and PASSWORD as per your MySQL setup.

MYSQL JDBC DRIVER
-----------------
At runtime, MySQL Connector/J is required.

In Eclipse:
1. Right-click project
2. Build Path > Configure Build Path
3. Libraries > Classpath > Add External JARs
4. Select mysql-connector-j-*.jar
5. Apply and Close

RUN PROJECT
-----------
Run:
src/com/kiranacademy/rpms/app/RecruitmentApp.java

Right-click RecruitmentApp.java > Run As > Java Application

The project itself imports and compiles as a standard Eclipse Java project.
The MySQL Connector/J JAR is required only when database connectivity is executed.
