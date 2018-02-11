reportmodel = Backbone.Model.extend({
	
	urlRoot : "rest/services/getAppointmentCount",
	
	defaults:{
		
		 reportname : '',
		 dateoftest : '',
		 hospitalname : '',
		 doctorname : '',
		 reportfile : ''

	}
	
});
	
	