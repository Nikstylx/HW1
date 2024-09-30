package src;

public class DataStorePrototype {

	public void prototype(DataStore apiToCall) {
		// For now, use an anonymous inner class - other approaches might be to set this to null, use
		// a mock object, of make InputConfig a class rather than an interface. All of those accomplish the same goal:
		// the client is going to get input information from somewhere, it could be a List<Integer>, a single int, a csv file with integers,
		// a database table with integers, etc - that's going to be an implementation detail
		//
		// You'll notice this is the same interface that ComputeRequest uses - this is a design decision that means that all of the logic
		// for parsing the user's information will have to live in the DataStore implementation component. You might choose to design the system
		// differently, in which case this might be a different type of input config. There are pros and cons to each approach: a fail-fast design that
		// has a lot of input validation logic might put that in the ComputationCoordinator, and require that the DataStore only get validated input, while
		// a design that expects to have many possible DataStore implementations that get mix-and-matched with one ComputationCoordinator component might
		// prefer to force all the input-parsing logic to live in the DataStore
		InputConfig inputConfig = new InputConfig() {

		};
		
		// An example of just using null to indicate 'we haven't decided yet, that's for the implementation'
		OutputConfig outputConfig = null;

		// Here, we know the type of the data based on the system specification: the user input must be convertable to a stream of
		// integers. We haven't covered streaming in a lot of detail yet, so if you opted for a more specific type (a single int, an int[], or a 
		// List<Integer>, or ditto with longs), that's completely fine for now. 
		Iterable<Integer> loadedData = apiToCall.read(inputConfig);
		
		// This prototype is a bit different from the examples we went through in class, because this part of the client workflow is 
		// <go off and do something with the loaded data>
		// For a prototype, we're not going to worry about what the actual real logic would be for a component calling this api, so we're going to
		// do the simplest possible thing that will still capture all the behavior from the system diagram
		for (int i : loadedData) {
			String result = "" + i;
			
			// we know we're going to write results of "something" to the output. Here, there's a pretty significant design decision that the output
			// will be represented as a String - this is saying that the logic for how to format the results will have to live *somewhere else*, and 
			// not in the DataStore. Other decisions about this are also valid!
			WriteResult writeResult = apiToCall.appendSingleResult(outputConfig, result);
			
			// For variety of solutions (not actually recommended within a project, consistency is valuable), this is an example of a slightly simpler
			// enum return value. Notice how the prototype code here is more complex than the result status checking in the ComputationCoordinatorPrototype!
			if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
				System.out.println("Oh no.");
			}
		}
	}
}
