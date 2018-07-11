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
package commands;

/**
 * The Man class implements the ability to display the man pages for any
 * available command
 *
 */
public class Man {

  /**
   * This method is designed to print out to the shell the desired man pages for
   * a certain command
   * 
   * @param command Is the name of the command to print the man pages for
   */
  public String printMan(String command) {
    // Create a header and footer String
    String header = "\n\t\t -- CSCB07 JShell Man Pages -- \t\t\n\n",
        footer = "\n\n\t\t-- Created on June 29, 2018 --\t\t\n", result = "";

    // If statements to determine what man to print
    // Man pages for the ls command
    if (command.equals("ls")) {
      result = (header + this.getLsMan() + footer);

      // Man pages for the cat command
    } else if (command.equals("cat")) {
      result = (header + this.getCatMan() + footer);

      // Man pages for the change directory command
    } else if (command.equals("cd")) {
      result = (header + this.getCdMan() + footer);

      // Man pages for the find command
    } else if (command.equals("find")) {
      result = (header + this.getFindMan() + footer);

      // Man pages for the history command
    } else if (command.equals("history")) {
      result = (header + this.getHistoryMan() + footer);

      // Man pages for the make directory command
    } else if (command.equals("mkdir")) {
      result = (header + this.getMkdirMan() + footer);

      // Man pages for the path of working directory command
    } else if (command.equals("pwd")) {
      result = (header + this.getPwdMan() + footer);

      // Man pages for the pushd command
    } else if (command.equals("pushd")) {
      result = (header + this.getPushdMan() + footer);

      // Man pages for the echo command
    } else if (command.equals("echo")) {
      result = (header + this.getEchoMan() + footer);

      // Man pages for the popd command
    } else if (command.equals("popd")) {
      result = (header + this.getPopdMan() + footer);

      // Man pages for the tree command
    } else if (command.equals("tree")) {
      result = (header + this.getTreeMan() + footer);

    }
    return result;
  }

  /**
   * This method is designed to return the man pages for the tree command
   * 
   * @return A string representing the man page for Tree
   */
  private String getTreeMan() {
    return "NAME\n\ttree -- display entire file system\n\nSYNOPSIS"
        + "\n\ttree\n\nDESCRIPTION\n\tThe tree utility is designed "
        + "to output to the shell the entire file system. Will "
        + "display all directories and files\n\tstarting at the "
        + "root directory.\n\nEXAMPLES\n\tThe following will "
        + "print out the entire file system starting from the "
        + "root directory.\n\n\t\ttree\n";
  }

  /**
   * This method is designed to return the man pages for the popd command
   * 
   * @return A string representing the man page for popd
   */
  private String getPopdMan() {
    return "NAME\n\tpopd -- change current directory to the top"
        + " directory on stack\n\nSYNOPSIS\n\tpopd\n\nDESCRIPTION"
        + "\n\tThe popd utility is designed to change the current"
        + " working directory with the last directory\n\twhich was "
        + "pushed onto the directory stack.\n\nEXAMPLES\n\tThe "
        + "follwing will change the current working directory to "
        + "the directory which is on top\n\tof the stack (The one"
        + " that was pushed onto it).\n\n\t\tpopd\n";
  }

  /**
   * This method is designed to return the man pages for the echo command
   * 
   * @return A string representing the man page for Echo
   */
  private String getEchoMan() {
    return "NAME\n\techo -- write arguments to standard output\n\n"
        + "SYNOPSIS\n\techo STRING [>|>>] [file]\n\nDESCRIPTION\n\t"
        + "The echo utility is designed to write out any specified "
        + "operands that are inputted to standard output.\n\n\t"
        + "Options:\n\t>  -  Writes the given operands to the given"
        + " file.\n\n\t>>  -  Appends the given operands to the given"
        + " file.\n\n\t[file]  -  Operations paramater which must be"
        + " passed in when using '>' or '>>'.\n\nEXAMPLES\n\tThe"
        + " following will print out to the shell the given string."
        + "\n\n\t\techo \"some_random_string\"\n\n\tThe following"
        + " will take the given string and put it into the given "
        + "file.\n\tIf the file already exists then whatever is in"
        + " the file will be removed and the string will be added."
        + " \n\tIf the file doesn't exist it will be "
        + "created.\n\n\t\techo \"some_random_string\" > somefile"
        + ".txt\n\n\tThe following will take the given string and "
        + "append it to the desired file.\n\tIf the file doesn't "
        + "already exist it will be created.\n\n\t\techo \"some_"
        + "random_string\" >> somefile.txt\n";
  }

  /**
   * This method is designed to return the man pages for the pushd command
   * 
   * @return A string representing the man page for pushd
   */
  private String getPushdMan() {
    return "NAME\n\tpushd -- push the current directory to a stack and"
        + " change current directory to the given directory\n\n"
        + "SYNOPSIS\n\tpushd DIR\n\nDESCRIPTION\n\tThe pushd utility"
        + " is designed to push the current working directory onto a"
        + " stack and then switch\n\tthe current working directory to"
        + " the given directory.\n\nEXAMPLES\n\tThe following will"
        + " push the current working directory onto a stack and "
        + "change the current working directory to the given one."
        + "\n\n\t\tpushd /Desktop/newDirectory\n";
  }

