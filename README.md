# Folder Size Searcher
This Java tool provides information about the size of a directory. It searches a folder specified by the user, and provides the folder's size, its 5 biggest subfolders (based on the files within each subfolder itself, and not the files in its own subfolders), and its 5 biggest files.<br>

The byte sizes in this tool are in base-10 units (i.e. bytes, kilobytes, megabytes, etc.) as opposed to binary units (bytes, kibibytes, mebibytes, etc.) which some operating systems use (including Windows, even though they use the base-ten term). The sizes are also truncated (as opposed to rounded) to two decimal places.<br>

This program was written in tandem with the MP3 Folder Creator, which also features Java code dealing with file systems.<br>

# Installation
Firstly, clone this repository, 
[using these instructions.](https://docs.github.com/en/enterprise/2.13/user/articles/cloning-a-repository)
<br><br>
Secondly, you should [install JDK 14 if you don't have it already, and set the PATH variable](https://docs.oracle.com/en/java/javase/14/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A). 

# Running the program
If you have an IDE that can run Java programs, you can just open `ProjectFiles/src/FolderSize.java` in the IDE and run it.<br><br>

Otherwise, open a terminal/command-line window, and change the directory to the location of the cloned repository.
Then, enter:
```
cd ProjectFiles/src
```
If you haven't compiled this code since you cloned it or last git pulled it, you should compile the Java files before running them by entering:
```
javac DirectorySearcher.java FolderSizeListener.java FolderSize.java
```
To run the program, enter:
```
java FolderSize
```
Note: Closing the terminal/command-line window will also close the program.

# Usage
Click the `Choose folder` button to pick a folder for the program to analyze. Click on the folder you want to choose, then click `Open` or press the Enter key.<br>
Note: if you want to go inside a folder, double-click that folder. Single-clicking it and either clicking `Open` or pressing the Enter key will result in the program analyzing that selected folder.<br>
Then, wait for the program to finish analyzing the results. You can see the status of the program by looking to the right of the `Choose folder` button, and seeing if it is still `Waiting` for the program's analysis to finish, or if the program is `Done` analyzing. Once it is done, you will see the size of the selected folder, its 5 largest subfolders, and its 5 largest files.

# Credits:
This program was written by:<br>
[Devin Gopaul](https://github.com/DevinGopaul)<br>
Sources that were referred to while making this program include:<br>
[Java Documentation](https://docs.oracle.com/en/java/javase/14/docs/api/)
