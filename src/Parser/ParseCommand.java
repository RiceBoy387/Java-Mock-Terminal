// **********************************************************
// Assignment2:
// Student1: Rahul Ramani
// UTORID user_name: ramanira
// UT Student #: 1004110758
// Author: Rahul Ramani
//
// Student2: Justin Lyn
// UTORID user_name: lynjust2
// UT Student #: 1004178301
// Author: Justin Lyn
//
// Student3: Ryan de Sao Jose
// UTORID user_name: desaojo3
// UT Student #: 1004401961
// Author: Ryan de Sao Jose
//
// Student4: Prashyen Kanagarajah
// UTORID user_name: kanaga60
// UT Student #: 1004107637
// Author: Prashyen Kanagarajah
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package parser;

import java.util.ArrayList;
import commands.Cat;
import commands.ChangeDirectory;
import commands.Echo;
import commands.History;
import commands.Ls;
import commands.MakeDirectory;
import commands.Man;
import commands.PushAndPopd;
import commands.Pwd;
import commands.Tree;
import commands.Find;
import commands.EchoAppend;
import commands.EchoCreate;
import file.*;

/**
 * The ParseCommand class implements the ability to parse and error check the
 * commands being passed in and execute the required classes to achieve the
 * intended command action
 *
 */
public class ParseCommand {

  // Create the objects that will be used for the commands
  /**
   * Object instantiation to use make directories functionality
   */
  MakeDirectory mkdir = new MakeDirectory();
  /**
   * Object instantiation to use list content functionality
   */
  Ls listStuff = new Ls();
  /**
   * Object instantiation to use print string functionality
   */
  Echo echoString = new Echo();
  /**
   * Object instantiation to use echo append functionality
   */
  EchoAppend append = new EchoAppend();
  /**
   * Object instantiation to use echo create functionality
   */
  EchoCreate write = new EchoCreate();
  /**
   * Object instantiation to use change directory functionality
   */
  ChangeDirectory newDir = new ChangeDirectory();
  /**
   * Object instantiation to use history functionality
   */
  History cmd_history = new History();
  /**
   * Object instantiation to use cat functionality
   */
  Cat show_contents = new Cat();
  /**
   * Object instantiation to use the tree functionality
   */
  Tree tree = new Tree();
  /**
   * Object instantiation to use the find functionality
   */
  Find find = new Find();
  /**
   * Object instantiation to use the man functionality
   */
  Man manPages = new Man();
  /**
   * Object instantiation to use the pushd and popd functionality
   */
  PushAndPopd pushAndPop = new PushAndPopd();