  /**
   * This method is designed to return the man pages for the pwd command
   * 
   * @return A string representing the man page for pwd
   */
  private String getPwdMan() {
    return "NAME\n\tpwd -- return the working directory\n\nSYNOPSIS"
        + "\n\tpwd\n\nDESCRIPTION\n\tThe pwd utility is designed to"
        + " print out to the shell the absolute pathway of the "
        + "current\n\tworking directory that the shell is in.\n\n"
        + "EXAMPLES\n\tThe following will display the absolute "
        + "pathway of the current working directory.\n\n\t\tpwd\n";
  }

  /**
   * This method is designed to return the man pages for the mkdir command
   * 
   * @return A string representing the man page for mkdir
   */
  private String getMkdirMan() {
    return "NAME\n\tmkdir -- make directories\n\nSYNOPSIS\n\tmkdir "
        + "directory_name ... \n\nDESCRIPTION\n\tThe mkdir utility "
        + "is designed to create directorie(s) with the given "
        + "directory name(s).\n\nEXAMPLES\n\tThe following will "
        + "create a directory with the name 'newDirectory' at the"
        + " desired pathway.\n\n\t\t mkdir /Users/newDirectory"
        + "\n\n\tThe following will create the directory 'A' and"
        + " the directory 'B' at their desired pathways."
        + "\n\n\t\t mkdir /Desktop/A /Users/B\n\n\tThe following will"
        + " create the directory 'C' in the current working directory."
        + "\n\n\t\t mkdir C\n";
  }

  /**
   * This method is designed to return the man pages for the history command
   * 
   * @return A string representing the man page for history
   */
  private String getHistoryMan() {
    return "NAME\n\thistory -- display command history\n\nSYNOPSIS\n"
        + "\thistory [n]\n\nDESCRIPTION\n\tThe history utility is"
        + " designed to output to the shell all previous commands"
        + " which have been used by the user.\n\n\tOptions:\n\tn  -"
        + "  Display the last 'n' number of commands. This is "
        + "inclusive, as such this command will also be outputted "
        + "and counted to 'n'.\n\nEXAMPLES\n\tThe following will "
        + "list the entire command history.\n\n\t\thistory\n\n\t"
        + "The following will list out the last 7 user commands"
        + " including this one.\n\n\t\thistory 7\n";
  }

  /**
   * This method is designed to return the man pages for the find command
   * 
   * @return A string representing the man page for find
   */
  private String getFindMan() {
    return "NAME\n\tfind -- find the given file in the given "
        + "directorie(s)\n\nSYNOPSIS\n\tfind path ... -type [d|f]"
        + " -name file_name\n\nDESCRIPTION\n\tThe find utility is"
        + " designed to find the given file or directory in the given"
        + " directorie(s).\n\n\tOptions:\n\tf  -  Specifies that the"
        + " utility will search for a file.\n\td  -  Specifies that"
        + " the utillity will search for a directory.\n\nEXAMPLES"
        + "\n\tThe following will search the given directory for a "
        + "file with the name of file_name.xtn.\n\n\t\tfind /Desktop"
        + "/RandomFolder -type f -name \"file_name.xtn\"\n\n\tThe"
        + " following will search the two given directories for a"
        + " directory named directory_name.\n\n\t\tfind /Desktop /"
        + "Desktop/Music/GNR -type d -name \"directory_name\"\n";
  }

  /**
   * This method is designed to return the man pages for the cd command
   * 
   * @return A string representing the man page for cd
   */
  private String getCdMan() {
    return "NAME\n\tcd -- change the working directory\n\nSYNOPSIS"
        + "\n\tcd DIR\n\nDESCRIPTION\n\tThe cd utility is designed"
        + " to change the current working directory of the shell to"
        + " the desired directory.\n\tThe given directory may be"
        + " given as a relative path to the current working directory"
        + " or an absolute path.\n\n\tOptions:\n\t[ .. ]  -  May be"
        + " used to go to the current directories parent.\n\n"
        + "EXAMPLES\n\tThe following will change the current working"
        + " directory to the parent of the current working"
        + " directory.\n\n\t\tcd ..\n\n\tThe following will change "
        + "the current working directory to the directory specified"
        + " by the given pathway.\n\n\t\tcd /Desktop/Wowza/k\n";
  }

  /**
   * This method is designed to return the man pages for the cat command
   * 
   * @return A string representing the man page for cat
   */
  private String getCatMan() {
    return "NAME\n\tcat -- display contents of file\n\nSYNOPSIS\n\tcat"
        + " file_name\n\nDESCRIPTION\n\tThe cat utility is designed"
        + " to print out to the shell the contents of the given"
        + " file. \n\tGiven multiple paths, the output of file contents is"
        + " separated using three newline characters. \n\nEXAMPLES\n\tThe"
        + " following will display "
        + "the contents of RiceBoy.txt on the shell.\n\n\t\t"
        + "cat Riceboy.txt\n";
  }

  /**
   * This method is designed to return the man pages for the ls command
   * 
   * @return A string representing the man page for ls
   */
  private String getLsMan() {
    return "NAME\n\tls -- list the contents of the current directory"
        + "\n\nSYNOPSIS\n\tls [PATH ...]\n\nDESCRIPTION\n\tThe ls"
        + " utility is designed to list out all the files and "
        + "directories which are in the directory.\n\tIf no specified"
        + " path is given then ls will list the contents of the "
        + "current working directory.\n\n\tOptions:\n\t[PATH ...]  -"
        + "  Optional paramter to list the contents of a specified "
        + "directory given a pathway.\n\nEXAMPLES\n\tThe following"
        + " will list the contents of the current working directory."
        + "\n\n\t\tls\n\n\tThe following will list all the contents "
        + "of the specified directory.\n\n\t\t ls /Desktop/Pics\n";
  }

}
