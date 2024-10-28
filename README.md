Prime Number Finder

Prime Number Finder is a simple Java project that allows users to find all prime numbers within a given input range. Prime numbers are integers greater than 1 divisible only by 1 and themselves. This program efficiently identifies primes by iterating over a user-specified range and checking each number's primality.

![Image of System diagram](https://github.com/Nikstylx/HW1/blob/main/Schematic%20for%20API.jpg)

Multi-threading Implementation
To enhance performance, the CoordinatorImpl executes computation requests using a multi-threaded approach. An upper limit of 8 threads is used for concurrent execution, which allows multiple computations to run simultaneously, improving throughput and responsiveness.

Justification for Thread Count
- Scalability: The number of threads is aligned with the capabilities of modern multi-core processors, optimizing performance without overloading system resources.
- Balance: This number strikes a balance between responsiveness and resource utilization, ensuring efficient processing of tasks.

This implementation allows for improved performance when handling multiple computation requests simultaneously.

