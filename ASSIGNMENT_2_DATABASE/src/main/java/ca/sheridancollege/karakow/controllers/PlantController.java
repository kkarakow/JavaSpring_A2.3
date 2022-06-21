package ca.sheridancollege.karakow.controllers;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.sheridancollege.karakow.beans.Plant;
import ca.sheridancollege.karakow.beans.PlantTypes;
import ca.sheridancollege.karakow.database.DatabaseAccess;


@Controller
public class PlantController {

	@Autowired
	DatabaseAccess da;

	@GetMapping("/")
	public String goHome(Model model) {

		model.addAttribute("plant", new Plant());
		model.addAttribute("plantList", da.getPlants());
		model.addAttribute("plantTypesList", da.getPlantTypes());

		return "index";
	}

	@GetMapping("/addPlant")
	public String addPlant(Model model) {

		model.addAttribute("plant", new Plant());
		model.addAttribute("plantList", da.getPlants());
		model.addAttribute("plantTypesList", da.getPlantTypes());

		return "addPlant";
	}

	@GetMapping("/searchPlant")
	public String searchPlant(Model model) {

		model.addAttribute("plant", new Plant());
		model.addAttribute("plantList", da.getPlants());
		model.addAttribute("plantTypesList", da.getPlantTypes());
		
		return "searchPlant";

	}

	@PostMapping("/searchPlant")
	public String searchPlants(Model model, @RequestParam String plantType) {
		
		model.addAttribute("plant", new Plant());
		model.addAttribute("searchList", da.getPlantByType(plantType));
		model.addAttribute("plantTypesList", da.getPlantTypes());
		return "searchPlant";
	}

	@GetMapping("/displayPlants")
	public String displayPlants(Model model) {

		model.addAttribute("plant", new Plant());
		model.addAttribute("plantList", da.getPlants());


		return "displayPlants";
	}

	@PostMapping("/processForm")
	public String processForm(Model model,			
			@RequestParam String plantName, 
			@RequestParam String plantType, 
			@RequestParam 
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfProduction, 
			@RequestParam String nameOfSupplier, 
			@RequestParam Long quantity,
			@RequestParam double amountPaid,
			@ModelAttribute Plant plant,
			@ModelAttribute PlantTypes plantTypes) {
		
		da.insertPlant(plantName, plantType, 
			dateOfProduction, nameOfSupplier, 
			quantity, amountPaid);
		
		model.addAttribute("plant", new Plant());
		model.addAttribute("plantList", da.getPlants());
		model.addAttribute("plantType", plantType);
		model.addAttribute("plantTypesList", da.getPlantTypes());
			
		return "addPlant";

	}

}
