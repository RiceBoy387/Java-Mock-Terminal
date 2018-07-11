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

import file.File;
import file.Directory;
import commands.Pwd;

/**
 * This class represents the Cat command
 *
 */
public class Cat extends PathCheck {

  /**
   * Print the contents of the given file
   * 
   * @param file: file that the contents are being printed
   * @return
   */
  public String printContents(File file) {
    String result_str = "";

    // call content function of class file to get contents
    result_str = file.contents();
    return result_str;
  }

  /**
   * This method parses through a given directory looking for a specific file
   * 
   * @param curr_dir The current working directory you're searching for the file
   *        in
   * @param
   * @param path: the path of the directory you are looking for
   * @return the file you were looking for or if the file doesn't exist then
   *         returns null
   */
  public File getFile(String path, Directory curr_dir, Pwd pwd) {
    // use parseDirectory to find the file at the given path
    File file = (File) this.parseDirectory("File", curr_dir, path);

    return file;
  }

  public String cat(String[] path, Directory curr_dir, Pwd pwd) {
    String result = "";
    // Run a loop to get all the paths that they want to show the contents
    // of
    for (int i = 0; i < path.length; i++) {
      // invalid path format
      if (path[i].matches("/+")) {
        // print error message
        result = result + path[i]
            + ": Provided path is not valid or does not exist \n";
      } else {
        // get the file at given path
        File file = this.getFile(path[i], curr_dir, pwd);
        // if file does exist, print contents
        if (file != null) {
          if (i + 1 == path.length && path.length == 1) {
            result = result + this.printContents(file);
          } else {
            result = result + this.printContents(file) + "\n \n \n";
          }
        } // check if file exists
        else {
          result = result + path[i] + ": File does not exist";
        }
      }
    }
    return result;

  }
}
