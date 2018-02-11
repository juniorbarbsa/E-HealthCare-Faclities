patientmodel = Backbone.Model.extend({
	
	urlRoot : "rest/services/checkPatient",
	
	defaults:{
		
		patientusername : '',
		patientpassword : ''
	}
	
});
	
	