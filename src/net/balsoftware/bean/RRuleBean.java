package net.balsoftware.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RRuleBean {

	private String rruleContent;
	private String dtstartContent;
	private String maxRecurrences;
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
	public String getMaxRecurrences() {
		return maxRecurrences;
	}
	public void setMaxRecurrences(String maxRecurrences) {
		this.maxRecurrences = maxRecurrences;
	}
}
