var servletURL = "http://localhost:8080/RRuleRest1/RRuleServlet";

/*
 * Get list of recurrences for the RRULE from servlet and render in table
 */
function getRecurrences()
{
	$.ajax({
		type: 'GET',
		contentType: 'application/x-www-form-urlencoded',
		data: $("#rruleForm").serialize(),
		url: servletURL,
		dataType: "text",
		beforeSend: function(request) {
		    request.setRequestHeader("X-FORWARDED-FOR", ipAddress);
		  },
		success: renderList
	});
}

/*
 * Get user's IP address for logging
 */
var ipAddress = null;
function getIPAddress()
{
	$.get('http://jsonip.com/', function(r)
			{
				ipAddress = r.ip;
			});
}

/*
 * Render comma-delimited list of date/times into table
 */
function renderList(data)
{
	var dataArray = data.split(",");
	$('#resultTable tbody').empty();
	var resultTable = document.getElementById("resultTableBody");
	for(var i = 0; i< dataArray.length; i++)
	{
		var row = resultTable.insertRow(i);
		var cell = row.insertCell(0);
		cell.innerHTML = dataArray[i];
	}
}

$( document ).ready(function()
{
	// Toggle between AJAX response to same page and regular GET response to new URL
	document.getElementById("resultsSamePageCheckBox").onclick = function()
	{
		toggleFormResult();
	};
});

/*
 * Handle response being on same page or in new page
 */
function toggleFormResult()
{
	var isSamePageClicked = document.getElementById("resultsSamePageCheckBox").checked;
	if (isSamePageClicked)
	{
	    //Stops the submit request
		$('#rruleForm').click(function(event) {
		    event.preventDefault();
		});
		// Register submit to run 
		$('#submitButton').click(function(e) {
			getRecurrences();
		});
	} else
	{
		$('#rruleForm').unbind('click');
		$('#submitButton').unbind('click');

	}
}