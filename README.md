

Initial Pull Request - 90 minutes transpired
	- Researched algorythms, Determined approach, initial loading of file, internal store, skeleton
	
	
Next pull request 
	dupe algorythm into the service, 
	controller to expose JSON, 
	simple thymeleaf template to display.
	comments, deployability



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