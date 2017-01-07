package net.balsoftware.bean;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RRule {

	private String rruleContent;
	private String dtstartContent;
	private int maxRecurrences;
	private LocalDateTime created;
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public String getRruleContent() {
		return rruleContent;
	}
	public void setRruleContent(String rruleContent) {
		this.rruleContent = rruleContent;
	}
	public String getDtstartContent() {
		return dtstartContent;
	}
	public void setDtstartContent(String dtstartContent) {
		this.dtstartContent = dtstartContent;
	}
	public int getMaxRecurrences() {
		return maxRecurrences;
	}
	public void setMaxRecurrences(int maxRecurrences) {
		this.maxRecurrences = maxRecurrences;
	}
}
