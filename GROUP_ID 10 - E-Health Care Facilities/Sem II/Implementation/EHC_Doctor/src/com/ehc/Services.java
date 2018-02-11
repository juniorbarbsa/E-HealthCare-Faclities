package com.ehc;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONException;
import org.json.JSONObject;

import com.ehc.bean.Appointment;
import com.ehc.bean.Doctor;
import com.ehc.bean.Patient;
import com.ehc.bean.Patientuser;
import com.ehc.bean.UpdateDoctor;
import com.ehc.bean.User;
import com.ehc.bean.patientRecBean;

@Path("/services")
public class Services {

	JSONObject json = new JSONObject();

	@POST
	@Path("/check")
	@Consumes("application/json")
	@Produces("text/plain")
	public String checkUser(User user) {

		Boolean exists = CheckUser.check(user);

		try {

			json.put("value", exists.toString());
			// System.out.println((String)(json.get("value")));
			return (String) json.get("value");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@POST
	@Path("/submit")
	@Consumes("application/json")
	@Produces("text/plain")
	public String submit(Doctor doctor) {

		Boolean newuser = SubmitDoctor.submit(doctor);
		try {
			json.put("value", newuser.toString());
			System.out.println((String) (json.get("value")));
			return (String) json.get("value");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		System.out.println(doctor);
		return "submitted";
	}

	@POST
	@Path("addPrescription/{patientid}")
	@Consumes("application/json")
	@Produces("text/plain")
	public String addPrescription(patientRecBean prescription, @PathParam("patientid") String patientid) {

		Boolean newpres = AddPrescription.add(prescription,patientid);
		try {
			json.put("value", newpres.toString());
			System.out.println((String) (json.get("value")));
			return (String) json.get("value");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		System.out.println(prescription);
		return "submitted";
	}

	@POST
	@Path("getDoctorData/{username}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Doctor getDoctorData(@PathParam("username") String username) {

		System.out.println("got " + username);
		return GetDocData.getData(username);

	}

	@POST
	@Path("updateDoctor/{username}")
	@Consumes("application/json")
	@Produces("text/plain")
	public String update(UpdateDoctor doc1,@PathParam("username") String username) {

		Boolean newuser = UpdateDocData.update(doc1, username);
		try {
			json.put("value", newuser.toString());
			System.out.println("update response:"+(String) (json.get("value")));
			System.out.println("update " + username);
			return (String) json.get("value");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		System.out.println(doc1);
		return "submitted";
	}
	
	
	@POST
	@Path("/checkPatient")
	@Consumes("application/json")
	@Produces("text/plain")
	public String checkPatient(Patientuser patientuser) {

		Boolean exists = CheckPatient.check(patientuser);

		try {

			json.put("value", exists.toString());
			 System.out.println((String)(json.get("value")));
			return (String) json.get("value");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@POST
	@Path("getPatientData/{patientusername}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Patient getPatientData(@PathParam("patientusername") String patient_username) {

		System.out.println("got " +patient_username);
		return GetPatientData.getData(patient_username);

	}
	
	@POST
	@Path("getAppointmentData/{doctor_id}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Appointment getAppointmentData(@PathParam("doctor_id") String doctor_id) {

		
		System.out.println("got " +doctor_id);
		return (Appointment) GetAppointmentData.getData(doctor_id);

	}
	
	@POST
	@Path("getSingleAppointment/{x}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Appointment getSingleAppointment(@PathParam("x") String x) {

		
		System.out.println("got " +x);
		return GetAppointmentData.Data(x);

	}
	

	
	@POST
	@Path("getAppointmentCount/{doctor_id}")
	@Consumes("text/plain")
	@Produces("application/json")
	public int getAppointmentCount(@PathParam("doctor_id") String doctor_id) {

		
		System.out.println("got " +doctor_id);
		int x = AppointmentCount.getData(doctor_id);
		return x;

	}
	
	
	@POST
	@Path("updateAppointment/{apptID}")
	@Consumes("application/json")
	@Produces("text/plain")
	public String updateAppointment(Appointment app,@PathParam("apptID") String apptID) {

		Boolean newAppt = UpdateAppointment.update(app, apptID);
		try {
			json.put("value", newAppt.toString());
			System.out.println((String) (json.get("value")));
			System.out.println("update " + apptID);
			return (String) json.get("value");
		} catch (JSONException e) {
			e.printStackTrace();

		}
		System.out.println(apptID);
		return "submitted";
	}
	
	
	@POST
	@Path("getConfirmedCount/{doctor_id}")
	@Consumes("text/plain")
	@Produces("application/json")
	public int getConfirmedCount(@PathParam("doctor_id") String doctor_id) {

		
		System.out.println("got " +doctor_id);
		int x = GetConfirmedAppointment.getConfirmedCount(doctor_id);
		return x;

	}
	
	
	@POST
	@Path("getConfirmApptointment/{doctor_id}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Appointment getConfirmApptointment(@PathParam("doctor_id") String doctor_id) {

		
		System.out.println("got " +doctor_id);
		return (Appointment) GetConfirmedAppointment.getConfirmApptointment(doctor_id);

	}
	
	@POST
	@Path("getTodayApptointment/{doctor_id}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Appointment getTodayApptointment(@PathParam("doctor_id") String doctor_id) {

		
		System.out.println("got " +doctor_id);
		return (Appointment) GetConfirmedAppointment.getTodayApptointment(doctor_id);

	}
	
	@POST
	@Path("getConfirmed/{a}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Appointment getConfirmed(@PathParam("a") String x) {

		
		System.out.println("got " +x);
		return GetConfirmedAppointment.getConfirmed(x);

	}
	
	
	
	
	@POST
	@Path("getTodaysCount/{doctor_id}")
	@Consumes("text/plain")
	@Produces("application/json")
	public int getTodaysCount(@PathParam("doctor_id") String doctor_id) {

		
		System.out.println("got " +doctor_id);
		int x = GetConfirmedAppointment.getTodaysCount(doctor_id);
		return x;

	}
	
	@POST
	@Path("getTodaysAppt/{a}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Appointment getTodaysAppt(@PathParam("a") String x) {

		
		System.out.println("got " +x);
		return GetConfirmedAppointment.getTodaysAppt(x);

	}
	
	
	/*@POST
	@Path("/addReport")
	@Consumes("application/json")
	@Produces("text/plain")
	public boolean addReport() 
	{
		
	}
*/
}