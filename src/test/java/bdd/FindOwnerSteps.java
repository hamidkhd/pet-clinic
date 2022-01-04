package bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindOwnerSteps {
	@Autowired
	PetService petService;

	@Autowired
	OwnerRepository ownerRepository;

	private Owner hamid;
	private Owner found;

	@Given("A Owner with an ID.")
	public void thereIsAPetOwnerCalled() {
		hamid = new Owner();
		hamid.setFirstName("Hamid");
		hamid.setLastName("Khodadadi");
		hamid.setAddress("Tehran Razi Street");
		hamid.setCity("Tehran");
		hamid.setTelephone("09353483113");
		ownerRepository.save(hamid);
	}

	@When("Someone wants to find him with his ID.")
	public void searchOwnerWithId() {
		found =  petService.findOwner(hamid.getId());
	}

	@Then("The owner is successfully found with his ID.")
	public void checkOwnerBeFound() {
		assertEquals(hamid.getId(), found.getId());
	}
}
