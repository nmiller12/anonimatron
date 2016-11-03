package com.rolfje.anonimatron.synonyms;

import java.sql.Timestamp;
import java.util.Date;


/**
 * Represents a synonym for a {@link String}.
 * 
 */
public class TimestampSynonym implements Synonym {
	private String type;
	private String from;
	private Timestamp to;

	public String getType() {
		return type;
	}

	public Object getFrom() {
		return from;
	}

	public Object getTo() {
		return to;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTo(Date to) {
		this.to = new Timestamp(to.getTime());
	}

	public void setFrom(Object from) {
		this.from = from.toString();		
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	@Override
	public int hashCode() {
		return from.hashCode() + to.hashCode() + type.hashCode();
	}
}
