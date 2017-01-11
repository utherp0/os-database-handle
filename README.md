# os-database-handle
Simple JAVA Class to wrap Database interactions for OpenShift tests

*Note* On a Mac the Mysql process defaults to port 3307, not 3306, hence the connection string is jdbc:mysql://localhost:3307

(Example use - note fully qualified lib directory)
java -classpath "/Users/uther/lib/*:target/core-1.0.jar" org.uth.osdbhandle.test.HandleTest1 com.mysql.jdbc.Driver jdbc:mysql://127.0.0.1:3307/test2 root
