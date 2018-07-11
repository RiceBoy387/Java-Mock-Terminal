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

/**
 * The echo class implements the ability of a given string that has been
 * inputed to be printed out on the shell
 *
 */
public class Echo {

  /**
   * Designed to output to the terminal the string when using the echo command
   * and no output file is given
   * 
   * @param theString: which is the string that will be printed
   */
  public void printString(String theString) {
    // Printout to the shell the given string
    System.out.println(theString.substring(1, theString.length() - 2));
  }

}
