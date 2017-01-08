package net.balsoftware.bean;

import java.time.temporal.Temporal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Deprecated
public class Recurrence
{
	private Temporal recurrence;

	public Temporal getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(Temporal recurrence) {
		this.recurrence = recurrence;
	}
}
