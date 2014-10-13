Get started with personafusion
-----------------------------------
Welcome to Java Web Starter application!

This sample application demonstrates how to write a Java Web application (powered by WebSphere Liberty) and deploy it on Bluemix.

1. [Install the cf command-line tool](https://www.ng.bluemix.net/docs/#starters/BuildingWeb.html#install_cf).
2. [Download the starter application package](https://ace.stage1.ng.bluemix.net:443/rest/../rest/apps/f13078b7-3f6d-4169-89b4-a6b7c5dc435b/starter-download).
3. Extract the package and `cd` to it.
4. Connect to Bluemix:

		cf api https://api.ng.bluemix.net

5. Log into Bluemix:

		cf login -u swelleck@us.ibm.com
		cf target -o swelleck@us.ibm.com -s dev
				
6. Compile the Java code and generate the war package using ant.
7. Deploy your app:

		cf push personafusion -p webStarterApp.war

8. Access your app: [http://personafusion.stage1.mybluemix.net](http://personafusion.stage1.mybluemix.net)
