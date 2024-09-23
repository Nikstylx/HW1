package clientapi;

public interface ClientAPI {
    DelimiterResponse getDelimiter(DelimiterRequest request);
    DestinationResponse getDestination(DestinationRequest request);
    SourceResponse getSource(SourceRequest request);
    InitializingResponse getInitializing(InitializingRequest request);
}

