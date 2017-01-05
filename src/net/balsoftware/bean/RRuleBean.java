package net.balsoftware.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RRuleBean {

	private String rruleContent;
	private String dtstartContent;
	private int maxRecurrences;
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
