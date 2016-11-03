package com.rolfje.anonimatron.anonymizer;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.rolfje.anonimatron.LoremIpsum;
import com.rolfje.anonimatron.synonyms.StringSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;

/**
 * Anonymizer that generates a fragment of lorem ipsum based on a rough word count of the original text. 
 * Note that the resulting length may be larger than the original input, which may be a problem for fields with limits.
 * Has been tested on Oracle:clob,nclob,varchar2,nvarchar2; Postgres:varchar,text; Mysql:varchar,text
 * @author nmiller
 *
 */
public class LoremIpsumAnonymizer implements Anonymizer {
	Logger LOG = Logger.getLogger(LoremIpsumAnonymizer.class);
	@Override
	public String getType() {
		return "LOREMIPSUM";
	}

	@Override
	public Synonym anonymize(Object from, int size) {
		String input = "";		
		if(from instanceof Clob) {
			Clob clob = (Clob)from;
			/* get value from Clob. Note that this supports clobs of length less than Integer.MAX_VALUE */
			try {				
				if(clob.length()>0) {					
					input = clob.getSubString(1, (int) clob.length());
				}
			} catch (SQLException e) {				
				LOG.error("error retrieving clob value:"+e);
			}
		} else {
			input = (String)from;
		}
		StringTokenizer words = new StringTokenizer(input);
		String to = LoremIpsum.getWords(words.countTokens());

		StringSynonym stringSynonym = new StringSynonym();
		stringSynonym.setFrom(input);
		stringSynonym.setType(getType());		
		stringSynonym.setTo(to);

		return stringSynonym;
	}

}
