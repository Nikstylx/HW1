package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* 
 * It's useful to keep this in the 'test' folder so it doesn't accidentally get used in the real implementation;
 * test-only code can make assumptions that aren't safe for production, so mixing the two can lead to surprising bugs that only
 * show up in a production environment
 */
public class InMemoryInputConfig implements InputConfig {

	// When possible, make things final for immutability
	private final List<Integer> inputs = new ArrayList<>();
	
	// Convenience overloading - the ... creates a variable length input. Check out ComputeEngineIntegrationTest for how to use this/why it's nice
	public InMemoryInputConfig(int... inputs) {
		for (int i : inputs) {
			this.inputs.add(i);
		}
	}
	
	public InMemoryInputConfig(Collection<Integer> inputs) {
		this.inputs.addAll(inputs);
	}

	// Note that this means the class isn't actually immutable, because we're
	// returning the actual inputs; to be truly immutable, we would either use an
	// ImmutableList in the first place (ideal), or return a copy of the list (return new ArrayList<>(inputs))
	// but since this is test code, YOLO
	public List<Integer> getInputs() {
		return inputs;
	}
}
