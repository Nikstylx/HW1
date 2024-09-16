package computeapi;

public class ComptAPIProto {
    public void prototype(CompAPI comp) {
        InitializingResponse readResponse = computeapi.getInitializing(new InitializingRequest());
        ComputeResponse readResponse = computeapi.getCompute(new ComputeRequest());
    
    	ReadResponse readResponse = computeapi.readData(new ReadRequest());

        //write data
        WriteResponse writeResponse = computeapi.writeData(new WriteRequest());


    }

}