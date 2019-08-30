package com.rancoura.validity.dupeexercise.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rancoura.validity.dupeexercise.domain.Contact;
import com.rancoura.validity.dupeexercise.repository.ContactRepository;

@Configuration
// This will run the "run" when the server has started
public class StartupLoader implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(StartupLoader.class);

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		String record;
		boolean firstLine = true;

		// https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
		String otherThanQuote = " [^\"] ";
		String quotedString = String.format(" \" %s* \" ", otherThanQuote);
		String regex = String.format("(?x) "+ // enable comments, ignore white spaces
				",                         "+ // match a comma
				"(?=                       "+ // start positive look ahead
				"  (?:                     "+ //   start non-capturing group 1
				"    %s*                   "+ //     match 'otherThanQuote' zero or more times
				"    %s                    "+ //     match 'quotedString'
				"  )*                      "+ //   end group 1 and repeat it zero or more times
				"  %s*                     "+ //   match 'otherThanQuote'
				"  $                       "+ // match the end of the string
				")                         ", // stop positive look ahead
				otherThanQuote, quotedString, otherThanQuote);
	        
		// pulling resource internally
		// TODO  future pass it in, command line..   File Upload
		BufferedReader csvReader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/static/advanced.csv")));
		
//		BufferedReader csvReader = new BufferedReader(new InputStreamReader(
//                this.getClass().getResourceAsStream("/static/normal.csv")));


		contactRepository.deleteAll();	
		while ((record = csvReader.readLine()) != null) {
			
			if (firstLine) {  // first Line is the header >> skip
				firstLine = false;
				continue;
			}
			
            // save to local store
			contactRepository.save(new Contact(record.split(regex, -1)));
		}
		csvReader.close();
		
		logger.info("  >>>>>>>>>>>>  Loaded resources <<<<<<<<<<<<<<<< ");
		
		// printing to stdout
		contactRepository.findAll().forEach((contact) -> {
			System.out.println(contact.outLine());
		});
		
	}
}
