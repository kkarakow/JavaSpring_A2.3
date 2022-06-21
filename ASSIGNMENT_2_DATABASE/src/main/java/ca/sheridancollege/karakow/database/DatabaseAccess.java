package ca.sheridancollege.karakow.database;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.karakow.beans.Plant;
import ca.sheridancollege.karakow.beans.PlantTypes;



@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertPlant(String plantName, String plantType, 
			Date dateOfProduction, String nameOfSupplier, 
			Long quantity, double amountPaid) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query="INSERT INTO plant(plantName, plantType, dateOfProduction, "
				+ "nameOfSupplier, quantity, amountPaid) "
				+ "VALUES(:plantName, :plantType, :dateOfProduction, "
				+ ":nameOfSupplier, :quantity, :amountPaid)";
		
		namedParameters.addValue("plantName", plantName);
		namedParameters.addValue("plantType", plantType.toUpperCase());
		namedParameters.addValue("dateOfProduction", dateOfProduction);
		namedParameters.addValue("nameOfSupplier", nameOfSupplier);
		namedParameters.addValue("quantity", quantity);
		namedParameters.addValue("amountPaid", quantity);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if (rowsAffected > 0) {
			System.out.println("Inserted plant into database");
		}
	}
	
	public List<Plant> getPlants(){
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM plant";
		
		return jdbc.query(query, namedParameters, 
				new BeanPropertyRowMapper<Plant>(Plant.class));
	}
	
	public List<PlantTypes> getPlantTypes(){
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM plantTypesList";
		
		return jdbc.query(query, namedParameters, 
				new BeanPropertyRowMapper<PlantTypes>(PlantTypes.class));
			
	}
	
	public List<Plant> getPlantByType(String plantType) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM plant WHERE plantType = :plantType"; 
		
		namedParameters.addValue("plantType", plantType.toUpperCase());
		
		return jdbc.query(query, namedParameters, 
				new BeanPropertyRowMapper<Plant>(Plant.class));
		
	}
}
