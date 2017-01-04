package net.balsoftware.rest;

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
@Path("/RRuleResource")
public class RRuleResource {
	private static final String LS = "<br>";
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.TEXT_PLAIN})
	protected String doPost(RRuleBean request)
	{

    	String rruleContent = request.getParameter("rruleContent");
		int limit = Integer.parseInt(request.getParameter("limit"));
		DateTimeStart dateTimeStart = DateTimeStart.parse(request.getParameter("dateTimeStart"));
		
		String recurrences;
		try {
		RecurrenceRule rrule = RecurrenceRule.parse(rruleContent);
		recurrences = rrule.getValue().streamRecurrences(dateTimeStart.getValue())
				.limit(limit)
				.map(t -> t.toString())
				.collect(Collectors.joining(LS));
		} catch (Exception e)
		{
			recurrences = "Invalid";
		}
		return recurrences;
//		response.setContentType("text/plain");
//		PrintWriter out = response.getWriter();
//		out.print("Recurrence Series:" + LS + rrules);
//		out.print(recurrences);
		
		// TODO - PUT REQUEST INTO DATABASE
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
