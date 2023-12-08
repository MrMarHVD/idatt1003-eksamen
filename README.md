# Portfolio project IDATT1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

STUDENT NAME = "Håvard Daleng"  
STUDENT ID = "Your ID"

## Project description

This is a train scheduling application. It allows the user to manage a set of
train departures arriving at-and departing from a single train station. 
It incorporates the ability to register new departures, add delay, change track, search
and cancel among other functions.

## Project structure

All source files and tests are in packages under edu.ntnu.stud. The main source files
are in the file path src/main/java/edu/ntnu/stud. 
Main class (TrainDispatchApp): edu.ntnu.stud.app.
Backend (TrainDeparture and DepartureOverview): edu.ntnu.stud.backend.
GUI (DepartureOverviewGui): edu.ntnu.stud.gui.

Tests are located in src/test/java/edu/ntnu/stud in the corresponding packages.

## Link to repository

https://github.com/MrMarHVD/idatt1003-eksamen

## How to run the project

The main class of the program is TrainDispatchApp.java and is found under
src/main/java/edu/ntnu/stud/app.

When running the program, it is expected that a separate window will open. This is the Java
Swing interface. It should show a table already populated by ten train departures initialized
prior to UI generation. A number of text fields and buttons should be present, all of which
are labelled according to their purpose. 

To make changes to individual departures it is not necessary to search. Simply select the departure
in question and any changes will apply.

Upon adding departures or changing the attributes of existing departures, the table should 
immediately update on its own, showing only those departures departing after current time
(top left of the interface). In the case of exceptions, the user is notified and informed
of their cause in a second pop-up window. It is not necessary to restart the application
upon exceptions.

If desired, the application can be run from the terminal using the following command:
'java -jar target/TrainDispatchSystem-1.0-SNAPSHOT.jar' when in the project directory.

## How to run the tests

To run tests manually, go to the project directory 'IDATT1003-Mappevurdering' and
run the commands 'mvn compile' followed by 'mvn test'. Note that this requires Maven be installed.


