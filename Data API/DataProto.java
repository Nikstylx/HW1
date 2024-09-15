package data_api;

public class DataProto {
    public void prototype(Data data) {
        //read data
        ReadRepsonse readResponse = data.readData(new ReadRequest());

        //write data
        WriteResponse writeResponse = data.writeData(new WriteRequest());
    }

}