package com.rolfje.anonimatron.anonymizer;

import java.util.StringTokenizer;

import com.rolfje.anonimatron.LoremIpsum;
import com.rolfje.anonimatron.synonyms.StringSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;

public class LoremIpsumAnonymizer implements Anonymizer {

	@Override
	public String getType() {
		return "LOREMIPSUM";
	}

	@Override
	public Synonym anonymize(Object from, int size) {		
		StringTokenizer words = new StringTokenizer((String) from);
		String to = LoremIpsum.getWords(words.countTokens());

		StringSynonym stringSynonym = new StringSynonym();
		stringSynonym.setFrom(from);
		stringSynonym.setType(getType());
		stringSynonym.setTo(to);

		return stringSynonym;
	}

}
