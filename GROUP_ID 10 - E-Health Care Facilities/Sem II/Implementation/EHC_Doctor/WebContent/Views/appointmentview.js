var appointmentview = Backbone.View.extend({

	el : "#main",

	events : {
		'click #confirm' : 'confirmAppointment'
	},

	initialize : function() {
		this.render();
	},

	render : function() {
		console.log("inside appointment");

		var self = this;
	},

	confirmAppointment : function() {
		console.log("inside confirm appt");
		var i = 0;

		var app = new appointment();
		for (i = 0; i < count ; i++)
			
			{
		if ($("#Row" + i).prop('checked')) {
			console.log($("#p"+i).text());
			console.log($("#ad"+i).text());
			console.log($("#t"+i).text());
			console.log($("#pn"+i).text())
			console.log($("#a"+i).text());
			console.log("checked!");
			
			app.set("appointment_id",$("#a"+i).text());
			app.set("patient_id",$("#p"+i).text());
			app.set("patient_name",$("#pn"+i).text());
			app.set("appointment_date",$("#ad"+i).text());
			app.set("time",$("#t"+i).text());
			app.set("status","Confirmed");
			var apptID = $("#a"+i).text();
			$.ajax({
				contentType : "application/json",
				type : "POST",
				url : "rest/services/updateAppointment/" + apptID,
				dataType : "text/plain",
				data : JSON.stringify(app),
				success : function(response) {

					console.log(response);

				},
				error : function(response) {
					console.log("updated");
					//console.log("b="+b);
					
					$("#appointment1 > tbody").html("");
					
				}
			});
		}
		else
			{
			
			app.set("appointment_id",$("#a"+i).text());
			app.set("patient_id",$("#p"+i).text());
			app.set("patient_name",$("#pn"+i).text())
			app.set("appointment_date",$("#ad"+i).text());
			app.set("time",$("#t"+i).text());
			app.set("status","Rejected");

			var apptID = $("#a"+i).text();
			$.ajax({
				contentType : "application/json",
				type : "POST",
				url : "rest/services/updateAppointment/" + apptID,
				dataType : "text/plain",
				data : JSON.stringify(app),
				success : function(response) {

					console.log(response);

				},
				error : function(response) {
					console.log("updated");
					$("#appointment1 > tbody").html("");
				}
			});

			}
		}
	}

});