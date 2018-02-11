var loginview = Backbone.View.extend({

	el : "#main",

	events : {
		'click #loginbtn' : 'loginshow',
		'click #regbtn' : 'regshow',
		'focus #username' : 'changeback',
		'focus #password' : 'changeback2'
	},

	initialize : function() {
		this.render();
	},

	render : function() {

		this.$el.html(_.template($("#login-temp").html()));
		console.log("inside login");
	},

	loginshow : function() {
		var self = this;
		console.log("inside loginshow");

		var user = new usermodel();

		user.set("username", $("#username").val());
		user.set("password", $("#password").val());

		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "rest/services/check",
			// dataType : "text/plain",
			data : JSON.stringify(user),
			success : function(response) {

				console.log(response);

				if (response === 'true') {

					current_user = $("#username").val();
					console.log(current_user);
					
					
					template = _.template($("#navtemp").html()), 
					self.$el.html(template());
					$("#homeDiv").html(_.template($("#hometemp").html()));
					
									
					//Display Todays Appointment
					
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
											
									today_appt.append("<tr><td id = ti"+n+">"+ response.patient_id 
									+ "</td><td id = tpn"+n+">"+ response.patient_name 
									+"</td><td id = tad"+n+">"+ response.appointment_date 
									+ "</td><td id = tt"+n+">" + response.time
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
							}
					});		
									
		}//if count > 0	
//					if(today_count == 0)
			else
			{
				$("#noappointents").show()
							
				 }
		

								},
								error : function(response) {
									
									
								}
});

		

						},
						error : function(response) {
							
						}
	});
} else {
			$("#warning").show();
			$("#username").css("border-color", "red");
			$("#password").css("border-color", "red");
		}

			},
			error : function(response) {
				console.log("error");
			}
		});

	},

	regshow : function() {
		console.log("inside regshow");

		template = _.template($("#regtemp").html()),

		this.$el.html(template());
	},

	changeback : function() {

		$("#username").css("border", "");
		$("#warning").hide();
	},

	changeback2 : function() {

		$("#password").css("border", "");
		$("#warning").hide();
	},

});
