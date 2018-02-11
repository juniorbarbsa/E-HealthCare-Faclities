doctor = Backbone.Model.extend({
	
	urlRoot : "rest/services/newuser",
	
	defaults:{
		
		name: '',
		address:'',
		gender:'',
		hospital:'',
		speciality : '',
		email:'',
		mobile:'',
		dob:'',
		username:'',
		password:'',
		id:''
	}
	
});
	
	