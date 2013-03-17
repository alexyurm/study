To compile any of the code in the subdirectories, go to the
subdirectory and use the following command:

javac -cp .:../junit-4.5.jar *.java

To run the code, use:

java -ea -cp .:../junit-4.5.jar org.junit.runner.JUnitCore XXTest

... where XX is appropriate for the program being run. This will
actually run junit tests, which unfortunately does not give directly
useful feedback, aside from telling you whether the tests ran
correctly.
