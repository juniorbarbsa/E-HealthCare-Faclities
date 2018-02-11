updateDoctorModel = Backbone.Model.extend({
	
	urlRoot : "rest/services/updateDoctor",
	
	defaults:{
		
		name: '',
		address:'',
		mobile:'',
		gender:'',
		hospital:'',
		speciality : '',
		email:'',
		dob:''
	}

});
