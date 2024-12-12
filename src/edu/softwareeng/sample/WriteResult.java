package edu.softwareeng.sample;

public class WriteResult {
    private WriteResultStatus status;

    public WriteResult(WriteResultStatus status) {
        this.status = status;
    }

    public WriteResultStatus getStatus() {
        return status;
    }
}
