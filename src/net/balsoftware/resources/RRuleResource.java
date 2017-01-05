package net.balsoftware.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jfxtras.icalendarfx.properties.component.recurrence.RecurrenceRule;
import jfxtras.icalendarfx.properties.component.time.DateTimeStart;
import net.balsoftware.bean.RRuleBean;

/**
 * Servlet implementation
 */
@Path("/parse")
public class RRuleResource {
	private static final String LS = "<br>";
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> doPost(RRuleBean request)
	{
		DateTimeStart dateTimeStart = DateTimeStart.parse(request.getDtstartContent());
//		int limit = Integer.parseInt(request.getMaxRecurrences());
		int limit = request.getMaxRecurrences();
//		String recurrences;
//		try {
//		RecurrenceRule rrule = RecurrenceRule.parse(request.getRruleContent());
//		recurrences = rrule.getValue().streamRecurrences(dateTimeStart.getValue())
//				.limit(limit)
//				.map(t -> t.toString())
//				.collect(Collectors.joining(LS));
//		} catch (Exception e)
//		{
//			recurrences = "Invalid";
//		}
//		return recurrences;
		
		List<String> recurrences;
		try {
		RecurrenceRule rrule = RecurrenceRule.parse(request.getRruleContent());
		recurrences = rrule.getValue().streamRecurrences(dateTimeStart.getValue())
				.limit(limit)
				.map(t -> t.toString())
				.collect(Collectors.toList());
		} catch (Exception e)
		{
			recurrences = null;
		}
		return recurrences;
	}
}
