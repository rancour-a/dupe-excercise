package com.rancoura.validity.dupeexercise.util;

import java.util.Set;
import java.util.stream.Collectors;

// https://www.programcreek.com/java-api-examples/?code=gentoku/name-matcher/name-matcher-master/src/namematcher/Matcher.java#
public interface Matcher {
	
	/**
	 * Returns TRUE if two strings match.
	 * @param a
	 * @param b
	 * @return boolean
	 */
	boolean match (String a, String b);
	
	/**
	 * Returns strings in candidates which match the subject.
	 * @param subject
	 * @param candidates
	 * @return may be more than two matches. empty set for no matches.
	 */
	default Set<String> getMatches (String subject, Set<String> candidates) {
		return candidates.stream().parallel()
						.filter(candidate -> this.match(subject, candidate))
						.collect(Collectors.toSet());
	}

}