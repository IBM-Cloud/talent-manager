Java Web Application

This sample application demonstrates how to write a Java Web application (powered by WebSphere Liberty) and deploy it on Bluemix.

Files

The Java Web starter application contains the following contents:

*   webstarterapp.war

    This WAR file is actually the application itself. It is the only file that'll be pushed to and run on the Bluemix cloud. Every time your application code is updated, you'll need to regenerate this WAR file and push to Bluemix again. See the next section on detailed steps.
    
*   WebContent/

    This directory contains the client side code (HTML/CSS/JavaScript) of your application as well as compiled server side java classes and necessary JAR libraries. The content inside this directory is all you need to generate the final WAR file.
    
*   src/

    This directory contains the server side code (JAVA) of your application. In this simple starter application, there's only one class: 'com.ibm.cloudoe.samples.HelloResource'
    
*   build.xml

    This file allows you to easily build your application using Ant.
    
*   instructions.md

    This file describes the Next Steps for getting started with this template.