  /**
   * This method is designed to parse the user command. The method will error
   * check each command and execute the required classes to perform the required
   * tasks.
   * 
   * @param command Is a string representing the intended command
   * @param fs Is the object which contains the root and working directory
   * @param work_dir Is the current path of the working directory
   */
  public void parseTheCommand(String command, FileSystem fs, Pwd work_dir) {
    // Store the command in an array and trim extra spaces
    String commands[] = command.trim().split("\\s+");
    // push entered command in history
    cmd_history.pushCom(commands);
    // If statements to determine what command has been passed and if valid
    // LS command (Error check and make sure they are only inputting no
    // directory or one directory)
    if (commands[0].equals("ls")) {
      String result_str;
      // Case when we are doing ls on the working directory
      if (commands.length == 1) {
        result_str = listStuff.LsWorkingDir(fs.getWorkingDir());
        // When we are given a specific path or paths
      } else if (commands.length > 1) {
        // Create an array list to store the desired paths
        String[] desiredPaths = new String[commands.length - 1];
        // Run a loop to get all the paths that they want to show the contents
        // of
        for (int i = 1; i < commands.length; i++) {
          desiredPaths[i - 1] = commands[i];
        }
        result_str = listStuff.ls(fs.getRoot(), fs.getWorkingDir(),
            desiredPaths, work_dir);
        // Case when they don't give any commands and just type ls
      } else {
        result_str = "Invalid command, please try again";
      }
      // print the result string of files and directories
      System.out.print(result_str);
    }

    // PWD command (Error check make sure only 'pwd' was inputed
    else if (commands[0].equals("pwd") && commands.length == 1) {
      System.out.println(work_dir.pwd());

    }

    // MKDIR command
    else if (commands[0].equals("mkdir") && commands.length > 1) {
      // loop call mkdir command to create the directory
      mkdir.mkdir(commands, fs);
    }

    // CD command (Error check make sure they give us a directory to change
    // into)
    else if (commands[0].equals("cd") && commands.length == 2) {
      // Call change directory on the object
      fs.changeWorkingDir(newDir.ChangeDir(commands[1], fs.getWorkingDir(),
          fs.getRoot(), work_dir));
    }

    // CAT command (Error check make sure they give us a file to print)
    else if (commands[0].equals("cat")) {
      String result;
      // Make sure they are giving us at least one file to print
      if (commands.length > 1) {
        // Create an array list to store the desired paths
        String[] desiredPaths = new String[commands.length - 1];
        // Run a loop to get all the paths that they want to show the contents
        // of
        for (int i = 1; i < commands.length; i++) {
          desiredPaths[i - 1] = commands[i];
        }
        // get the contents of the files
        result = show_contents.cat(desiredPaths, fs.getWorkingDir(), work_dir);
        // Invalid case when they only input cat
      } else {
        result = "Invalid command, please try again";
      }
      // print the result
      System.out.print(result);
      // HISTORY command
      // Check to see if they are inputting just history or history with a
      // number
      // Make sure if they pass in more than just 'history' that its a number
      // being passed
    } else if (commands[0].equals("history") && (commands.length == 1
        || (commands.length == 2 && commands[1].matches("-?\\d+(\\.\\d+)?")))) {
      // if complete history needs to be displayed
      if (commands.length == 1) {
        cmd_history.outputHist();
        // display only given amount of recent commands
      } else {
        // get the integer amount to display
        int amount = Integer.parseInt(commands[1]);
        cmd_history.outputNumHist(amount);
      }
    }

    // MAN command (Error check make sure they give us a command)
    else if (commands[0].equals("man") && commands.length == 2) {
      // Need to check that they are calling man on an actual command
      // Store all the valid commands in a list and then check the given command
      // against the list
      String availCmds[] = {"ls", "pwd", "mkdir", "history", "cat", "echo",
          "pushd", "popd", "find", "tree", "cd"};
      // boolean to hold whether or not the command is valid
      boolean valid = false;
      // Loop to check if inputed command is in the list
      for (int i = 0; i < availCmds.length; i++) {
        // Set valid bool if the command was found
        if (commands[1].equals(availCmds[i])) {
          valid = true;
        }
      }
      // If the command was found call the man class
      if (valid) {
        System.out.println(manPages.printMan(commands[1]));
      }
      // Case when the command doesn't exist
      else {
        System.out.println("Invalid command, please try again");
      }
    }

    // PUSHD command
    else if (commands[0].equals("pushd") && commands.length == 2) {
      // simply call the pushd command
      fs.changeWorkingDir(pushAndPop.Pushd(work_dir.pwd(), commands[1],
          fs.getWorkingDir(), fs.getRoot(), work_dir));
    }

    // POPD command
    else if (commands[0].equals("popd") && commands.length == 1) {
      fs.changeWorkingDir(
          pushAndPop.popd(fs.getWorkingDir(), fs.getRoot(), work_dir));
    }

    // ECHO command
    else if (commands[0].equals("echo") && commands.length > 1) {
      // Variables to hold the desired string, type of echo, and file
      String theString = "", type = "", file = "";
      // Loop to get the string and check what type of echo
      for (int i = 1; i < commands.length; i++) {
        // Case when they want to put the string into an output file
        if (commands[i].equals(">")) {
          type = commands[i];
          // Case when they want to append to a file
        } else if (commands[i].equals(">>")) {
          type = commands[i];
          // Concatenate the string that they want to use
        } else if (type.equals("")) {
          theString += commands[i] + " ";
        } else if (file.equals("")) {
          file = commands[i];
        }
      }
      // Error checking so it only calls the echo command if valid
      // Loop through the string and make sure there aren't any extra quotes
      // inside the given string
      boolean noXtraQuotes = true;
      for (int i = 1; i < theString.length() - 2; i++) {
        // If found another quote in the string its no longer valid
        if (theString.charAt(i) == ('"')) {
          noXtraQuotes = false;
        }
      }
      // Only execute echo commands if the string is enclosed in quotes and
      // there aren't any extra quotes within the string
      if (!theString.equals("") && theString.charAt(0) == ('"')
          && theString.charAt(theString.length() - 2) == ('"')
          && noXtraQuotes) {
        // If statements to determine which echo command to call
        if (type.equals("")) {
          echoString.printString(theString);
          // Case when overwriting file
        } else if (type.equals(">")) {
          // need to pass root as dir if an absolute path was given
          // Sending in substring of the string to remove quotes
          if (file.startsWith("/")) {
            write.run(fs.getRoot(), file.substring(1),
                theString.substring(1, theString.length() - 2));
          } else {
            write.run(fs.getWorkingDir(), file,
                theString.substring(1, theString.length() - 2));
          }
          // Case when appending to file
        } else if (type.equals(">>")) {
          // need to pass root as working dir if an absolute path was given
          if (file.startsWith("/")) {
            append.run(fs.getRoot(), file.substring(1),
                theString.substring(1, theString.length() - 2));
          } else { // need to pass the actual working dir
            append.run(fs.getWorkingDir(), file,
                theString.substring(1, theString.length() - 2));
          }
        }
      } else {
        // When the command is invalid
        System.out.println("Invalid command, please try again");
      }
    }

    // TREE command (Error check make sure they only input 'tree')
    else if (commands[0].equals("tree") && commands.length == 1) {
      System.out.print(tree.buildTree(fs.getRoot()));
    }

    // FIND command
    else if (commands[0].equals("find") && commands.length != 1) {
      // Declare strings to hold file type and its name
      String type = "", name = "";
      // Declare ints to use when checking if the command is valid
      int foundType = 0, foundName = 0;
      // Arraylist which the directories we are going to search in are stored
      ArrayList<String> directories = new ArrayList<String>();
      // Sort the input data
      for (int i = 1; i < commands.length; i++) {
        // If we find '-type' make sure the proceeding argument is a valid
        // type (either 'f' or 'd') and check that they are only passing in
        // a single parameter for the filetype
        if (commands[i].equals("-type") && i + 1 < commands.length
            && (commands[i + 1].equals("f") || commands[i + 1].equals("d"))
            && commands[i + 2].equals("-name")) {
          // Store the index where '-type' is found to store the file type later
          foundType = i;
          // If we find '-name' make sure they are only inputting one file name
        } else if (commands[i].equals("-name") && i + 1 < commands.length
            && i + 2 == commands.length) {
          // Make sure the file name they are passing is surrounded by double
          // quotes
          if (commands[i + 1].charAt(0) == ('"')
              && commands[i + 1].charAt(commands[i + 1].length() - 1) == ('"'))
            // Store the index of '-name' so we can store the name later
            foundName = i;
          // Keep adding the directories to search in while haven't found type
        } else if (foundType == 0) {
          directories.add(commands[i]);
        }
        // Only get type and name arguments if they exist
        if (foundName != 0 && foundType != 0) {
          type = commands[foundType + 1];
          name = commands[foundName + 1].substring(1,
              commands[foundName + 1].length() - 1);
        }
      }
      // Only call the necessary classes to execute find if all the
      // parameters are given and valid
      if (foundName != 0 && foundType != 0 && !directories.isEmpty()) {
        // run find file command to see if the file exists
        System.out.print(find.findFile(directories, name, type, fs));
        // Else if we aren't given all the required parameters or some
        // are not valid
      } else {
        System.out.println("Invalid command, please try again");
      }
    }

    // All the invalid cases
    else {
      System.out.println("Invalid command, please try again");
    }

  }
}
