package org.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

// Several test class execution
@Suite.SuiteClasses({
        TestAssertions.class,
        JUnitAnnotation.class,
        TestJunit.class
})

public class TestSuite {
}
