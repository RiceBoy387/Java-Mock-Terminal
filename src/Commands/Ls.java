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

import commands.ChangeDirectory;
import commands.Pwd;
import file.Directory;
import file.File;
import file.FileType;

/**
 * This class represents the List command
 *
 */
public class Ls extends PathCheck {

  /**
   * Object instantiation to use change directories functionality
   */
  public ChangeDirectory cd = new ChangeDirectory();

  /**
   * Prints all directories and files in given current working directory
   * 
   * @param Working_dir: current working directory
   * @return string of concatenated names of files and directories in working
   *         dir
   */

  public String LsWorkingDir(Directory working_dir) {
    String result = "";
    // loop through each item in the working directory and print the filename
    for (int i = 0; i < working_dir.numFile(); i++) {
      result = result + (working_dir.getFile(i)).getFileName() + "\n";
    }
    return result;
  }

  /**
   * Prints all directories and files of the directory at the given path, if the
   * path exists, otherwise does nothing
   * 
   * @param root: root directory of file system
   * @param Working_dir: current working directory of user
   * @param path: path of directory that must have items in it listed
   * @param pwd: path of working directory
   * @return string of concatenated names of files and directories in given
   *         directory
   */

  private String LsGivenDir(Directory root, Directory working_dir, String path,
      Pwd pwd) {
    String result_str = "";
    String init_path = path;
    // get current working path, and generate the absolute path
    String curr_working_path = pwd.pwd();
    path = this.generate_fullpath(path, curr_working_path);
    // check if path points to a file
    FileType type_file = this.parseDirectory("File", working_dir, path);
    // if type is file and not directory
    if (type_file != null) {
      this.LsGivenFile((File) type_file);
    } else {
      // change into the desired directory based on path
      Directory ls_dir =
          (Directory) this.parseDirectory("Directory", working_dir, path);
      // call LsworkingDir on new changed directory , to print all items in
      // that directory
      if (ls_dir == null) {
        result_str = path + ": Provided path does not exist \n";
      }
      // if the changed dir is not the root
      else if (ls_dir != root) {
        // print each file and dir name
        result_str = init_path + ":";
        for (int i = 0; i < ls_dir.numFile(); i++) {
          result_str = result_str + " " + (ls_dir.getFile(i)).getFileName();
        }
        result_str = result_str + "\n";
      }
    }

    return result_str;
  }

  /**
   * Prints all directories and files of the root directory
   * 
   * @param root: root directory of file system
   * @param path: path of directory that must have items in it listed
   * @return string of concatenated names of files and directories in root
   *         directory
   */
  private String lsRootHelper(Directory root, String path) {
    // initial string var with path name
    String result_str = path + ":";
    // loop thorough all files and directories in root and concatenate the names
    for (int i = 0; i < root.numFile(); i++) {
      result_str = result_str + " " + (root.getFile(i)).getFileName();
    }
    // add a newline character and return the string
    result_str = result_str + "\n";
    return result_str;
  }

  /**
   * Prints name of given file
   * 
   * @param file
   * @return file name
   * 
   */

  public String LsGivenFile(File file) {
    // get and return file name
    return file.getFileName();
  }

  /**
   * Given 1 or more paths to a directory this method will list files and
   * subdirectories of each directory if
   * 
   * @param root: root directory of file system
   * @param Working_dir: current working directory of user
   * @param path: path of directory that must have items in it listed
   * @param pwd: path of working directory
   * @return string of concatenated names of files and directories of given
   *         paths
   */
  public String ls(Directory root, Directory currDir, String[] desiredPaths,
      Pwd work_dir) {
    String result = "";
    // loop through all paths and concatenate the resulting string names of file
    // and dirs
    for (int i = 0; i < desiredPaths.length; i++) {
      // if invalid path format
      if (desiredPaths[i].matches("/+")) {
        // print error message
        result = result + desiredPaths[i]
            + ": Provided path is not valid or does not exist \n";
        // check if the path is relative
      } else if (desiredPaths[i].equals("/")) {
        // if files and directories of root must be listed
        result += this.lsRootHelper(root, desiredPaths[i]);
      } else {
        // if given path is not root
        result += this.LsGivenDir(root, currDir, desiredPaths[i], work_dir);
      }
    }
    return result;
  }

}

