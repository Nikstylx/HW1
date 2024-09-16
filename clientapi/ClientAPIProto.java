package clientapi;

public class ClientAPIProto {
    public void prototype(ClientAPI client) {
    
    	DelimiterResponse delimiterResponse = client.getDelimiter(new DelimiterRequest());

    
        DestinationResponse destinationResponse = client.setDestination(new DestinationRequest());

        SourceResponse sourceResponse = client.getSource(new SourceRequest());


    }

}
