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

import file.Directory;
import commands.Pwd;


/**
 * This class represents the Change Directory command
 *
 */
public class ChangeDirectory extends PathCheck {

  /**
   * Changes into sub directory based on given path, returns the new directory,
   * if argument is invalid prints error, and return the original working  
   * directory of user
   * 
   * @param path: full path location of the directory to change into
   * @param curr_dir: current working directory
   * @param curr_working_path: path of current working directory
   * @return changed directory or original directory if given path does not
   *         exist
   */
  public Directory ChangeDir(String path, Directory curr_dir, Directory root,
      Pwd pwd) {
    // if path is multiple forward slashes, then change direcotry to root
    if (path.matches("/+")) {
      //set current directory to root
      curr_dir = root;
      // set pwd to root
      pwd.root();
      // check for invalid path format with forward slashes
    } else if (path.matches("/+")) {
      // print error message
      System.out
          .println(path + ": Provided path is not valid or does not exist");
      // change directory to other paths excluding root
    } else {
      // store original dir to return, in case given path doesn't exist
      Directory orig_dir = curr_dir;
      // if path is abs path, then switch curr dir to root, to start search from
      // root
      if (path.indexOf("/") == 0) {
        // set current dir to root
        curr_dir = root;
      }
      // make sure path is the  absolute path
      path = this.generate_fullpath(path, pwd.pwd());
      // check if curr_dirr is root and the path is valid, then change pwd to
      // root
      if (this.parseDirectory("Directory", root, path) != null
          && curr_dir == root) {
        pwd.root();
      }
      // split path based on sub directories using the forward slashes
      String[] directories = path.split("/+");
      // keep track of number of pwd changes completed
      int index = 0;
      // index of the directory to change to from the directories array
      int dir_index;
      // if current directory is root, or starts with root symbol and curr dir 
      // is the root
      if (pwd.pwd().equals("/")
          || pwd.pwd().indexOf("/") == 0 && curr_dir == root) {
        dir_index = 1;
      } else {
        // else get the index of the directory to change to from finding the
        // length of directories in pwd path
        String[] cur_directories = pwd.pwd().split("/");
        dir_index = cur_directories.length;
      }
      int location_found = 0;
      // while the sub directories are found and index does not surpass the
      // number
      // of directories
      while (location_found != -1 && dir_index < directories.length) {
        // if change to parent directory
        if (directories[dir_index].equals("..")) {
          // change one directory up, move to next index
          curr_dir = (Directory) this.parseDirectory("Directory", curr_dir,
              directories[dir_index]);
          index += 1;
          dir_index += 1;
          // change pwd to parent
          pwd.upDir(1);
          // stay in current directory, increment index
        } else if (directories[dir_index].equals(".")) {
          index = +1;
          dir_index += 1;
        } else {
          // get next sub directory in directories, and increment index
          Directory newdir = (Directory) this.parseDirectory("Directory",
              curr_dir, directories[dir_index]);
          index += 1;
          dir_index += 1;
          // if sub directory exists, set curr_dir to be the new sub directory
          if (newdir != null) {
            curr_dir = newdir;
            pwd.dirChange(curr_dir);
          }
          // child directory does not exist
          else {
            // change pwd back to original path
            pwd.upDir(index - 1);
            System.out.println(
                path + ": Provided path is not valid or does not exist");
            // set curr dir to original
            curr_dir = orig_dir;
            location_found = -1;
          }
        }
      }
    }
    return curr_dir;


  }

  /**
   * Returns root directory
   * 
   * @param root: is the root directory
   * @return root directory
   */
  public Directory changeDirRoot(Directory root) {
    return root;
  }

}
