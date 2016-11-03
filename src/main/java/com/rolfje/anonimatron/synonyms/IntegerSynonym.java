package com.rolfje.anonimatron.synonyms;

import java.math.BigDecimal;


/**
 * Represents a synonym for a {@link String}.
 * 
 */
public class IntegerSynonym implements Synonym {
	private String type;
	private Integer from;
	private Integer to;

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

	public void setTo(Object to) {
		this.to = Integer.parseInt((String) to);
	}

	public void setFrom(Object from) {
		if(from instanceof Boolean) {
			this.from = (Boolean) (from) ? 1 : 0;			
		} else if(from instanceof BigDecimal) {
			this.from = Integer.valueOf(((BigDecimal) from).intValueExact());			
		} else {
			this.from = (Integer) from;
		}
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
