Assumptions Made:
1. Random Number Generation: I assumed the "random numbers" generated per tic to check against `landingRate` and `takeOffRate` are standard uniform random decimals between 0.0 and 1.0 (using `Math.random()`).
2. Remaining Runway Time: If a plane takes 7 minutes to land, and the clock advances in 5-minute tics, the runway remains occupied for the next tic and becomes free in the tic after that.
3. List Implementation: The prompt mentions both the `QueueInterface` and `List` interfaces. To ensure all requirements are met, `LinkedQueue.java` implements BOTH interfaces using the doubly-linked list logic.
