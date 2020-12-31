# Spring boot application with the using of the JavaFx 11

### Validating the

###  * HLA/DNA codes by utins the hla_nom file(IPD-IMGT/HLA Database)

###  * NMDP Codes by using of the Open NMDP API

### The reason for writing,

### is to test FxWeaver Platform a.k.a Spring + JavaFx

# Package:

#### There is a special command for package the app as a JAR application

```shell
mvn clean package spring-boot:repackage
```

# To run(start.bat):

```shell

java --module-path=.\lib --add-modules=javafx.base --add-modules=javafx.controls --add-modules=javafx.fxml --add-modules=javafx.graphics --add-modules=javafx.media --add-modules=javafx.swing --add-modules=javafx.web --add-modules=com.jfoenix --add-opens java.base/java.lang.reflect=com.jfoenix --add-opens javafx.base/com.sun.javafx.runtime=com.jfoenix --add-opens javafx.graphics/com.sun.javafx.scene=com.jfoenix --add-opens javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix --add-opens javafx.controls/com.sun.javafx.scene.control=com.jfoenix --add-opens javafx.base/com.sun.javafx.binding=com.jfoenix --add-opens javafx.base/com.sun.javafx.event=com.jfoenix --add-opens javafx.graphics/com.sun.javafx.stage=com.jfoenix -jar hla-validator-0.0.4-SNAPSHOT.jar

```

