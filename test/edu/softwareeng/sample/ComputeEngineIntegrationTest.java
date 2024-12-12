@Test
public void testComputeWorkflow() {
    ComputeEngine engine = new ComputeEngineImpl();
    TestDataStore testDs = new TestDataStore();
    ComputationCoordinator coord = new CoordinatorImpl(testDs, engine);

    InMemoryInputConfig input = new InMemoryInputConfig(1, 10, 25);
    InMemoryOutputConfig output = new InMemoryOutputConfig();

    ComputeRequest mockRequest = Mockito.mock(ComputeRequest.class);
    when(mockRequest.getInputConfig()).thenReturn(input);
    when(mockRequest.getOutputConfig()).thenReturn(output);

    ComputeResult result = coord.compute(mockRequest);

    // Manually checking the result using if statements
    if (result != ComputeResult.SUCCESS) {
        throw new AssertionError("Expected result to be SUCCESS, but was " + result);
    }

    List<String> expected = new ArrayList<>();
    expected.add("1");
    expected.add("10");
    expected.add("25");

    // Manually checking the output
    if (output.getOutputMutable().size() != expected.size()) {
        throw new AssertionError("Expected output size to be " + expected.size() + ", but was " + output.getOutputMutable().size());
    }

    for (int i = 0; i < expected.size(); i++) {
        if (!expected.get(i).equals(output.getOutputMutable().get(i))) {
            throw new AssertionError("Expected output at index " + i + " to be " + expected.get(i) + ", but was " + output.getOutputMutable().get(i));
        }
    }
}
