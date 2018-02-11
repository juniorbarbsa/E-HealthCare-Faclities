package com.project.ehealthcarefacilities;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

public class Map extends Activity {
	
	private GoogleMap googleMap;
	
	
	//public double latitude = 18.530398;
	//public double longitude = 73.876534;
	
	public double[] lat = {18.530398, 18.5033406, 18.493551, 18.5021052, 18.4984649, 18.5108318, 18.562595, 18.529982, 18.5197846, 18.5237566, 18.5305138, 18.5205055, 18.5338469, 18.5545094, 18.535224, 18.4834606, 18.5049964};
	public double[] lon = {73.876534, 73.9001922, 73.9094035, 73.8322119, 73.8869064, 73.842062, 73.810669, 73.853024, 73.8669134, 73.8617661, 73.847545, 73.8657739, 73.8869448, 73.8036271, 73.8762853, 73.800463 ,73.9271109};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		try {
			// Loading map
			initilizeMap();

			// Changing map type
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

			// Showing / hiding your current location
			googleMap.setMyLocationEnabled(true);

			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(true);

			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);

			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);

			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);
			
			

			CurrentLocation gps = new CurrentLocation(Map.this);
			Location location = gps.getLocation();
			if(location!=null){
				//Current Location
				MarkerOptions marker = new MarkerOptions().position(
						new LatLng(location.getLatitude(),location.getLongitude()))
						.title("You are here!");
				
				marker.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
				
				googleMap.addMarker(marker);
				
				//Jehangir Hospital
				MarkerOptions marker1 = new MarkerOptions().position(
						new LatLng(lat[0],lon[0]))
						.title("Jehangir Hospital");
				
				marker1.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker1);
				
				//Inamdar Hospital
				MarkerOptions marker2 = new MarkerOptions().position(
						new LatLng(lat[1],lon[1]))
						.title("Inamdar MultiSpeciality Hospital");
				
				marker2.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker2);
				
				//Ruby Hall Clinic Wanowrie
				MarkerOptions marker3 = new MarkerOptions().position(
						new LatLng(lat[2],lon[2]))
						.title("Ruby Hall Clinic Wanowrie");
				
				marker3.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker3);
				
				//Deenanath Mangeshkar Hospital and Research Center
				MarkerOptions marker4 = new MarkerOptions().position(
						new LatLng(lat[3],lon[3]))
						.title("Deenanath Mangeshkar Hospital and Research Center");
				
				marker4.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker4);
				
				//Southern Command Hospital 
				MarkerOptions marker5 = new MarkerOptions().position(
						new LatLng(lat[4],lon[4]))
						.title("Southern Command Hospital");
				
				marker5.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker5);
				
				//Poona Hospital 
				MarkerOptions marker6 = new MarkerOptions().position(
						new LatLng(lat[5],lon[5]))
						.title("Poona Hospital");
				
				marker6.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker6);
				
				//AIMS hospital
				MarkerOptions marker7 = new MarkerOptions().position(
						new LatLng(lat[6],lon[6]))
						.title("AIMS Hospital");
				
				marker7.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker7);
				
				//sancheti hospital 
				MarkerOptions marker8 = new MarkerOptions().position(
						new LatLng(lat[7],lon[7]))
						.title("Sancheti Hospital");
				
				marker8.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker8);
				
				//KEM hospital
				MarkerOptions marker9 = new MarkerOptions().position(
						new LatLng(lat[8],lon[8]))
						.title("KEM Hospital");
				
				marker9.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker9);
				
				//Sahyadri Specialty Hospital
				MarkerOptions marker10 = new MarkerOptions().position(
						new LatLng(lat[9],lon[9]))
						.title("Sahyadri Speciality Hospital");
				
				marker10.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker10);
				
				//Hardikar Hospital 
				MarkerOptions marker11 = new MarkerOptions().position(
						new LatLng(lat[10],lon[10]))
						.title("Hardikar Hospital");
				
				marker11.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker11);
				
				//Sasson Hospital
				MarkerOptions marker12 = new MarkerOptions().position(
						new LatLng(lat[11],lon[11]))
						.title("Society of Friends of Sassoon Hospital");
				
				marker12.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker12);
				
				//Inlaks and Budhrani Hospital
				MarkerOptions marker13 = new MarkerOptions().position(
						new LatLng(lat[12],lon[12]))
						.title("Inlaks and Budhrani Hospital");
				
				marker13.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker13);
				
				//Aditya Birla Clinic
				MarkerOptions marker14 = new MarkerOptions().position(
						new LatLng(lat[13],lon[13]))
						.title("Aditya Birla Clinic");
				
				marker14.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker14);
				
				//Ruby Hall Clinic
				MarkerOptions marker15 = new MarkerOptions().position(
						new LatLng(lat[14],lon[14]))
						.title("Ruby Hall Clinic");
				
				marker15.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker15);
				
				//Lodha Hospital
				MarkerOptions marker16 = new MarkerOptions().position(
						new LatLng(lat[15],lon[15]))
						.title("Lodha Hospital");
				
				marker16.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker16);
				
				//Nobel Hospital
				MarkerOptions marker17 = new MarkerOptions().position(
						new LatLng(lat[16],lon[16]))
						.title("Nobel Hospital");
				
				marker17.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				googleMap.addMarker(marker17);
				
				
				
				CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(location.getLatitude(),location.getLongitude())).zoom(14).build();

				googleMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(cameraPosition));
				
				
			}
			else{
				Toast.makeText(getApplicationContext(), "GPS is not enabled", Toast.LENGTH_SHORT).show();
			}
									
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
