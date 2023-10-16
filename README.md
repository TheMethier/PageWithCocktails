# PageWithCocktails
Web application with recepies to your favorite cocktails. Backend was written in Spring Boot with MS SQL database, and fronted (in file "cocktails") in ReactJS. So enjoy your drink and cheers!
# 1. Requirements:
+ Java v20.0.1<br />
+ Microsoft SQL Server 2022<br />
+ Node v18.12.1<br />
+ Windows 10 <br />
Database Settings:<br />
+username: sa<br />
+password: 12345<br />
# 2. To Launch:<br />
1. Create new database "Drink" in Microsoft Server Managment Studio<br />
2. Run VeryLastApi-0.0.1-SNAPSHOT.jar file<br />
3. Run with PowerShell LaunchFrontend.ps1 file or type in PowerShell:<br />
cd cocktails<br />
npm start<br />
# 3. Functionalities and used technologies:
+ Recipies to database are scraped from https://mojbar.pl/drinki-z-likieru/
+ Register and authentication with JWT Token (Only executed from API)
+ Recipies are displayed on frontend by using fetch API
+ Components are styled using MUI library
+ Only logged users can add new cocktails, when manager accept thier requests (Only executed from API)
+ Users can have their own favorite cocktails (in progress)
+ Logout (Only API)
+ Filtering data from Backend
+ Searching bar executed from Frontend
+ Swagger Docs (in progress)
