var ipAddress = null;
/**
 * 
 */
//function loadXMLDoc() {
////	if (ipAddress != null)
////	{
//		var xmlhttp;
//		if (window.XMLHttpRequest) {
//			// code for IE7+, Firefox, Chrome, Opera, Safari
//			xmlhttp = new XMLHttpRequest();
//			} else {
//				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
//		}
//		xmlhttp.onreadystatechange = function() {
//			if (xmlhttp.readyState == 4) {
//				if (xmlhttp.status = 200){
//					document.getElementById("result").innerHTML = xmlhttp.responseText;
//					} else
//					{
//						alert("Action can't be performed");
//					}
//				}
//			};
//	
//		xmlhttp.open("POST", "webapi/parse");
//	//	xmlhttp.setRequestHeader('Content-type', "application/x-www-form-urlencoded");
//		xmlhttp.setRequestHeader('Content-type', "application/json");
//		var data = formToJSON();
//	//    var rruleContent = document.getElementById('rruleContent').value;
//	//    var dtstartContent = document.getElementById('dtstartContent').value;
//	//    var maxRecurrences = document.getElementById('maxRecurrences').value;
//		xmlhttp.send(data);
////	xmlhttp.send("rruleContent=" + rruleContent + "&dtstartContent=" + dtstartContent + "&maxRecurrences=" + maxRecurrences);
////	}
//}

//var rootURL = "http://localhost:8080/RRuleRest1/webapi/parse";
//
///*
// * Submit RRULE and DTSTART
// */
//function postRRuleO() {
//	console.log('post RRULE' + formToJSON() + ipAddress);
//	$.ajax({
//		type: 'POST',
//		contentType: 'application/json',
//		data: formToJSON(),
//		url: rootURL,
//		dataType: "text",
//		success: renderList
////		success: function(data, textStatus, jqXHR){
////			alert('RRULE created successfully');
////		},
////		error: function(jqXHR, textStatus, errorThrown){
////			alert('RRULE error: ' + textStatus);
////		}
//	});
//}

var servletURL = "http://localhost:8080/RRuleRest1/RRuleServlet";

//function postRRulem() {
//	console.log('post RRULE servlet' + $("#rruleForm").val() + ", " + $("#rruleContent").val());
//	$.ajax({
//		type: 'POST',
//		contentType: 'application/x-www-form-urlencoded',
//		data: $("#rruleForm").serialize(),
//		url: rootURL,
//		dataType: "text",
//		success: renderList
////		success: function(data, textStatus, jqXHR){
////			alert('RRULE created successfully');
////		},
////		error: function(jqXHR, textStatus, errorThrown){
////			alert('RRULE error: ' + textStatus);
////		}
//	});
//}

/*
 * Get list of recurrences for the RRULE from servlet
 */
function getRecurrences()
{
	console.log('post RRULE servlet' + $("#rruleForm").serialize());
		
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
//		success: function(data, textStatus, jqXHR){
//			alert('RRULE created successfully');
//		},
//		error: function(jqXHR, textStatus, errorThrown){
//			alert('RRULE error: ' + textStatus);
//		}
	});
}

function getIPAddress()
{
	$.get('http://jsonip.com/', function(r)
			{
				console.log(r.ip);
				ipAddress = r.ip;
				console.log("ip:" + ipAddress);
//				buildHistories();
			});
}

////Helper function to serialize all the form fields into a JSON string
//function formToJSON() {
//	console.log($('#rruleContent').val());
//	return JSON.stringify({
//		"rruleContent": $('#rruleContent').val(), 
//		"dtstartContent": $('#dtstartContent').val(),
//		"maxRecurrences": $('#maxRecurrences').val(),
//		"ipAddress": ipAddress
//		});
//}

//function findAll() {
//	console.log('findAll');
//	$.ajax({
//		type: 'GET',
//		url: rootURL,
//		dataType: "text", // data type of response
//		success: renderList,
//		error: function(jqXHR, textStatus, errorThrown){
//			alert('RRULE error: ' + textStatus);
//		}
//	});
//}

function renderList(data)
{
	var dataArray = data.split(",");
	console.log("dataArray.length:" + dataArray.length);
	$('#resultTable tbody').empty();
	var resultTable = document.getElementById("resultTableBody");
	for(var i = 0; i< dataArray.length; i++)
	{
		var row = resultTable.insertRow(i);
		var cell = row.insertCell(0);
		cell.innerHTML = dataArray[i];
	}
}

///*
// * Toggle between AJAX response to same page and regular GET response to new URL
// */
//toggleResultsPage(isResultsOnSamePage)
//{
//	var isResultsOnSamePage = document.getElementById('resultsPageCheckBox').value;
//	if (isResultsOnSamePage)
//	{
//	    //Stops the submit request
//	    $("#rruleForm").submit(function(e){
//	           e.preventDefault();
//	    });
//	}
//}

