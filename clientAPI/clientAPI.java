package clientapi;

public interface ClientAPI {
    DelimiterResponse getDelimiter(DelimiterRequest request);
    DestinationResponse SetDestination(DestinationRequest request);
    SourceResponse getSource(SourceRequest request);
}
