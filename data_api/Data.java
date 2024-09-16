package dataAPI;

public interface Data {
    ReadRepsonse readData(ReadRequest request);
    WriteResponse writeData(WriteRequest request);
}
