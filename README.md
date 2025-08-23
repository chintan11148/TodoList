# Phase 1:

SayHelloController.java

It is the controller class which handles requests .. which is known as controller.
# for which we are using @Controller.

inside the class, we have created an method, when invoked it will provide the output.

@RequestMapping("say-hello")
@ResponseBody
public String hello(){
    return "Hello";
}

# @RequestMapping("say-hello") --> is mapping the done with the URL (http:localhost:8080/say-hello)

# @ResponseBody --> is used to provide the output on the browser

# Phase 2:

We need to show the output using html.

For which we had created sayHello.jsp in a specific location.
under resources --> META-INF/resources/WEB-INF/jsp  [Predefined path given by springboot]

in the Jsp file, we can simply place the html tags for frontend.
And this will be called and controlled through the sayHelloController.java
using a method :

@RequestMapping("say-hello-jsp")
    public String outJsp(){
        return "sayHello";
    }

# Note: here we are not using @ResponseBody here as responseBody directly showcase in the frontend. but we are providing output on the basis of the view or .jsp

We have also added an dependency:

        <dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>

# Phase 3: 

We can get the name from the URL and display in the jsp frontend.

http://localhost:8080/login?name=ravi    [?name=ravi is called as request param]

to handle the same, we have to use 
# public String login(@RequestParam String name, ModelMap model){          [Anything u want to pass from your controller to the Jsp by using ModelMap] 
    model.put("name", name);
    return "login";
}       


In the Jsp, if you want to pass any variable, you can by ${name} 

# Phase 4:

private Logger logger = LoggerFactory.getLogger(getClass());    [Logger is a predefined function, used for logging]

you can add logging in application.properties
logging.level.org.springframework=info   [it is used to set logging for the whole project]
logging.level.src.main.java.com.springboot.webapp=debug   [this is used to set logging level to a specific package]

