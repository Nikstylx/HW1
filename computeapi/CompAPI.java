package computeapi;

public interface CompAPI {
    InitializingResponse getInitializing(InitializingRequest request);
    ComputeResponse getCompute(ComputeRequest request);
    ReadResponse readData(ReadRequest request);
    WriteResponse writeData(WriteRequest request);
}