$( document ).ready(function()
{
	console.log("regisger click listener");
	// add onclick listener to resultsPageCheckBox
	// Toggle between AJAX response to same page and regular GET response to new URL
	document.getElementById("resultsPageCheckBox").onclick = function()
	{
		toggleFormResult();
	};
});

function toggleFormResult()
{
	var isClicked = document.getElementById("resultsPageCheckBox").checked;
	if (isClicked)
	{
	    //Stops the submit request
		$('#rruleForm').click(function(event) { // prevent default form post
		    event.preventDefault();
		});
	} else
	{
		$('#rruleForm').unbind('click');
	}
}

/*
 * Form Controls
 */

/*
 * Initialize start date picker
 */
function initDates()
{
    var date = new Date();
    // DTSTART
    var dateString = buildDateString(date, "-"); // assemble ISO 8601 date
    document.getElementById('dateStart').value = dateString;
    
    // UNTIL
    date.setMonth(date.getMonth()+1);
    var untilDateString = buildDateString(date, "-"); // assemble ISO 8601 date
    document.getElementById('until').value = untilDateString;
    
    // TIME
	var options = { hour12: false };
	var timeString = date.toLocaleTimeString('default', options);
    document.getElementById('timeStart').value = timeString;
    
//    //Stops the submit request
//    $("#rruleForm").submit(function(e){
//           e.preventDefault();
//    });
}

//function buildHistories()
//{
////	console.log('post RRULE' + formToJSON() + ipAddress);
////	$.ajax({
////		type: 'POST',
////		contentType: 'application/json',
////		url: rootURL,
////		dataType: "text",
////		data: formToJSON(),
////		success: renderList
////	});
//}

// Entry point for refresh operation
function refreshRRuleAndDTStart()
{
	buildRRule();
	buildDTStart();
}

/*
 * Generate RRULE field
 */
function buildRRule()
{
    var freq = document.getElementById('freq').value;
    var rrule = "RRULE:FREQ=" + freq;

    var interval = document.getElementById('interval').value;
    if (interval > 1)
	{
    	rrule += ";INTERVAL=" + interval;
	}
    
    /*
     * WEEKLY
     * 
     * Handle Day-of-Week options
     */
    if (freq === "WEEKLY")
	{
        document.getElementById('dayOfWeek').style.display = "inline";
    	
    	var sun = document.getElementById('sundayCheckbox').checked;
    	var mon = document.getElementById('mondayCheckbox').checked;
    	var tue = document.getElementById('tuesdayCheckbox').checked;
    	var wed = document.getElementById('wednesdayCheckbox').checked;
    	var thu = document.getElementById('thursdayCheckbox').checked;
    	var fri = document.getElementById('fridayCheckbox').checked;
    	var sat = document.getElementById('saturdayCheckbox').checked;
    	
    	if (!sun && !mon && !tue && !wed && !thu && !fri && !sat)
		{
        	document.getElementById('wednesdayCheckbox').checked = true;
        	wed = true;
		}
    	
    	rrule += ";BYDAY=";
    	if (sun)
		{
    		rrule += "SU,";
		}
    	if (mon)
		{
    		rrule += "MO,";
		}
    	if (tue)
		{
    		rrule += "TU,";
		}
    	if (wed)
		{
    		rrule += "WE,";
		}
    	if (thu)
		{
    		rrule += "TH,";
		}
    	if (fri)
		{
    		rrule += "FR,";
		}
    	if (sat)
		{
    		rrule += "SA,";
		}
    	rrule = rrule.substring(0, rrule.length - 1);
	} else
	{
    	document.getElementById('dayOfWeek').style.display = "none";
	}

    /*
     * MONTHLY options
     */
    if (freq === "MONTHLY")
	{
        document.getElementById('monthlyOptions').style.display = "inline";
    	
    	var isDayOfMonthChecked = document.getElementById('dayOfMonthCheckBox').checked;
    	var isDayOfWeekChecked = document.getElementById('dayOfWeekCheckBox').checked;
    	var dateString = document.getElementById('dateStart').value;
    	var timeString = document.getElementById('timeStart').value;
    	var date = makeDateTime(dateString, timeString)
    	var days = ['SU','MO','TU','WE','TH','FR','SA'];
    	var dayOfWeek = days[date.getDay()];

   	 	if (!isDayOfMonthChecked && !isDayOfWeekChecked)
		{
        	document.getElementById('dayOfMonthCheckBox').checked = true;
        	dom = true;
		}
    	 console.log(date + ":" + dayOfWeek);
    	if (isDayOfWeekChecked)
		{
    		var ordinal = weekOrdinalInMonth(date);
			rrule += ";BYDAY=" + ordinal + dayOfWeek;
		} else if (isDayOfMonthChecked)
		{
			rrule += ";BYMONTHDAY=" + date.getDate();
		}
	} else
	{
    	document.getElementById('monthlyOptions').style.display = "none";
	}
    
    /*
     * END criteria
     */
	var isAfterChecked = document.getElementById('afterCheckBox').checked;
	var isOnChecked = document.getElementById('onCheckBox').checked;
	if (isAfterChecked)
	{
		document.getElementById('countSpan').style.display = "inline";
		document.getElementById('untilSpan').style.display = "none";
		// Set COUNT
		var count = document.getElementById('count').value;
		var afterType = (count > 1) ? "events" : "event";
		document.getElementById('countType').innerHTML = afterType;
		rrule += ";COUNT=" + count;
	} else if (isOnChecked)
	{
		document.getElementById('untilSpan').style.display = "inline";	
		document.getElementById('countSpan').style.display = "none";
		var untilDateString = document.getElementById('until').value;
		var timeString = document.getElementById('timeStart').value;
		var timeZone = new Date().toString().substring(24);
		var offset = new Date().getTimezoneOffset();
		var untilDate = new Date(untilDateString + "T" + timeString);
		untilDate.setTime(untilDate.getTime() + untilDate.getTimezoneOffset()*60*1000); // time zone offset adjustment
		var timestamp = Date.parse(untilDate);
		if (! isNaN(timestamp))
		{
			var untilDateString = untilDate.toISOString();
			untilDateString = untilDateString.replace(/-/g, ""); // remove dashes
			untilDateString = untilDateString.replace(/:/g, ""); // remove colons
			untilDateString = untilDateString.substring(0, untilDateString.indexOf(".")) + "Z"; // remove fraction of second
			rrule += ";UNTIL=" + untilDateString;
		}
	} else
	{ // Never end checkbox
		document.getElementById('countSpan').style.display = "none";
		document.getElementById('untilSpan').style.display = "none";
	}
    
	/*
	 * Set RRULE text
	 */
    document.getElementById('rruleContent').value = rrule;
    
    /*
     * Interval type word
     */
    var intervalType = makeIntervalType(freq, document.getElementById('interval').value);
    document.getElementById('intervalType').innerHTML = intervalType;
}

