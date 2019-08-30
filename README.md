
Standard Spring boot application, running embedded tomcat on port 7755.  
Uses in memory database and loads the data initially on startup.  The decision was made to do this to lessen the load on the page call, even though with 100 records the difference shouldn't be noticeable.

Source code:  https://github.com/rancour-a/dupe-excercise
To run (SPRING BOOT/MAVEN):  mvn spring-boot:run  in the project dir, then goto localhost:7755 
To see a running app:   HEROKU   

Create a JAR from source:  mvn install package 

------------

Initial Pull Request - 90 minutes transpired
	- Researched algorythms, Determined approach, initial loading of file, internal store, skeleton
	
Final - 3 hours.
Next pull request 
	dupe algorythm into the service, 
	controller to expose JSON, 
	simple thymeleaf template to display.
	comments

-------------------

The Exercise

Write an app that parses the attached normal.csv file using any language you choose (unless you’ve been asked otherwise).
Identify possible duplicate records in the data-set.
Leverage existing algorithms (no need to reinvent the wheel)
Example algorithms include Metaphone and Levenshtein distance
We’re looking to find not just exact duplicates but also records that are likely to be a duplicate entry with different spelling, missing data, small differences, etc.
Ignore the ID column for deduplication as they are here to help you visually identify duplicates

Build a lightweight web application to display the processed data. Print each set of duplicates separately as well as a set of non-duplicate entries.
Create a JSON object output
Print the results to the standard output (stdout)
Display duplicates separately as well as a set of non-duplicate entries.
Example output attached Note: the values returned aren’t necessarily duplicates:
Some duplicates are harder to identify than others and some false negatives harder to eliminate. Do what you can in the few hours you can dedicate to this.