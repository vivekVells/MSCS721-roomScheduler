# RoomScheduler
Simple console menu driven room scheduler framework.

### Project Working Demo (all demo files location: [link](https://github.com/vivekVells/MSCS721-roomScheduler/tree/master/src/resources/workingDemo))
- [Video Link]() - to be updated soon
- [Working Demo - PDF sheet](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/files/Room%20Scheduler%20-%20by%20Vivek%20Vellaiyappan.pdf)

### Objective
- To allow users to create rooms and schedule meeting for it
### Tech Involved 
- Java, GSON
### Features
- Add a room with max accommodation capacity
- Remove and view the created room info
- Schedule room and view the scheduled room info
- Export room info so that it can be later imported (since this does not use db. to store values we use this one. GSON is used here)
- Import room info from recently exported room information (GSON is used here.)
### Concepts I Newly Learnt & Practiced
- GSON
- Static code analysis
- Junit
### Future Code
- Enhancement tweaks
- Moving to better GUI

### Instructions to run this code in your machine:
#### In Eclipse or such IDE
- This is very simple, just open the file project pointing to this code repo's src folder
#### In console (Tested in Windows using GitBash cmd)
- Make sure JAVA setup is available in your machine (coded when java was on version 1.8.0_144)
- Open console command prompt or gitbash (I love git bash. Try this one)
- Pull this code to your machine and run it (Install git and use git bash for the followings)
  - Create a folder and do 
    - **git init**
  - Add this repo as your remote origin 
    - **git remote add origin https://github.com/vivekVells/MSCS721-roomScheduler.git**
  - Pull the code in this repo to your remote origin 
    - **git pull origin master**   
  - Type the followings (running the program in a particular path matters)
    - if you are in the created folder then, run like the following
        - Vivek-Pc@kev MINGW64 /e/Marist/Semester2/SoftwareVerificationAndMaintenance/RoomScheduler (master)
        - $ **java -jar src/resources/files/roomExportedFiles/exportedRoomScheduler.jar** 
    - Or move into all the way to roomExportedFiles folder and then try like the following
        - Vivek-Pc@kev MINGW64 /e/Marist/Semester2/SoftwareVerificationAndMaintenance/RoomScheduler/src/resources/files/roomExportedFiles (master)
        - $ **java -jar exportedRoomScheduler.jar**

### Tasks | (Program is solid. but waiting for other fellow testers to test and break my code if it has any loop holes)
**Completed objectives:**
* Add the ability to import and export the meeting, room, and schedule data to JSON a file
or files, so that one can save and restore data entered from each program execution.
* Harden and enhance the program to handle errors so that the program will not crash or
fail, no matter what the user does. “Make the program idiot proof.”
* Add conflict checking to scheduling a room.
* Add comments using Javadoc tools and guideline
* Run static tool analysis and correct stuffs
* Correct issues in repo reported [Issues in RoomScheduler](https://github.com/vivekVells/MSCS721-roomScheduler/issues)
* Documenting all the stuffs I did so far
* Resolving any issues reported 
* Report any issues of the peers

**Pending objectives:**
* Review peer code
* Junit tests

### Modification log
- All the stuffs I did that involves in the modification of code will be present in this link [Modification Log](https://github.com/vivekVells/MSCS721-roomScheduler/tree/master/src/resources/log) - click this link and view the ***Modification Log.pdf*** file to view the log

### Note
View [Parent Repo](https://github.com/gildmi/RoomScheduler) by Professor: Michael Gildein.
- Initial code of this was overly simple, intentially uncommented, and buggy which was intended to be used as an extendable framework for testing class assignments.

### Application Screenshots (wanna avoid scrolling a lot, use above pdf sheet)
Beware of the lots of scrolling to come :P



Executing Room Scheduler Program![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/1-ExecutingRoomSchedulerJarFile.png)

Room Scheduler Home Page![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/2-RoomSchedulerHomePage.png)

Room Add Home Page![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/3-RoomAddingResult.png)

List Room Home Page![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/4-ViewAddedRooms.png)

Remove Room Home Page![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/5-RemoveRoomPage.png)

Schedule Room Home Page![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/6-ScheduleRoomHomePageResult.png)

Scheduled Room Information Page - ALL ROOMS![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/7-ViewingAllRoomsSchedule.png)

Scheduled Room Information Page - Desired Room![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/8-ViewingDesiredRoomResult.png)

Export Room Home Page - After adding all the rooms![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/10-ExportFeatureResults.png)

Removing all the rooms to demonstrate the import and export feature![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/9-RemovedAllRoomsForExportAndImportFeatureDemo.png)

Import Room Home Page - imoprt all the room info using a json file![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/11-ImportFeatureResults.png)

After Import view all rooms![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/12-AfterImportViewingRooms.png)

After Import view all rooms scheduled information![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/13-AfterImportViewingAllRoomsSchedules.png)

Quitting the Program![](https://github.com/vivekVells/MSCS721-roomScheduler/blob/master/src/resources/workingDemo/Images/V1Feb17/14-QuittingProgram.png)

Wow! Appreciate you for scrolling all the way down.

Highly appreciate if you could test this appln and log the issues or enhancements.

Have a great day!