function makeDateTime(dateString, timeString)
{
	if (timeString === "")
	{
		var d = new Date();
		var options = { hour12: false };
		timeString = d.toLocaleTimeString('default', options);
//		console.log("timeString:" + timeString);
//		console.log(d.toLocaleTimeString('en-US', { hour12: false }));
	}
	return new Date(dateString + "T" + timeString);
}

/*
 * Build a date string
 */
function buildDateString(date, delimiter)
{
    var yearString = date.toLocaleDateString('default', {year: 'numeric'});
    var monthString = date.toLocaleDateString('default', {month: 'numeric'});
    monthString = ("0" + monthString).slice(-2); // make 2-digits
    var dayString = date.toLocaleDateString('default', {day: 'numeric'});
    dayString = ("0" + dayString).slice(-2); // make 2-digits
    return yearString + delimiter + monthString + delimiter + dayString;
}

/*
 * Make interval or after word
 * adjusts for singular and plural
 */
function makeIntervalType(freq, value)
{
    var intervalType;
    if (freq === "DAILY")
	{
    	intervalType = "day";
	} else if (freq === "WEEKLY")
	{
		intervalType = "week";
	} else if (freq === "MONTHLY")
	{
		intervalType = "month";
	} else if (freq === "YEARLY")
	{
		intervalType = "year";
	} else if (freq === "SECONDLY")
	{
		intervalType = "second";
	} else if (freq === "MINUTELY")
	{
		intervalType = "minute";
	} else if (freq === "HOURLY")
	{
		intervalType = "hour";
	}

    if (value > 1)
	{
    	intervalType += "s";
	}
    return intervalType;
}

/*
 * Create DTSTART property for dtstartContent
 */
function buildDTStart()
{
	var dateString = document.getElementById('dateStart').value;
	dateString = dateString.replace("-","");
	dateString = dateString.replace("-","");
    var timeString = document.getElementById('timeStart').value;

    if (dateString === "")
	{
    	document.getElementById("submitButton").disabled = true;
	} else
	{
    	document.getElementById("submitButton").disabled = false;
	    var dtstartContentString = "DTSTART"
	    if (timeString === "")
		{
			dtstartContentString += ";VALUE=DATE" + ":" + dateString;
		} else
		{
			timeString = timeString.replace(":", ""); // remove minutes :
			if (timeString.indexOf(":") > 0)
			{
				timeString = timeString.replace(":", ""); // remove seconds :
			} else
			{
				timeString += "00"; // add 2 zeros is seconds isn't present
			}
			dtstartContentString += ":" + dateString + "T" + timeString;
		}
	}
	document.getElementById('dtstartContent').value = dtstartContentString;
}

function weekOrdinalInMonth(date)
{
    var firstDayInMonth = new Date(date)
    firstDayInMonth.setDate(1);
    var testDate = firstDayInMonth;
    console.log("ORDINAL:" + date + " " + firstDayInMonth);
    var ordinalWeekNumber = 0;
    while (testDate < date)
    {
        ordinalWeekNumber++;
        testDate.setDate(testDate.getDate()+7); // add one week
    }
    return ordinalWeekNumber;
	}
