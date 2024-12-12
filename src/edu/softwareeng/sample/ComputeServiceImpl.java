import io.grpc.stub.StreamObserver;

class ComputeServiceImpl extends ComputeServiceGrpc.ComputeServiceImplBase {
    @Override
    public void computeTask(ComputeRequest req, StreamObserver<ComputeResult> responseObserver) {
        // Extract the parameters from the request
        String inputConfig = req.getInputConfig();
        String outputConfig = req.getOutputConfig();
        String delimiter = req.getDelimiter();

        // Call your existing CoordinatorImpl to process the computation
        ComputeResult computeResult = processComputation(inputConfig, outputConfig, delimiter);

        // Build the response and send it back
        responseObserver.onNext(computeResult);
        responseObserver.onCompleted();
    }

    private ComputeResult processComputation(String inputConfig, String outputConfig, String delimiter) {
        // Call your existing computation logic (from the CoordinatorImpl)
        // Example:
        try {
            // Assuming CoordinatorImpl already handles input/output and computation logic
            CoordinatorImpl coordinator = new CoordinatorImpl(new DataStoreImpl(), new ComputeEngineImpl());
            ComputeRequest request = new ComputeRequest(inputConfig, outputConfig, delimiter);
            return coordinator.compute(request);
        } catch (Exception e) {
            return ComputeResult.newBuilder()
                    .setStatus("FAILURE")
                    .setMessage("Error: " + e.getMessage())
                    .build();
        }
    }
}
