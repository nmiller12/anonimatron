package com.rolfje.anonimatron.anonymizer;

import java.util.Date;

import com.rolfje.anonimatron.synonyms.TimestampSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;

class TimestampAnonymizer implements Anonymizer {	
	private static final String TYPE = "TIMESTAMP";
	private static final long RANDOMIZATION_MILLIS = 1000 * 60 * 60 * 24 * 31;	

	@Override
	public Synonym anonymize(Object from, int size) {
		TimestampSynonym s = new TimestampSynonym();
		s.setType(TYPE);
		s.setFrom(from);

		if (from == null) {
			s.setTo(null);
		} else if (from instanceof Date) {
			long originalDate = ((Date) from).getTime();

			Date newDate;						
			/* chooses a new date in a range of about 5 months earlier */
			long deviation = Math.round(10 * RANDOMIZATION_MILLIS
						* Math.random())
						- RANDOMIZATION_MILLIS;				
			newDate = new Date(originalDate + deviation);
			
						
			s.setTo(newDate);
		} else {
			throw new UnsupportedOperationException(
					"Can not anonymize objects of type " + from.getClass());
		}

		return s;
	}

	@Override
	public String getType() {
		return TYPE;
	}
}
