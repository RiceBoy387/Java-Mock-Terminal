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
import file.*;

/**
 * This class represents the pushd and popd commands
 *
 */
public class PushAndPopd {

  // define a contents in addition to a ChangeDiretory Object
  /**
   * this is the contents of the directory stack
   */
  Stack contents;
  /**
   * This is an instance of the change directory command used to change the
   * current directory
   */
  ChangeDirectory cd;

  /**
   * instantiates a PushAndPopd object
   */
  public PushAndPopd() {
    contents = new Stack();
    cd = new ChangeDirectory();
  }

  /**
   * This function will push the current working directory onto the Stack and
   * change the current directory to the given new directory
   * 
   * @param pathOfWorkingDir the current working directory
   * @param newDir the path of the new directory to be switched into
   * @param workingDir the current working directory
   * @param root the root of the entire file system
   * @param pwd the current path of working directory
   * @return returns the address of the directory it was changed to
   */
  public Directory Pushd(String pathOfWorkingDir, String newDir,
      Directory workingDir, Directory root, Pwd pwd) {
    // to push the current working directory onto the stack use PushToStack
    this.PushToStack(pathOfWorkingDir);
    // to move to the new directory use the ChangeToNewDir method
    return this.ChangeToNewDir(newDir, workingDir, root, pwd);
  }

  /**
   * Pops the last Directory that was pushed and changes to that directory if it
   * still exists, else returns the current working directory
   * 
   * @param workingDir the current working Directory
   * @param root the root of the working file system
   * @param pwd the pwd representation of current working directory
   * @return the new working Directory
   */
  public Directory popd(Directory workingDir, Directory root, Pwd pwd) {
    Directory forReturn = workingDir; // our default directory for return
    // make sure we have something to pop
    if (contents.size() > 0) {
      String pop = (String) contents.pop(); // get the desired path
      // if root set return directory to root and fix pwd
      if (pop.equals("/")) {
        forReturn = root;
        pwd.root();
      } else { // else change directory to desired
        forReturn = cd.ChangeDir(pop, workingDir, root, pwd);
      }
    } else { // tell user there is nothing to pop
      System.out.println("There is nothing to pop");
    }
    // return the new directory
    return forReturn;
  }

  /**
   * This method pushes the directory object onto the stack
   * 
   * @param workingDir the current working directory to be pushed on the stack
   */
  private void PushToStack(String pathOfWorkingDir) {
    // simple just push the workingDir onto the stack
    contents.push(pathOfWorkingDir);
  }

  /**
   * this method changes the current directory to the given new directory
   * 
   * @param newDir the path of the new directory to be switched into
   * @param workingDir the working directory of the new directory to be switched
   *        into
   * @param the root directory of the current file system
   * @param pwd the current path of working directory
   * @return the new directory to be changed into
   */
  private Directory ChangeToNewDir(String newDir, Directory workingDir,
      Directory root, Pwd pwd) {
    // simply use the change directory object to swtich to the new directory
    // Call change directory on the object
    return cd.ChangeDir(newDir, workingDir, root, pwd);

  }

}
