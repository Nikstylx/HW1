package edu.softwareeng.sample;

import java.util.Iterator;

public interface DataStore {
    Iterator<Integer> read(InputConfig inputConfig);
    WriteResult appendSingleResult(OutputConfig outputConfig, String result, char delimiter);
}
