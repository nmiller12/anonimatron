package com.rolfje.anonimatron.anonymizer;

import java.util.Random;

import com.rolfje.anonimatron.synonyms.IntegerSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;

public class NumberAnonymizer extends AbstractElevenProofAnonymizer {

	@Override
	public String getType() {
		return "RANDOMNUMBERS";
	}

	@Override
	public Synonym anonymize(Object from, int size) {
		IntegerSynonym integerSynonym = new IntegerSynonym();
		/* convert boolean to random 0/1 */
		if(from.toString().equals("true")||from.toString().equals("false")) {		
			integerSynonym.setFrom(from);
			integerSynonym.setType(getType());
			Random rand =  new Random();
			int random01 = rand.nextInt(2);
			integerSynonym.setTo(Integer.toString(random01));
		} else {
			int length = from.toString().length();
	
			int[] digits = getRandomDigits(length);
			String to = digitsAsNumber(digits);
				
			integerSynonym.setFrom(from);
			integerSynonym.setType(getType());
			integerSynonym.setTo(to);
		}
		
		return integerSynonym;
	}

}
