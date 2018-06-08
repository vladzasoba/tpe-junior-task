# TPE - Test task junior
## Getting strated
### Building project
Compile and build the project using Maven
```
mvn clean package
```
### Running project
Run jar file with specifying spring datasource properties. 
Otherwise you can set them in application.properties file.
```
java -jar target/test-task-junior-1.0.jar --spring.datasource.url=<URL> 
					  --srping.datasource.username=<username> 
					  --spring.datasource.password=<password>
					  --server.port=<port>
```
