package dataapi;

public interface Data {
    ReadResponse readData(ReadRequest request);
    WriteResponse writeData(WriteRequest request);
}