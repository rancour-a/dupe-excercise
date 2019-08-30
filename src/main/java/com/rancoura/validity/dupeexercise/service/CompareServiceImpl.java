package com.rancoura.validity.dupeexercise.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rancoura.validity.dupeexercise.domain.Contact;
import com.rancoura.validity.dupeexercise.repository.ContactRepository;

@Service
public class CompareServiceImpl implements CompareService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public Map<Integer, List<String>> findDuplicates() {

		List<Contact> contactList = contactRepository.findAll();
		Map<Long, Contact> contactMap = contactList.stream().collect(Collectors.toMap(Contact::getId, Function.identity()));
	    int dupeIndex = 0;
	    for (Long key : contactMap.keySet()) {
			dupeIndex++;
			for (Contact c : contactList) {
				if (contactMap.get(key).pseudoEquals(c)) {
					c.setDupeIndex(dupeIndex);
				}				
			}		
		}
		
	    // there shouldn't be two loops here.. done for the sake of time
	    // ideally the loop above would incorporate the secondary groupings to optimize performance
	    Map<Integer, List<String>> list = new HashMap<>();   
	    contactList.stream().collect(Collectors.groupingBy(Contact::getDupeIndex)).values().forEach(contact-> {
	    	if (contact.size() == 1) {
	    		if (list.containsKey(0)) {
	    			list.get(0).addAll(contact.stream().map(Contact::outLine).collect(Collectors.toList()));
	    		} else {
	    			list.put(0, contact.stream().map(Contact::outLine).collect(Collectors.toList()));
	    		}    		
	    	} else {
	    		if (list.containsKey(contact.get(0).getDupeIndex())) {
	    			list.get(contact.get(0).getDupeIndex()).addAll(contact.stream().map(Contact::outLine).collect(Collectors.toList()));
	    		} else {
	    			list.put(contact.get(0).getDupeIndex(), contact.stream().map(Contact::outLine).collect(Collectors.toList()));
	    		}
	    	}  	
	    });
	    	    
	    // https://javarevisited.blogspot.com/2017/07/how-to-sort-map-by-keys-in-java-8.html
	    return list .entrySet() .stream() 
	    		.sorted(Collections.reverseOrder(Map.Entry.comparingByKey())) 
	    		.collect( Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
	}

}
