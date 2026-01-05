# Multithreaded Parking System Simulation

## Overview
A Java-based concurrent parking lot management system that simulates real-world parking scenarios using multithreading and semaphore synchronization. The system manages multiple entry gates, handles concurrent car arrivals, and ensures thread-safe parking spot allocation.

## Technical Highlights
- **Concurrency Control**: Custom semaphore implementation for resource management
- **Thread Synchronization**: Safe handling of shared resources across multiple threads
- **Real-time Simulation**: Time-based car arrival and departure scheduling
- **Multi-gate Support**: Concurrent processing of vehicles from multiple entry points

## System Architecture

### Core Components

#### 1. `semaPhore.java`
Custom semaphore implementation for parking spot allocation:
- **P() operation**: Acquires a parking spot (wait if unavailable)
- **V() operation**: Releases a parking spot (notify waiting threads)
- Thread-safe counter management using synchronized methods

#### 2. `Car.java`
Represents individual vehicles as threads:
- Each car runs as an independent thread
- Simulates arrival time, parking duration, and departure
- Tracks statistics per gate using synchronized counters
- Handles parking spot acquisition and release

#### 3. `ParkingLots.java`
Main simulation coordinator:
- Reads car data from input file
- Initializes parking capacity
- Manages thread lifecycle (creation and joining)
- Generates final statistics report

#### 4. `Main.java`
Entry point that configures and launches the simulation

## Key Features

### Thread Safety
- Synchronized access to shared semaphore counter
- Lock-based protection for gate statistics
- Wait/notify mechanism for blocked threads

### Real-time Simulation
- Time-based delays (1 second = 1 time unit)
- Concurrent arrival handling from multiple gates
- Dynamic parking status updates

### Statistics Tracking
- Total cars served across all gates
- Per-gate service counters
- Real-time parking occupancy

## Input Format
```
Gate [number], Car [id], Arrive [time], Parks [duration]
```

Example:
```
Gate 1, Car 0, Arrive 0, Parks 3
Gate 2, Car 5, Arrive 3, Parks 4
Gate 3, Car 10, Arrive 2, Parks 4
```

## Output Example
```
Car 0 from Gate 1 arrived at time 0
Car 0 from Gate 1 parked. (Parking Status: 1 spots occupied)
Car 2 from Gate 1 arrived at time 2
Car 10 from Gate 3 arrived at time 2
...
Car 0 from Gate 1 left after 3 units of time. (Parking Status: 3 spots occupied)
...
Total Cars Served: 15
Current Cars in Parking: 0
Gate 1 served 5 cars.
Gate 2 served 5 cars.
Gate 3 served 5 cars.
```

## Technical Specifications

### Requirements
- **Language**: Java (JDK 8 or higher)
- **IDE**: IntelliJ IDEA (or any Java IDE)
- **Paradigm**: Object-Oriented Programming with Concurrency

### Compilation & Execution
```bash
# Compile
javac src/*.java

# Run (update file path as needed)
java Main
```

## Design Patterns & Concepts

### Concurrency Patterns
1. **Producer-Consumer**: Cars (producers) requesting parking spots (resources)
2. **Resource Pool**: Limited parking spots managed by semaphore
3. **Thread Lifecycle Management**: Proper thread creation, execution, and joining

### Synchronization Mechanisms
- **Intrinsic Locks**: `synchronized` keyword for method-level locking
- **Wait/Notify**: Inter-thread communication for blocking/unblocking
- **Atomic Operations**: Thread-safe counter increments

## Learning Outcomes
- Implementation of classical synchronization primitives (semaphores)
- Handling race conditions and deadlock prevention
- Thread lifecycle management in Java
- File I/O and parsing in concurrent environments
- Real-world application of operating system concepts

## Potential Enhancements
- Priority queue for VIP cars
- Multiple parking zones with different capacities
- GUI visualization of parking status
- Logging system for detailed analytics
- Deadlock detection and prevention mechanisms
- Dynamic parking capacity adjustment

## Known Limitations
- `totalCarsServed` counter in `Car.java` is never incremented (should be incremented after parking)
- Fixed file path in `Main.java` (should be parameterized)
- Semaphore implementation could use `java.util.concurrent.Semaphore` for production

## Author
Developed as part of Operating Systems coursework, demonstrating practical application of concurrent programming concepts.

## License
Academic project - Free to use for educational purposes.
