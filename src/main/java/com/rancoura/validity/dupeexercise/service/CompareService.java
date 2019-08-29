package com.rancoura.validity.dupeexercise.service;

import java.util.List;
import java.util.Map;

import com.rancoura.validity.dupeexercise.domain.Contact;

public interface CompareService {

	Map<Integer, List<Contact>> findDuplicates(List<Contact> contactList);
	
}
