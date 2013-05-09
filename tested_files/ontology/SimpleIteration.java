import java.util.Iterator;

public class SimpleIteration {
	public void disposeAll() {
		PetStore pets = new PetStore();
		Iterator iterator = pets.iterator();
		while (iterator.hasNext()) {
			Pet pet = (Pet) iterator.next();
			if (pet == null) {
				continue;
			}
			
			System.out.println("pet name: " + pet.getName());
			pets.remove(pet); // VIOLATION
		}
	}
}
