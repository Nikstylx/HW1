package edu.softwareeng.sample;

public class CoordinatorImpl implements ComputationCoordinator {
    
    private final DataStore ds;
    private final ComputeEngine ce;

    public CoordinatorImpl(DataStore ds, ComputeEngine ce) {
        // Validate parameters
        if (ds == null) {
            throw new IllegalArgumentException("DataStore cannot be null");
        }
        if (ce == null) {
            throw new IllegalArgumentException("ComputeEngine cannot be null");
        }

        this.ds = ds;
        this.ce = ce;
    }

    @Override
    public ComputeResult compute(ComputeRequest request) {
        // Validate request parameter
        if (request == null) {
            throw new IllegalArgumentException("ComputeRequest cannot be null");
        }
        
        // Validate input and output configurations
        if (request.getInputConfig() == null) {
            throw new IllegalArgumentException("InputConfig cannot be null");
        }
        if (request.getOutputConfig() == null) {
            throw new IllegalArgumentException("OutputConfig cannot be null");
        }

        // No validation needed for delimiter as it is a char type
  
