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
// UT Student #:  1004107637
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

import file.Directory;

/**
 * This class represents the pwd command
 *
 */
public class Pwd {
  /**
   * Stores the current working directory i.e the path to the working directory
   */
  private String workingDir;

  public Pwd() {
    workingDir = "/";
  }

  /**
   * Changes the working directory according to the array <path> that contains
   * the directories that are being navigated through.
   * 
   * @param path: array of directories for new working directory
   */
  public void absDirChange(Directory[] path) {
    this.workingDir = "";
    for (int i = 0; i < path.length; i++) {
      this.workingDir = this.workingDir + "/" + path[i].getFileName();
    }
  }

  /**
   * Single change in directory
   * 
   * @param newWorking: Directory that is now the working directory
   */
  public void dirChange(Directory newWorking) {
    // if curr working dir is root, no need for separator
    if (this.workingDir.equals("/")) {
      this.workingDir = this.workingDir + newWorking.getFileName();
    } else {
      this.workingDir = this.workingDir + "/" + newWorking.getFileName();
    }
  }

  /**
   * Changes the working directory up <num> amount of times
   * 
   * @param num: the number of upward directory movements
   */
  public void upDir(int num) {
    // split the current working directory into its individual directories
    String[] splitPath = this.workingDir.split("/");
    // reset working directory
    this.workingDir = "";
    // if not reseting back to or beyond the root
    if (splitPath.length - num > 0) {
      // loop through the split path adding the directories that are still
      // required
      for (int i = 0; i < (splitPath.length - num); i++) {
        // if curr working dir is root, no need for separator
        if (this.workingDir.equals("/")) {
          this.workingDir = this.workingDir + splitPath[i];
        } else {
          this.workingDir = this.workingDir + "/" + splitPath[i];
        }
      }
    } else {
      this.workingDir = "/";
    }

  }

  /**
   * Resets working directory to the root
   */
  public void root() {
    this.workingDir = "/";
  }

  /**
   * Returns the path of the working directory
   * 
   * @return the path of the working directory
   */
  public String pwd() {
    return this.workingDir;
  }
}
