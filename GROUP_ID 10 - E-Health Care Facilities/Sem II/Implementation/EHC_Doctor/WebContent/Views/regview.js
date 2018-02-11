var regview = Backbone.View
		.extend({

			el : "#main",

			events : {
				/*'blur #name' : 'validatename',
				'blur #email' : 'validateemail',
				'blur #mobile' : 'validatemobile',
				'blur #address' : 'validateaddress',
				'blur #username' : 'validateusername',
				'blur #password' : 'validatepassword',
				'focus #dob' : 'decrement',
				'focus #name' : 'decrement',
				'focus #email' : 'decrement',
				'focus #mobile' : 'decrement',
				'focus #address' : 'decrement',
				'focus #username' : 'decrement',
				'focus #password' : 'decrement',
				'focus #dob' : 'decrement',*/
				'click #backbtn' : 'backlogin',
				'click #submitBtn' : 'submitdata'
			},
			initialize : function() {
				this.render();
			},

			render : function() {

					console.log("inside render");
					/*var validate = false;
					var i;
					var self = this;
					self.i = 1;*/
				},

			submitdata : function() {
				console.log("inside submit");
				var self = this;
				var check ;
				
				var reg_name = new RegExp("^[a-zA-Z ]{2,30}$");
				var check_name = (reg_name.test($("#name").val()));
				console.log("name:"+check_name);
				
				var check_address = $("#address").val().length;
				console.log("addr:"+check_address);
				
				var reg_email = new RegExp("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
				var check_email = (reg_email.test($("#email").val()));
				console.log("email:"+check_email);
				
				var reg_mobile = new RegExp("^[0-9]{10}$");
				var check_mobile = (reg_mobile.test($("#mobile").val()));
				console.log("mobile:"+check_mobile);
				
				var check_username = $("#username").val().length;
				console.log("username:"+check_username);
				
				var reg_dob = new RegExp("^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$");
				var check_dob = (reg_dob.test($("#dob").val()));
				console.log("dob:"+check_dob);
				
				var reg_password = new RegExp("^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{4,8}$");
				var check_password = (reg_password.test($("#password").val()));
				console.log("password:"+check_password);
				var  password = $("#password").val();
				var confirm_password = $("#password2").val();
				
				if(check_name == true && check_address > 0 && check_email == true && check_mobile == true
						&& check_username > 4 && check_dob == true && check_password == true && password == confirm_password)
					{
						check = true;
						console.log("check="+check);
					}
					
				else
					{
						check = false;
						console.log("check="+check);
					}
				
				if(check_name == false)
					$("#name").css("border-color", "red");
				if(check_address < 0 )
					$("#address").css("border-color", "red");
				if(check_email == false)
					$("#email").css("border-color", "red");
				if(check_mobile == false)
					$("#mobile").css("border-color", "red");
				if(check_dob == false)
					$("#dob").css("border-color", "red");
				if(check_username < 4 )
					$("#username").css("border-color", "red");
				if(check_password == false)
					$("#password").css("border-color", "red");
				if(confirm_password != password)
					{
						$("#password").css("border-color", "red");
						$("#password").css("border-color", "red");
					}
				if (check == true) {

					var doc = new doctor();

					doc.set("name", $("#name").val());
					doc.set("address", $("#address").val());
					doc.set("gender", $("#gender").val());
					doc.set("email", $("#email").val());
					doc.set("mobile", $("#mobile").val());
					doc.set("dob", $("#dob").val());
					doc.set("hospital", $("#hospital").val());
					doc.set("speciality", $("#speciality").val());
					doc.set("username", $("#username").val());
					doc.set("password", $("#password").val());

					console.log(JSON.stringify(doc));

					$.ajax({
						contentType : "application/json",
						type : "POST",
						url : "rest/services/submit",
						// dataType : "text/plain",
						data : JSON.stringify(doc),
						success : function(response) {

							console.log(response);

							if (response === 'true') {

								template = _.template($("#login-temp").html()),
										self.$el.html(template());
							} else {
								$("#warning").show();

							}

						},
						error : function(response) {
							console.log("error"+ response);
						}
					});

				}

				else {
					$("#warning").show();
				}

			},
			/*//decrement
			decrement : function(){
				var self = this;
				console.log("decrement");
				self.i = self.i - 1;
				console.log("i="+self.i);
			},

			// validate name
			validatename : function() {

				var n = $("#name");
				var self = this;
				var reg = new RegExp("^[a-zA-Z ]{2,30}$");
				if (reg.test(n.val())) {
					
					self.i = self.i + 1;
					console.log("name i ="+self.i);
					
					$("#name").css("border-color", "green");
				} else {

					//self.i = self.i - 1;
					$("#name").css("border-color", "red");
				}

			},

			// validate email id
			validateemail : function() {
				var self = this;
				var n = $("#email");
				var reg1 = new RegExp(
						"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
				if (reg1.test(n.val())) {
					self.i = self.i + 1;
					console.log("emailid i = "+self.i);
					$("#email").css("border-color", "green");
				} else {

					//self.i = self.i - 1;
					$("#email").css("border-color", "red");
				}
			},

			// validate mobile number
			validatemobile : function() {
				var self = this;
				var n = $("#mobile");
				var reg2 = new RegExp("^[0-9]{10}$");
				if (reg2.test(n.val())) {

					self.i = self.i + 1;
					$("#mobile").css("border-color", "green");
				} else {

					//self.i = self.i - 1;
					$("#mobile").css("border-color", "red");
				}
			},

			// validate address
			validateaddress : function() {
				var self = this;
				var addr = $("#address");
				var len = addr.val().length;
				if (len > 0) {
					self.i = self.i + 1;
					$("#address").css("border-color", "green");
				} else {

					//self.i = self.i - 1;
					$("#address").css("border-color", "red");
				}
			},

			// validate dob

			
			  validatedob : function() 
			  { 
				  var self = this;
				  var d = $("#dob"); 
				  var reg2 = new RegExp("^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$");
				  
				  console.log("test date:"+reg2.test(d.val()))
				  if(reg2.test(d.val())) 
					  {
							  
							  self.i = self.i + 1 ;
							  $("#dob").css("border-color", "green"); 
					  } 
				  else
					  {
							  
							  self.i = self.i - 1 ;
							  $("#dob").css("border-color", "red"); 
					  } 
			  },
			 

			// validate username
			validateusername : function() {
				var self = this;
				var username = $("#username");
				var len = username.val().length;
				if (len > 4) {
					self.i = self.i + 1;
					$("#username").css("border-color", "green");
				} else {

					//self.i = self.i - 1;
					$("#username").css("border-color", "red");
				}
			},
			// validate password
			validatepassword : function() {
				var self = this;
				var n1 = $("#password");
				self.i = self.i + 1;
				$("#password").css("border-color", "green");

				var reg3 = new RegExp(
						"^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{4,8}$");
				var n2 = $("#password2");
				console.log($("#password2"));
				console.log(reg3.test(n1.val()));
				if (reg3.test(n1.val())) {

					$("#password").css("border-color", "green");
					console.log("password i = "+self.i);
					console.log((n2.val())==(n1.val()));
					self.i = self.i + 1;
				} else {

					//self.i = self.i - 1;
					$("#password").css("border-color", "red");
				}

			},*/

			backlogin : function() {
				console.log("inside back");
				this.$el.html(_.template($("#login-temp").html()))
				// load template of login
			}

		});
