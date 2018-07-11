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

import Stack.Stack;

/**
 * This class represents the History command
 *
 */
public class History {

  /**
   * Object instantiation to use stack functionality
   */
  private Stack saved_cmds = new Stack();
  /**
   * Object instantiation to use stack functionality
   */
  private Stack temp_cmd = new Stack();

  /**
   * Inserts given string array of command into History stack
   * 
   * @param command: the command being called in an array
   */
  public void pushCom(String[] command) {
    // push the command into the stack
    int len = command.length;
    String result = "";
    for (int i = 0; i < len; i++) {
      result = result + " " + command[i];
    }
    saved_cmds.push(result);
  }

  /**
   * returns the numbers of commands stored in history
   * 
   * @return the number of commands in history
   */
  public int getsize() {
    return saved_cmds.size();
  }

  /**
   * Print all the commands from the history stack
   * 
   */
  public void outputHist() {
    this.outputNumHist(this.getsize());
  }


  /**
   * Print the given amount of recent commands from the history stack
   * 
   * @param amount: number of commands to print from the history stack
   */
  public void outputNumHist(int amount) {
    // keep track of the position on the command, and initialize a string
    // variable
    int counter = 0;
    String result = "";
    // total number of commands saved in history
    int total_cmds = saved_cmds.size();
    // if requested amount is greater than the number of commands stored
    if (amount > saved_cmds.size()) {
      System.err
          .println("OutOfBoundError: exceeded maximum amount of commands");
    } else {
      // while the current command position does not exceed the amount requested
      while (counter < amount) {
        // pop from saved commands
        String command = (String) saved_cmds.pop();
        // temporarily store it in another stack
        temp_cmd.push(command);
        // append the position and the command name to the result
        counter += 1;
      }

      // position of command in terms of most recent
      int pos = total_cmds - amount;
      // temporary stack size
      int temp_cmd_size = temp_cmd.size();
      // loop through the temporary stack, and put back the commands that were
      // extracted from the saved_commands stack
      for (int i = 0; i < temp_cmd_size; i++) {
        String cmd_return = (String) temp_cmd.pop();
        saved_cmds.push(cmd_return);
        // proceed to next position
        pos += 1;
        // append to result
        result = result + pos + ". " + cmd_return + "\n";
      }
      // print the result
      System.out.print(result);
    }

  }


}
