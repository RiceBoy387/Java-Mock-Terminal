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

import java.util.ArrayList;
import file.Directory;
import file.FileSystem;

/**
 * This class represents the find command
 *
 */
public class Find extends PathCheck {

  /**
   * This method acts as a helper for the findFile and actually checks if a
   * given file name exists at a given directory
   * 
   * @param type the type of file that is being searched for i.e Directory or
   *        File
   * @param currDir the current working directory
   * @param searchDir the path of the directory we're searching for the wanted
   *        file in
   * @param searchFile The wanted file
   * @return a boolean value indicating if the file exists
   */
  private boolean findFileHelper(String type, Directory currDir,
      String searchDir, String searchFile) {
    // if parse directory doesn't return null then the file exists
    // concatenate the searchDir and the searchFile also drop quotes from file
    // name
    String totalDir;
    totalDir = searchDir + "/" + searchFile;

    // find out with the search type is as well
    String searchType;
    if (type.equals("f")) {
      searchType = "File";
    } else {
      searchType = "Directory";
    }
    return (this.parseDirectory(searchType, currDir, totalDir) != null);
  }

  /**
   * This method checks if a file/directory with a given name exists at any
   * number of locations and returns true if it does
   * 
   * @param directories the directories to search for the file in
   * @param nameOfFile the name of the file to search for
   * @param type the type of the file to search for either d for directory or f
   *        for file
   * @param fs the current working file system
   */
  public String findFile(ArrayList<String> directories, String nameOfFile,
      String type, FileSystem fs) {
    // store the boolean value of whether the file exists
    boolean exists;
    // Store the output
    String output = "";
    // now loop through each given directory
    for (int i = 0; i < directories.size(); i++) {
      // check if the given directory using find file helper
      if (this.isPathRelative(directories.get(i))) {
        exists = this.findFileHelper(type, fs.getWorkingDir(),
            directories.get(i), nameOfFile);
      } else {
        exists = this.findFileHelper(type, fs.getRoot(), directories.get(i),
            nameOfFile);
      }
      // add the output to the output variable
      if (exists) {
        output += ("The file " + nameOfFile + " of type " + type + " exists at "
            + directories.get(i) + "\n");
      } else {
        output += ("The file " + nameOfFile + " of type " + type
            + " does not exist at " + directories.get(i) + "\n");
      }
    }
    return output;
  }
}
