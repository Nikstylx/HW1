package data_api;

public interface Data {
    ReadRepsonse readData(ReadRequest request);
    WriteResponse writeData(WriteRequest request);
}
