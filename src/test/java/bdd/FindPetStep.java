package bdd;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindPetStep {
	@Autowired
	PetService petService;

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	PetTypeRepository petTypeRepository;

	@Autowired
	PetRepository petRepository;

	private Owner hamid;
	private Pet pet;
	private Pet found;
	private PetType petType;

	@Before()
	public void setup() {
		hamid = new Owner();
		hamid.setFirstName("Hamid");
		hamid.setLastName("Khodadadi");
		hamid.setAddress("Tehran Razi Street");
		hamid.setCity("Tehran");
		hamid.setTelephone("09353483113");
		ownerRepository.save(hamid);

		petType = new PetType();
		petType.setName("Cat");
		petTypeRepository.save(petType);
	}

	@Given("A pet with an ID.")
	public void thereIsAPetCalled() {

		pet = new Pet();
		pet.setName("HamidPet");
		pet.setBirthDate(LocalDate.of(2017, 05, 05));
		pet.setType(petType);
		hamid.addPet(pet);
		petRepository.save(pet);
	}

	@When("Someone wants to find the bird with his ID.")
	public void searchOwnerWithId() {
		found =  petService.findPet(pet.getId());
	}

	@Then("The pet is successfully found with his ID.")
	public void checkOwnerBeFound() {
		assertEquals(pet.getId(), found.getId());
	}
}
