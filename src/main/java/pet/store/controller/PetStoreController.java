package pet.store.controller;

import java.util.List;  
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;
	
@RestController
@RequestMapping
@Slf4j

public class PetStoreController {

@Autowired	
private PetStoreService petStoreService;
	
	
	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	 @PostMapping("/{petStoreId}/employee")
	    @ResponseStatus(code = HttpStatus.CREATED)
	    public PetStoreEmployee addEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
	        log.info("Adding employee to Pet Store with ID: {}", petStoreId);
	        return petStoreService.saveEmployee(petStoreId, employee);
	}

	 @PostMapping("/{petStoreId}/customer")
	    @ResponseStatus(code = HttpStatus.CREATED)
	    public PetStoreCustomer insertPerStoreCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer customer) {
	        log.info("Creating a customer for pet store: {}", petStoreId);
	        return petStoreService.saveCustomer(petStoreId, customer);

	    }

	 @PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	    }

	    @DeleteMapping("/{petStoreId}")
	    public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
	        log.info("Deleting pet store with id: {}", petStoreId);
	        petStoreService.deletePetStoreById(petStoreId);
	        return Map.of("Message", "Deletion of ID: " + petStoreId + " was successful.");
	    }

	    @GetMapping("/pet_store")
	    public List<PetStoreData> retrieveAllPetStores() {
	        log.info("Retrieving all pet stores.");
	        return petStoreService.retrieveAllPetStores();
	    }
	

	    @GetMapping("/pet_store/{petStoreId}")
	    public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
	        log.info("Retrieving pet store with ID: {}", petStoreId);
	        return petStoreService.retrievePetStoreById(petStoreId);
	    }
	}
