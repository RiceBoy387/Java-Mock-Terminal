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
import file.File;
import file.FileType;

/**
 * This class represents the echo command when appending a string onto a file
 *
 */
public class EchoAppend extends PathCheck {

  /**
   * Appends the given String <content> to the file at <filePath>, if the file
   * does not exist at that location, it will create the file and add <contents>
   * to it.
   * 
   * @param currDir Directory from which filePath is relative to
   * @param filePath relative path to desired file
   * @param content the desired content to add to the file
   */
  public void run(Directory currDir, String filePath, String content) {
    FileType desiredFile = this.checkExistence(currDir, filePath);
    // test for file with desired name
    if (desiredFile != null
        && "file.File".equals(desiredFile.getClass().getName())) {
      // append to file
      this.write((File) desiredFile, content);
      // test for directory that will contain the file
    } else if (desiredFile != null
        && "file.Directory".equals(desiredFile.getClass().getName())) {
      if (!desiredFile.getFileName().equals("/null")) {
        // test for valid name
        if (validChars(filePath.substring(filePath.lastIndexOf("/") + 1))) {
          // create new file
          File newF = this.create(
              filePath.substring(filePath.lastIndexOf("/") + 1), content);
          currDir = (Directory) desiredFile;
          currDir.append(newF);
        } else {
          System.out.println("The File name contains invalid characters");
        }
      }
      // file not found but the directory it needs to be in exists
    } else if (desiredFile == null) {
      // create the file and add it to the directory
      File newF = this.create(filePath, content);
      currDir.append(newF);
    }
  }

  /**
   * Appends String <content> to the given File <toAppend>
   * 
   * @param toAppend File that must be appended to
   * @param content String that must be appended to the File
   */
  public void write(File toAppend, String content) {
    toAppend.append(content + "\n");
  }

  /**
   * Creates a new file with the name <fileName> and adds the string <content>
   * to it then returns it
   * 
   * @param fileName name for the desired file
   * @param content content for the desired file
   * @return the newly created File, that contains <content>
   */
  protected File create(String fileName, String content) {
    File newF = new File(fileName);
    newF.append(content + "\n");
    return newF;
  }

  /**
   * Returns true if the name for the file meets the character requirements else
   * it will return false
   * 
   * @param name the desired name to test for correct characters
   * @return true if the string has correct characters
   */
  protected boolean validChars(String name) {
    return (name.matches("[a-zA-Z0-9.]*"));
  }

  /**
   * Returns the FileType that the relative <filePath> points to. if the file
   * does not exist returns null if the path to the file does not exist returns
   * a null directory (Directory with name "/null")
   * 
   * @param currDir the Directory that filePath is relative to
   * @param filePath a String representation of the relative file path
   * @return the desired file or null if the file does not exist
   */
  protected FileType checkExistence(Directory currDir, String filePath) {
    FileType desiredFile = null;
    int fileIndex;
    /*
     * check if file is only part of desired path ie must go in currDir by
     * checking if there are no slashes in the desired filePath
     */
    int slashes = filePath.length() - filePath.replace("/", "").length();
    if (slashes == 0) {
      // check if file exists
      fileIndex = currDir.find(filePath);
      // file was found, get the file and test if it is a directory
      if (fileIndex != -1) {
        desiredFile = currDir.getFile(fileIndex);
        desiredFile = this.isDir(desiredFile);
      }
    } else {
      String pathToFile = filePath.substring(0, filePath.lastIndexOf("/"));
      // get the directory that the file needs to be in, if it exists
      currDir =
          (Directory) this.parseDirectory("Directory", currDir, pathToFile);
      if (currDir != null) {
        // check file name
        fileIndex =
            currDir.find(filePath.substring(filePath.lastIndexOf("/") + 1));
        // file does not exist yet, send back directory it will be put in
        if (fileIndex == -1) {
          desiredFile = currDir;
        } else {
          // get the file at index and test if it is a directory or file
          desiredFile = currDir.getFile(fileIndex);
          desiredFile = this.isDir(desiredFile);
        }
        // targeted directory does not exist
      } else {
        System.out
            .println("The desired target directory for File does not exist");
        // null directory (can never be created by user because of invalid name)
        desiredFile = new Directory("/null", null);
      }
    }
    return desiredFile;
  }

  /**
   * Returns null if the FileType <test> is a Directory, returns the original
   * test if it is not a directory
   * 
   * @param test the FileType to test
   * @return null if <test> is a Directory else <test>
   */
  private FileType isDir(FileType test) {
    if (test.equalsType("file.Directory")) {
      test = null;
      System.out.println("Target file already exists as a Directory");
    }
    return test;
  }

}
