# In-class Assignment 3

## Overview

In real-world software development, there are always adversaries that are going to 
break your code (most of the time unintentionally). For high-stakes products, such as
GMail, testing is essential because the impact of broken code could be catastrophic.
In traditional CS assignments, it is hard to replicate this environment where code is
constantly under attack and failure is not an option.

This assignment introduces a new __feature__ of this course -- __the Whammy__. The Whammy
is an automated build system that watches your source repositories on GitHub and attempts
to automatically inject subtle bugs into them. The Whammy has rules though -- it will only
inject a bug if it can do so without causing any of your tests to fail. If you test 
thoroughly, you will thwart the Whammy. If you get sloppy with your testing, the Whammy
will make your development much more challenging. 

All future assignments in this course are going to incorporate the Whammy. From now on,
every line of code you commit is going to be inspected by the Whammy and attacked. The
Whammy is going to get smarter over time, so it will be essential that you get better and
better at testing your code over time. You will also need to learn to create a development
process that makes testing a priority and ensures that untested code doesn't make its way
into your repositories.

You may think this idea is crazy. It isn't. Netflix does the equivalent type of testing
for the resiliency of their infrastructure by automatically killing and injecting bugs
into their __production__ servers: 
http://techblog.netflix.com/2012/07/chaos-monkey-released-into-wild.html
 
## Key Information

Released: 10-02-14

Due Date: 10-02-14

Close Time: 12:15

Requires Group Completion: Yes

Total Groups: 6

## Part 0 - JUnit Tests

JUnit is a framework for writing tests of your code. JUnit provides a very simple
interface for developers, you simply create a class with methods named testXYZ
and annotate those methods with @Test. Each time that you right-click on a test
class and Run As->JUnit, JUnit automatically discovers and runs each of these
methods annotated with @Test. Inside of these test methods, you define your logic
for the test and check that one or more conditions are met by adding assertXYZ
statements, as shown below:

   ```java   
	package org.magnum.cs278.testdriven;

	import static org.junit.Assert.*;
	import org.junit.Test;

	public class TestAddition {

        // A simple test method
		@Test
		public void testTwoPlusTwoEqualsFour() {
			int sum = 2 + 2;
			
			// An assertion that is being checked
			assertEquals(4, sum);
			
			// This will fail and generate an error in JUnit
			assertTrue(sum == 5);
		}

	} 
   ```
In this simple test, the "testTwoPlusTwoEqualsFour()" method will be invoked and JUnit
will check all of the assertions. You can add JUnit tests in Eclipse by creating
new classes in the project by right-clicking on a package in src/test/java, 
New->Other->Java->JUnit->JUnit Test Case. Eclipse will automatically generate a barebones
test for you. You can then add as many testXYZ methods annotated with @Test as you want.

Typically, you create a separate Test class for each class in your application. The
normal naming scheme is that the class Foo will have a corresponding FooTest class in your
src/test/... code. 

## Part 1 - Creating New Features

Each team has inherited a set of source code from one of the merged code bases from the
second in-class exercise. Your team must add 5 additional features to this code base and
get the code committed without getting a Whammy. There are a couple of rules:

1. All of your code must eventually make its way into the master branch
2. You must use the existing build system and you can't have a failing build in master
3. Your code must survive at least 15min of continual Whammy attacks - you need to let
   the instructor know when you want to start the clock
4. Each time you get a Whammy! commit (that will be the commit message), you must create a
   test that to thwart that attack from the Whammy. You must also survive at least 1 additional
   minute of Whammy attacks (e.g., if you fail at minute 14, the clock is going to be reset
   to zero and you will need to survive 16min of attacks...fail again and it is 17min,
   etc.).
5. You are free to commit bugs to other teams repos if they pass their tests (any branch
   is fair game).
   
__Good Luck!__
