package bdd;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddNewPetToOwnerStep {
	@Autowired
	PetService petService;

	@Autowired
	OwnerRepository ownerRepository;

	private Owner hamid;
	private Pet newPet;


	@Given("An owner.")
	public void thereIsAOwner() {
		hamid = new Owner();
		hamid.setFirstName("Hamid");
		hamid.setLastName("Khodadadi");
		hamid.setAddress("Tehran Razi Street");
		hamid.setCity("Tehran");
		hamid.setTelephone("09353483113");
		ownerRepository.save(hamid);
	}

	@When("The owner adds that new pet to his list.")
	public void addPetToOwnerList() {
		newPet =  petService.newPet(hamid);
	}

	@Then("The pet must be successfully added to his pets list.")
	public void checkPetAdded() {
		assertEquals(hamid.getId(), newPet.getOwner().getId());
	}
}
