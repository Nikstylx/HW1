Prime Number Finder

Prime Number Finder is a simple Java project that allows users to find all prime numbers within a given input range. Prime numbers are integers greater than 1 divisible only by 1 and themselves. This program efficiently identifies primes by iterating over a user-specified range and checking each number's primality.

![Image of System diagram](https://github.com/Nikstylx/HW1/blob/main/Schematic%20for%20API.jpg)

Multi-threading Implementation
To enhance performance, the CoordinatorImpl executes computation requests using a multi-threaded approach. An upper limit of 8 threads is used for concurrent execution, which allows multiple computations to run simultaneously, improving throughput and responsiveness.

Justification for Thread Count
- Scalability: The number of threads is aligned with the capabilities of modern multi-core processors, optimizing performance without overloading system resources.
- Balance: This number strikes a balance between responsiveness and resource utilization, ensuring efficient processing of tasks.

This implementation allows for improved performance when handling multiple computation requests simultaneously.

CoordinatorImplV1 took 5.555ms while v2 was reduced to 4.123ms
https://github.com/Nikstylx/HW1/blob/assign-8/src/Benchmark/Benchmarktest.java
The initial implementation of the CoordinatorImpl class was inefficient because it used a fixed thread pool with a limited number of threads and blocked task execution sequentially. This approach caused delays and underutilized system resources when processing a large number of tasks concurrently.

