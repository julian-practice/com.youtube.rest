package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Oracle308tube;
import com.youtube.dao.Schema308tube;
import com.youtube.util.ToJSON;



	@Path("/v2/inventory")
	public class V2_inventory {

		/**
		 * This method will return the specific brand of PC parts the user is looking for.  
		 * It uses a QueryParam bring in the data to the method.
		 * 
		 * Example would be:
		 * http://localhost:7001/com.youtube.rest/api/v2/inventory?brand=ASUS
		 * 
		 * @param brand - product brand name
		 * @return - json array results list from the database
		 * @throws Exception
		 */
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response returnBrandParts(
					@QueryParam("brand") String brand)
					throws Exception {
			
			String returnString = null;
			JSONArray json = new JSONArray();
			
			try {
				
				//return a error is brand is missing from the url string
				if(brand == null) {
					return Response.status(400).entity("Error: please specify brand for this search").build();
				}
				
				Schema308tube dao = new Schema308tube();
				
				json = dao.queryReturnBrandParts(brand);
				returnString = json.toString();
				
			}
			catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			return Response.ok(returnString).build();
		}
		
		
}
