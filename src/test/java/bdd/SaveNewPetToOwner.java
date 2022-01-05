package bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveNewPetToOwner {
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
	private PetType petType;


	@Given("An existing owner.")
	public void thereIsAOwner() {
		hamid = new Owner();
		hamid.setFirstName("Hamid");
		hamid.setLastName("Khodadadi");
		hamid.setAddress("Tehran Razi Street");
		hamid.setCity("Tehran");
		hamid.setTelephone("09353483113");
		ownerRepository.save(hamid);
	}

	@Given("A existing pet.")
	public void thereIsAPet() {
		petType = new PetType();
		petType.setName("Cat");
		petTypeRepository.save(petType);

		pet = new Pet();
		pet.setName("HamidPet");
		pet.setBirthDate(LocalDate.of(2017, 05, 05));
		pet.setType(petType);
		hamid.addPet(pet);
		petRepository.save(pet);
	}

	@When("The owner wants to save new pet in his list.")
	public void savePetInOwnerList() {
		petService.savePet(pet, hamid);
	}

	@Then("The pet must be successfully added to his pets.")
	public void checkPetAdded() {
		assertEquals(hamid.getId(), pet.getOwner().getId());
	}

	@Then("The pet must be successfully saved in his pets list.")
	public void checkPetSaved() {
		assertEquals(pet, hamid.getPet(pet.getName()));
	}
}
