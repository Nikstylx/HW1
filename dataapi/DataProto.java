package dataapi;

public class DataProto {
    public void prototype(Data data) {
        //read data
    	ReadResponse readResponse = data.readData(new ReadRequest());

        //write data
        WriteResponse writeResponse = data.writeData(new WriteRequest());
    }

}