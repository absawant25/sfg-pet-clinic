package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialityService specialityService;

  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialityService = specialityService;
  }

  @Override
  public void run(String... args) throws Exception {

    int count = petTypeService.findAll().size();
    if(count == 0) {
      loadData();
    }
  }


  private void loadData() {

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);


    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    Speciality radiology = new Speciality();
    radiology.setDescription("Radiology");
    Speciality savedRadiology = specialityService.save(radiology);

    Speciality surgery = new Speciality();
    surgery.setDescription("Surgery");
    Speciality savedSurgery = specialityService.save(surgery);

    Speciality dentistry = new Speciality();
    dentistry.setDescription("Dentistry");
    Speciality savedDentistry = specialityService.save(dentistry);

    Owner owner1 = new Owner();
    owner1.setFirstName("Abhishek");
    owner1.setLastName("Sawant");
    owner1.setAddress("Talegaon");
    owner1.setCity("Pune");
    owner1.setTelephone("999999999");

    Pet absPet = new Pet();
    absPet.setPetType(savedDogPetType);
    absPet.setOwner(owner1);
    absPet.setBirthDate(LocalDate.now());
    absPet.setName("Bruno");
    owner1.getPets().add(absPet);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Advika");
    owner2.setLastName("Sawant");
    owner2.setAddress("Talegaon");
    owner2.setCity("Pune");
    owner2.setTelephone("8888888888");

    Pet aduPet = new Pet();
    aduPet.setPetType(savedCatPetType);
    aduPet.setOwner(owner2);
    aduPet.setBirthDate(LocalDate.now());
    aduPet.setName("Meow");
    owner2.getPets().add(aduPet);

    ownerService.save(owner2);

    System.out.println("Loaded Owners....");

    Vet vet1 = new Vet();
    vet1.setFirstName("Ajinkya");
    vet1.setLastName("Shinde");
    vet1.getSpecialities().add(savedSurgery);
    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Arya");
    vet2.setLastName("Shinde");
    vet2.getSpecialities().add(savedDentistry);
    vetService.save(vet2);

    System.out.println("Loaded Vets....");

  }
}
