package test;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOutputConfig implements OutputConfig {
	
	// Still make things final; we know this will end up being mutable data,
	// but we can at least contain the mutability
	private final List<String> output = new ArrayList<>();
	
	// No explicit constructor needed
	
	// When doing something sketchy (deliberately allowing another class to mutate internal state),
	// it's good to include that in variable/method names as a heads-up to anyone reading the code later
	public List<String> getOutputMutable() {
		return output;
	}

}
