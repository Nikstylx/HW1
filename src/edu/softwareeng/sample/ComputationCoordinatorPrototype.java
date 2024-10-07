package edu.softwareeng.sample;

public class ComputationCoordinatorPrototype {

	public void prototype(ComputationCoordinator apiToCall) {
		// For now, use an anonymous inner class - other approaches might be to set this to null, use
		// a mock object, of make InputConfig a class rather than an interface. All of those accomplish the same goal:
		// the client is going to get input information from somewhere, it could be a List<Integer>, a single int, a csv file with integers,
		// a database table with integers, etc - that's going to be an implementation detail
		InputConfig inputConfig = new InputConfig() {
			
		};
		
		// An example of just using null to indicate 'we haven't decided yet, that's for the implementation'
		OutputConfig outputConfig = null;
		
		// This is also a case where we could make ComputeRequest an interface and just use an anonymous inner class - that lets us punt on
		// how the default delimiter will be specified. On the other hand, an overloaded constructor isn't that terrible. If you've spotted that this
		// would be an excellent place to apply the Builder pattern, you're right! This sample code is written without the design patterns we've
		// started to cover, but feel free to go back to refactor your own code if you notice that it can be improved with any of the topics we cover throughout the
		// semester. IRL, development teams often allocate 10-25% of their time to this sort of refactoring - it's often called "paying down technical debt"
		ComputeRequest request = new ComputeRequest(inputConfig, outputConfig, ',');
		
		// Now we've assembled all the pieces that we know, based on the system specification description, that we're going to need - an input, output, and delimiter
		// All that's left is to actually run the computation, and check that it worked:
		ComputeResult result = apiToCall.compute(request);
		
		// Here's an example of using an enum to wrap a boolean success value. This gives us the option of providing more detailed
		// failure information later, even if we don't know what it should be now. Another advantage of having API design be its own deliberate step
		// is that it sets aside time to really polish the APIs - everything else in the project is going to depend on this code, so it's worth
		// taking the extra few minutes to set up an enum, and then give it a wrapped boolean - when you actually start building the implementation,
		// you'll appreciate your Past Self for making life easy for Future Self
		if (result.getStatus().isSuccess()) {
			System.out.println("Yay!");
		}
		
		// Now we've gone through the whole workflow from the client's perspective, so we're done with this API!
	}
}
