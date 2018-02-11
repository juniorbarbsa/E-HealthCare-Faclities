var homeview = Backbone.View.extend({

	el : "#main",

	events : {

		'click #homelink' : 'Homeshow',
		'click #updatelink' : 'updateshow',
		'click #apptlink' : 'apptshow',
		'click #patientLink' : 'PatientRecshow',
		'click #logoutbtn' : 'logout',
		'click #refresh' : 'refresh'
	},
	initialize : function() {
		this.render();
	},

	render : function() {
			},
	
	// update
	updateshow : function() {
		console.log("inside update");

		template = _.template($("#updatetemp").html()),
		$("#homeDiv").html(template());

		$.ajax({
			contentType : "text/plain",
			type : "POST",
			url : "rest/services/getDoctorData/" + current_user,
			dataType : "application/json",
			success : function(response) {

				console.log("success");

			},
			error : function(response) {
				var r = jQuery.parseJSON(response.responseText);
				console.log(r.name);
				$("#update_name").val(r.name);
				$("#update_address").val(r.address);
				$("#update_mobile").val(r.mobile);
				$("#update_gender").val(r.gender);
				$("#update_dob").val(r.dob);
				$("#update_email").val(r.email);
				$("#update_hospital").val(r.hospital);
				$("#update_speciality").val(r.speciality);
				doctor_id = r.id;
				console.log("doc id:"+doctor_id);
				// console.log(r);
				// console.log("error");
			}
		});

	},

	

	// appointments
	apptshow : function() {
		console.log("inside appointment");

		template = _.template($("#apptTemp").html()),

		$("#homeDiv").html(template());
		
		//show new appointments
		
		var table = $("#appointment1");
		console.log("doctor id-" + doctor_id);
		var x = 0;
		var i = 0;
		
		$.ajax({
			//contentType : "text/plain",
			type : "POST",
			url : "rest/services/getAppointmentCount/" + doctor_id,
			//dataType : "application/json",
			success : function(response) {
				
				console.log("success get new appointments count");
				count = response
				 //count = jQuery.parseJSON(response.responseText);
				console.log("count = "+count);
				
				if(count>0)
					{
					$.ajax({
						//contentType : "text/plain",
						type : "POST",
						url : "rest/services/getAppointmentData/" + doctor_id,
						//dataType : "application/json",
						
						success : function(response) {

							console.log("success getAppointmentData");

							
							console.log("x="+x);
							var z = count;
							for(var z = count ; z > 0 ; z-- )
								{
							$.ajax({
								//contentType : "text/plain",
								type : "POST",
								url : "rest/services/getSingleAppointment/" + x,
								//dataType : "application/json",
								
								success : function(response) {

									console.log("success get singleappointment");

									var r = response
									//var r = jQuery.parseJSON(response.responseText);
									console.log(r);
									
								
								
									var statusid = "Row"+i;
									var row = $("<tr />");
									var status = $('<input />', {
										type : 'checkbox',
										id : statusid,
										value : "No"
									});

									console.log("checkboxid=" + statusid);
									var cid = i;
									
									table.append("<tr><td id = a"+i+">" + response.appointment_id
											+"</td><td id = p"+i+">"+ response.patient_id 
											+ "</td><td id = pn"+i+">"+ response.patient_name 
											+"</td><td id = ad"+i+">"+ response.appointment_date 
											+ "</td><td id = t"+i+">" + response.time
											+ "</td><td id = cid"+i+">"
											+ "</td></tr>");
									
									var y = "cid"+i;
									console.log("td id=" +y);
									status.appendTo("#cid"+i).html();
									i = i + 1;
									
								   
								
								},
								error : function(response) {
									
									console.log("error in getsingleappointmet");
								}

							
							});
							
							x = x + 1;
							
								}
						
						
						},
						error : function(response) {
							
							console.log("error in getnewappointments");
						}
					});		
				
				
					}//if count > 0
							
			

			},
			error : function(response) {
				
				console.log("error in gettodaycount");
			}	
			
});
		
		
		//Display Confirmed Appointments
		

		var confirm = $("#confirmed_appointmets");
		console.log("doctor id-" + doctor_id);
		var a = 0;
		var b = 0;
		
		$.ajax({
			//contentType : "text/plain",
			type : "POST",
			url : "rest/services/getConfirmedCount/" + doctor_id,
			//dataType : "application/json",
			success : function(response) {
				
				console.log("success getconfirmed count");
				confirm_count = response;
				//confirm_count = jQuery.parseJSON(response.responseText);
				console.log("confirm_count = "+confirm_count);
				
				if(confirm_count>0)
										{
										$.ajax({
											//contentType : "text/plain",
											type : "POST",
											url : "rest/services/getConfirmApptointment/" + doctor_id,
											//dataType : "application/json",
														
										success : function(response) {
										
										console.log("success getconfirmedappointment");

										
										
										var z = confirm_count;
										for(var z = confirm_count ; z > 0 ; z-- )
											{
														$.ajax({
																//contentType : "text/plain",
																type : "POST",
																url : "rest/services/getConfirmed/" + a,
																//dataType : "application/json",
																								
																success : function(response) {
																
																console.log("success getconfirmed");

																var s = response;
																//var s = jQuery.parseJSON(response.responseText);
																									
																console.log(s);
																								
																
																
																								
																confirm.append("<tr><td aid = a"+b+">" + response.appointment_id
																+"</td><td id = pid"+b+">"+ response.patient_id 
																+ "</td><td id = pname"+b+">"+ response.patient_name 
																+"</td><td id = adate"+b+">"+ response.appointment_date 
																+ "</td><td id = time"+b+">" + response.time
																+ "</td></tr>");
																
																b = b + 1;							
																   	
																	},
																error : function(response) {
																	
																	console.log("error in getconfirmed");
																}
																						
																});
																			
													a = a + 1;
																				
													}
													
										},
										error : function(response) {
											
											console.log("error in getconfirmedappointment")
										}
										});		
														
														
								}//if count > 0				

			},
			error : function(response) {
				console.log("error in get confirm count!")
			}
});
	},
	

	// patient records

	PatientRecshow : function() {
		console.log("inside patient record");

		template = _.template($("#patientTemp").html()),

		$("#homeDiv").html(template());
	},

	// home
	Homeshow : function() {
		console.log("inside home");
		template = _.template($("#hometemp").html()),

		$("#homeDiv").html(template());
		
		//Display todays appointment

		var today_appt = $("#today_appointment");
		console.log("doctor id-" + doctor_id);
		var m = 0;
		var n = 0;
		
		$.ajax({
			//contentType : "text/plain",
			type : "POST",
			url : "rest/services/getDoctorData/" + current_user,
			//dataType : "application/json",
			success : function(response) {

				console.log("success");

				//var r = jQuery.parseJSON(response.responseText);

				$("#welcome").html("Welcome " + response.name);
				doctor_id = response.id;
				console.log("doc id:"+doctor_id);
				
				console.log("todays appointment");
				
				//Display todays appointment

				var today_appt = $("#today_appointment");
				console.log("doctor id:" + doctor_id);
				var m = 0;
				var n = 0;
				
				$.ajax({
					//contentType : "text/plain",
					type : "POST",
					url : "rest/services/getTodaysCount/" + doctor_id,
					//dataType : "application/json",
					success : function(response) {
						
						console.log("success todaycount");
						today_count = response;
						//today_count = jQuery.parseJSON(response.responseText);
						console.log("today_count = "+today_count);
	//display todays schedule					
		if(today_count>0)
		{
		$.ajax({
			//contentType : "text/plain",
			type : "POST",
			url : "rest/services/getTodayApptointment/" + doctor_id,
			//dataType : "application/json",
						
		success : function(response) {

		console.log("success getTodayAppointment");
		

		
		var z = today_count;
		for(var z = today_count ; z > 0 ; z-- )
		{
		$.ajax({
				//contentType : "text/plain",
				type : "POST",
				url : "rest/services/getTodaysAppt/" + m,
				//dataType : "application/json",
												
				success : function(response) {

					console.log("success getTodaysAppt");
					

					var s = response;
					//var s = jQuery.parseJSON(response.responseText);
														
					console.log(s);
								
						today_appt.append("<tr><td id = ti"+n+">"+ s.patient_id 
						+ "</td><td id = tpn"+n+">"+ s.patient_name 
						+"</td><td id = tad"+n+">"+ s.appointment_date 
						+ "</td><td id = tt"+n+">" + s.time
						+ "</td></tr>");
	
						n = n + 1;	
													
						},
				error : function(response) {						
								   }
								
							});
								
							m = m + 1;
									
					}
								
		},
		error : function(response) {
			console.log(response);
				}
		});		
						
}//if count > 0	
//		if(today_count == 0)
else
{
	$("#noappointents").show()
				
	 }
	},
	error : function(response)
	{
						console.log(response);
						
					}
});



			},
			error : function(response) {
				console.log(response);
			}
});
		},

	// logout
	logout : function() {
		this.$el.html(_.template($("#login-temp").html()));
	},
	
	//refresh
	refresh : function()
	{
		$( "#refresh" ).on( "click", function() {
			console.log("inside refresh");  
			//$("#apptshow");
			  
			});
			$( "#apptlink" ).trigger( "click" );

	}

});
