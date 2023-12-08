# Portfolio project IDATA1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

STUDENT NAME = "Håvard Daleng"  
STUDENT ID = "Your ID"

## Project description

[//] # This is a train scheduling application. It allows the user to manage a set of
train departures arriving at-and departing from a single train station. 
It incorporates the ability to register new departures, add delay, change track, search
and cancel among other functions.

## Project structure

[//]: # All source files and tests are in the package edu.ntnu.stud. The main source files
are in the file path src/main/java/edu/ntnu/stud. The main class is under the
app-subdirectory, backend (TrainDeparture and DepartureOverview) are under backend,
and the GUI-class (DepartureOverviewGui) is under gui.

Tests are located in src/test/java/edu/ntnu/stud.


## Link to repository

[//]: # https://github.com/MrMarHVD/idatt1003-eksamen

## How to run the project

[//]: # The main class of the program is TrainDispatchApp.java and is found under
src/main/java/edu/ntnu/stud/app.

When running the program, it is expected that a separate window will open. This is the Java
Swing interface. It should show a table already populated by ten train departures initialized
prior to the user interface. A number of text fields and buttons should be present, all of which
are labelled according to their purpose. 

Upon adding departures or changing the attributes of existing departures, the table should 
immediately update on its own, showing only those departures departing after current time
(top left of the interface). In the case of exceptions, the user is notified and informed
of their cause in a second pop-up window. It is not necessary to restart the application
upon exceptions.

What is the input and output of the program? What is the expected behaviour of the program?)

## How to run the tests

[//]: # (TODO: Describe how to run the tests here.)

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
