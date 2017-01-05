package net.balsoftware.resources;

import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	@Produces(MediaType.TEXT_PLAIN)
	public String doPost(RRuleBean request)
	{
//		DateTimeStart dateTimeStart = DateTimeStart.parse("DTSTART:20170104T131901");
//		
//		String recurrences;
//		try {
//		RecurrenceRule rrule = RecurrenceRule.parse("RRULE:FREQ=DAILY");
//		recurrences = rrule.getValue().streamRecurrences(dateTimeStart.getValue())
//				.limit(10)
//				.map(t -> t.toString())
//				.collect(Collectors.joining(LS));
//		} catch (Exception e)
//		{
//			recurrences = "Invalid";
//		}
//		return recurrences;
		
		DateTimeStart dateTimeStart = DateTimeStart.parse(request.getDtstartContent());
		int limit = Integer.parseInt(request.getMaxRecurrences());
//		int limit = request.getMaxRecurrences();
		String recurrences;
		try {
		RecurrenceRule rrule = RecurrenceRule.parse(request.getRruleContent());
		recurrences = rrule.getValue().streamRecurrences(dateTimeStart.getValue())
				.limit(limit)
				.map(t -> t.toString())
				.collect(Collectors.joining(LS));
		} catch (Exception e)
		{
			recurrences = "Invalid";
		}
		return recurrences;
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String doPost(String request)
	{
		return request;
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	protected String doPost()
//	{
//		return "here";
////		System.out.println("run servlet");
////		DateTimeStart dateTimeStart = DateTimeStart.parse(request.getDtstartContent());
////		
////		String recurrences;
////		try {
////		RecurrenceRule rrule = RecurrenceRule.parse(request.getRruleContent());
////		recurrences = rrule.getValue().streamRecurrences(dateTimeStart.getValue())
////				.limit(request.getMaxRecurrences())
////				.map(t -> t.toString())
////				.collect(Collectors.joining(LS));
////		} catch (Exception e)
////		{
////			recurrences = "Invalid";
////		}
////		return recurrences;
//	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String doGet()
	{
		DateTimeStart dateTimeStart = DateTimeStart.parse("DTSTART:20170104T131901");
		
		String recurrences;
		try {
		RecurrenceRule rrule = RecurrenceRule.parse("RRULE:FREQ=DAILY");
		recurrences = rrule.getValue().streamRecurrences(dateTimeStart.getValue())
				.limit(10)
				.map(t -> t.toString())
				.collect(Collectors.joining(LS));
		} catch (Exception e)
		{
			recurrences = "Invalid";
		}
		return recurrences;
	}

//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		// get 1st row in database as initial setting
//	}

//	/**
//     * @see HttpServlet#HttpServlet()
//     */
//    public RRuleServlet3() {
//        super();
//    }
//
//	/**
//	 * @see Servlet#init(ServletConfig)
//	 */
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//	}

}
