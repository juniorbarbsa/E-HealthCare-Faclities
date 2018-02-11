appointment = Backbone.Model.extend({
	
	urlRoot : "rest/services/getAppointmentCount",
	
	defaults:{
		
		 appointment_id : '',
		 doctor_id : '',
		 patient_id : '',
		 patient_name : '',
		 speciality : '',
		 appointment_date : '',
		 time : '',
		 status : ''

	}
	
});
	
	