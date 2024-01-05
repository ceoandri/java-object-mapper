package gratis.contoh.mapper;

import java.io.Serializable;

public class FieldMapper implements Serializable {
	
	private static final long serialVersionUID = 3424491883488813858L;
	
	private String from;
	private String to;
	
	public FieldMapper(String from, String to) {
		setFrom(from);
		setTo(to);
	}
	
	public String getFrom() {
		return this.from;
	}
	
	public String getTo() {
		return this.to;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}

}
