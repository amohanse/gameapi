# Game API

REST-API for Fizz Buzz Game

# How to build and run 

1. to build : > mvn clean install
2. to run 	: > mvn spring-boot:run 

The app is configured to run on port 8081, if the local port is occupied, modify the > server.port property in > src/main/resources/application.properties

Use the url to access the game api, if the app is running in default port 

http://localhost:8081/rest-api?A=15  (query parameter A=15 is the number given to the game app to generate Fizz Buzz series, add more pairs by 
using unique keys as query parameters)

# How to deploy to Heroku cloud

Prerequisites
	Follow the steps to install Heroku cli : 
			1. [Heroku Introduction](https://devcenter.heroku.com/articles/getting-started-with-java#introduction)
            2. [Set Up](https://devcenter.heroku.com/articles/getting-started-with-java#set-up)
1. Login to Heroku : > heroku login
2. Clone this git repo to a directory
3. Create a app in heroku : > heroku create {optional name}
	- at this step, heroku would add another remote repository to the git repo in local, check it by using the command "git remote -v", should show two remote repo url. Good lets move on.
	- note down the endpoint of the app, it is required to access the application.
4. Push the code to heroku remote : > git push heroku master
	- wait .. while heroku run the maven to build the project, once it shows "remote: Verifying deploy.... done.", its done.
5. Start a dyno to run the app : > heroku ps:scale web=1
6. Tail the logs from heroku use : > heroku logs --tail
7. Once the testing is done, scale down the dyno : > heroku ps:scale web=0

Launch a browser / terminal, use the url (https://<end point from step 3>/rest-api?A=30)




