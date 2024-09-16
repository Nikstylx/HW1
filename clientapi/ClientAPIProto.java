package clientapi;

public class ClientAPIProto {
    public void prototype(ClientAPI client) {
    
    	DelimiterResponse DelimiterResponse = client.getDelimiter(new DelimiterRequest());

    
        DestinationResponse DestinationResponse = client.setDestination(new DestinationRequest());

        SourceResponse SourceResponse = client.getSource(new SourceRequest());


    }

}
