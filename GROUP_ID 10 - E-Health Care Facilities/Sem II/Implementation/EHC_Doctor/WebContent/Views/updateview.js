var updateview = Backbone.View.extend({

	el : "#main",

	events : {
		'click #updateBtn' : 'update'
	},

	init : function() {
		this.render();
	},

	render : function() {
		console.log("render");
	},

	update : function() {
		
		var self = this;
		var check;
		console.log("inside updatedoc");var check ;
		
		var reg_name = new RegExp("^[a-zA-Z ]{2,30}$");
		var check_name = (reg_name.test($("#update_name").val()));
		console.log("name:"+check_name);
		
		var check_address = $("#update_address").val().length;
		console.log("addr:"+check_address);
		
		var reg_email = new RegExp("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		var check_email = (reg_email.test($("#update_email").val()));
		console.log("email:"+check_email);
		
		var reg_mobile = new RegExp("^[0-9]{10}$");
		var check_mobile = (reg_mobile.test($("#update_mobile").val()));
		console.log("mobile:"+check_mobile);
		
		/*var check_username = $("#username").val().length;
		console.log("username:"+check_username);*/
		
		var reg_dob = new RegExp("^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$");
		var check_dob = (reg_dob.test($("#update_dob").val()));
		console.log("dob:"+check_dob);
		
		/*var reg_password = new RegExp("^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{4,8}$");
		var check_password = (reg_password.test($("#password").val()));
		console.log("password:"+check_password);
		var  password = $("#password").val();
		var confirm_password = $("#password2").val();*/
		
		if(check_name == true && check_address > 0 && check_email == true && check_mobile == true
				&& check_dob == true)
			{
				check = true;
				console.log("check="+check);
			}
			
		else
			{
				check = false;
				console.log("check="+check);
			}
		
		
		
		if(check == true){
		
		var doc1 = new updateDoctorModel();

		doc1.set("name", $("#update_name").val());
		doc1.set("address", $("#update_address").val());
		doc1.set("gender", $("#update_gender").val());
		doc1.set("email", $("#update_email").val());
		doc1.set("mobile", $("#update_mobile").val());
		doc1.set("dob", $("#update_dob").val());
		doc1.set("hospital", $("#update_hospital").val());
		doc1.set("speciality", $("#update_speciality").val());

		console.log(JSON.stringify(doc1));
//		console.log(current_user);

		$.ajax({

			contentType : "application/json",
			type : "POST",
			url : "rest/services/updateDoctor/" + current_user,
			dataType : "text/plain",
			data : JSON.stringify(doc1),
			success : function(response) {
				
				console.log("inside update success")
				console.log("update:"+response);
				
				if(response === 'true')
					{
						$("#updated").show();
					}
				if(check_name == true)
					$("#update_name").css("border-color", "green");
				if(check_address < 0 )
					$("#update_address").css("border-color", "green");
				if(check_email == true)
					$("#update_email").css("border-color", "green");
				if(check_mobile == true)
					$("#update_mobile").css("border-color", "green");
				if(check_dob == true)
					$("#update_dob").css("border-color", "green");
				/*template = _.template($("#hometemp").html()),
				$("#homeDiv").html(template());*/
			},
			
			error : function(response) {
				console.log("error :"+response);
				$("#updated").show();
				template = _.template($("#hometemp").html()),
				$("#homeDiv").html(template());
			}
		});

		}
		else
			{
			$("#warning").show();
			if(check_name == false)
				$("#update_name").css("border-color", "red");
			if(check_address < 0 )
				$("#update_address").css("border-color", "red");
			if(check_email == false)
				$("#update_email").css("border-color", "red");
			if(check_mobile == false)
				$("#update_mobile").css("border-color", "red");
			if(check_dob == false)
				$("#update_dob").css("border-color", "red");
			}

	}
});