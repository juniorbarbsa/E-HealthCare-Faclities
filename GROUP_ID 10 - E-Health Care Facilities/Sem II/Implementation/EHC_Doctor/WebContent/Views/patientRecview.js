var patientRecview = Backbone.View.extend({

	el : "#main",

	events : {
		'click #addprescription' : 'addpres',
		'click #search' : 'searchpatient',
		'focus #patientusername' : 'changeback',
		'focus #patientpassword' : 'changeback2',
		'click #addreport' : 'addreport'
	},

	init : function() {
		this.render();
	},

	render : function() {
		console.log("render");
	},

	// get patient data
	searchpatient : function() {

		
		var patient = new patientmodel();
		patient.set("uname", $("#patientusername").val());
		patient.set("password", $("#patientpassword").val());
		var table1 = $("#patient");

		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "rest/services/checkPatient",
			// dataType : "text/plain",
			data : JSON.stringify(patient),
			success : function(response) {

				console.log(response);

				if (response === 'true') {

					//var r = jQuery.parseJSON(response.responseText);
					console.log("inside search patient");
					patient_username = $("#patientusername").val();
					
					$.ajax({
						//contentType : "text/plain",
						type : "POST",
						url : "rest/services/getPatientData/"
								+ patient_username,
						//dataType : "application/json",
						success : function(response) {

							console.log("success getPatientData");

							
							var rowcount = $("#patient tbody tr").length;
							console.log("row count:"+rowcount);
							if(rowcount != 1)
								{
								//var r = jQuery.parseJSON(response.responseText);
								var r = response;
								patient_id = r.id;
								console.log("patient id:"+patient_id);
							table1.append("<tr><td>" + r.name + "</td><td>"
									+ r.dob + "</td><td>" + r.gender
									+ "</td><td>" + r.bg + "</td><td>"
									+ r.address + "</td><td>" + r.email
									+ "</td><td>" + r.mob + "</td></tr>");
							rowcount = rowcount + 1;
							console.log("After Adding:"+rowcount);
							// console.log(r);
							// console.log("error");
								}
							else
								{
								
									console.log("cannot add");
									//rowcount = rowcount-1;
									//table1.children(rowcount).remove();
								    //table1.empty();
									$("#patient > tbody").html("");
									rowcount = rowcount - 1;
									console.log("After deleting:"+rowcount);
									//var r = jQuery.parseJSON(response.responseText);
									var r = response;
									patient_id = r.id;
									console.log("patient id:"+patient_id);
									table1.append("<tr><td>" + r.name + "</td><td>"
											+ r.dob + "</td><td>" + r.gender
											+ "</td><td>" + r.bg + "</td><td>"
											+ r.address + "</td><td>" + r.email
											+ "</td><td>" + r.mob + "</td></tr>");
									rowcount = rowcount + 1;
									console.log("After Adding:"+rowcount);
									 }
								
						

						},
						error : function(response) {
							
							console.log("error getPatientData!")
						}
					});
				} else {
					//warning show for incorrect patient details
					$("#warning").show();
					$("#patientusername").css("border-color", "red");
					$("#patientpassword").css("border-color", "red");
				}
			}
		});

	},

	// add prescription
	addpres : function() {
		console.log("inside addpres");
		var self = this;
		var validate = true;

		var prescription = new prescriptionmodel();
		prescription.set("med1", $("#med1").val());
		prescription.set("dosage1", $("#dosage1").val());
		prescription.set("m1", ($("#m1").val()==="1")? "Yes" : "No" );
		prescription.set("a1", ($("#a1").val()==="1")? "Yes" : "No");
		prescription.set("e1", ($("#e1").val()==="1")? "Yes" : "No");
		prescription.set("duration1", $("#duration1").val());
		prescription.set("med2", $("#med2").val());
		prescription.set("dosage2", $("#dosage2").val());
		prescription.set("m2", ($("#m2").val()==="1")? "Yes" : "No");
		prescription.set("a2", ($("#a2").val()==="1")? "Yes" : "No");
		prescription.set("e2", ($("#e2").val()==="1")? "Yes" : "No");
		prescription.set("duration2", $("#duration2").val());
		prescription.set("med3", $("#med3").val());
		prescription.set("dosage3", $("#dosage3").val());
		prescription.set("m3", ($("#m3").val()==="1")? "Yes" : "No");
		prescription.set("a3", ($("#a3").val()==="1")? "Yes" : "No");
		prescription.set("e3", ($("#e3").val()==="1")? "Yes" : "No");
		prescription.set("duration3", $("#duration3").val());
		prescription.set("med4", $("#med4").val());
		prescription.set("dosage4", $("#dosage4").val());
		prescription.set("m4", ($("#m4").val()==="1")? "Yes" : "No");
		prescription.set("a4", ($("#a4").val()==="1")? "Yes" : "No");
		prescription.set("e4", ($("#e4").val()==="1")? "Yes" : "No");
		prescription.set("duration4", $("#duration4").val());
		prescription.set("med5", $("#med5").val());
		prescription.set("dosage5", $("#dosage5").val());
		prescription.set("m5", ($("#m5").val()==="1")? "Yes" : "No");
		prescription.set("a5", ($("#a5").val()==="1")? "Yes" : "No");
		prescription.set("e5", ($("#e5").val()==="1")? "Yes" : "No");
		prescription.set("duration5", $("#duration5").val());
		prescription.set("id",patient_id);
		console.log(JSON.stringify(prescription));

		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "rest/services/addPrescription/" +patient_id ,
			// dataType : "text/plain",
			data : JSON.stringify(prescription),
			success : function(response) {

				console.log(response);

				if(response === 'true')
					{
					$("#sucess").show()
					setTimeout(function(){ $("#sucess").hide(); }, 2000);
					//$("#prescriptiontab").rows.html("");
					("med1", $("#med1").val(""));
					("dosage1", $("#dosage1").val(""));
					("m1", $("#m1").prop('checked', false));
					("a1", $("#a1").prop('checked', false));
					("e1", $("#e1").prop('checked', false));
					("duration1", $("#duration1").val(""));
					("med2", $("#med2").val(""));
					("dosage2", $("#dosage2").val(""));
					("m2", $("#m2").prop('checked', false));
					("a2", $("#a2").prop('checked', false));
					("e2", $("#e2").prop('checked', false));
					("duration2", $("#duration2").val(""));
					("med3", $("#med3").val(""));
					("dosage3", $("#dosage3").val(""));
					("m3", $("#m3").prop('checked', false));
					("a3", $("#a3").prop('checked', false));
					("e3", $("#e3").prop('checked', false));
					("duration3", $("#duration3").val(""));
					("med4", $("#med4").val(""));
					("dosage4", $("#dosage4").val(""));
					("m4", $("#m4").prop('checked', false));
					("a4", $("#a4").prop('checked', false));
					("e4", $("#e4").prop('checked', false));
					("duration4", $("#duration4").val(""));
					("med5", $("#med5").val(""));
					("dosage5", $("#dosage5").val(""));
					("m5", $("#m5").prop('checked', false));
					("a5", $("#a5").prop('checked', false));
					("e5", $("#e5").prop('checked', false));
					("duration5", $("#duration5").val(""));
					("patientusername", $("#patientusername").val(""));
					("patientpassword", $("#patientpassword").val(""));
					$("#patient > tbody").html("");
					}
			},
			error : function(response) {
				console.log("error");
			}
		});

	},
	changeback : function() {

		$("#patientusername").css("border", "");
		$("#warning").hide();
	},

	changeback2 : function() {

		$("#patientpassword").css("border", "");
		$("#warning").hide();
	},
	
	/*addreport : function()
	{
		var report = new reportmodel();
		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "rest/services/addReport/" +patient_id ,
			// dataType : "text/plain",
			data : JSON.stringify(report),
			success : function(response) {

				console.log(response);

				if(response === 'true')
					{
						
					}
				
					}
		});
		
	}*/

});
