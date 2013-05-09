import java.util.*;

public class PetStore extends AbstractCollection<Pet> {
	private Pet[] pets;

	public PetStore(){
		 pets = new Pet[10];
	}
	
	public int size() {
		return pets.length;
	}

	public Iterator<Pet> iterator() {
		return new Iterator<Pet>() {
			private int index = 0;

			public boolean hasNext() {
				return index < pets.length;
			}

			public Pet next() {
				return pets[index++];
			}

			public void remove() { // Not implemented
				System.err.println("not implemented");
			}
		};
	}
}
