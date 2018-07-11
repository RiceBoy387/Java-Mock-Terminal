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

import file.*;

/**
 * This class represents the echo command when overWriting/creating a file every
 * time it writes a strings
 *
 */
public class EchoCreate extends EchoAppend {

  /**
   * Overwrites File <toOverWrite> with String <content>
   * 
   * @param toOverWrite File that must be overwritten to
   * @param content String that must be overwritten to the File
   */
  public void write(File toOverWrite, String content) {
    toOverWrite.rewrite(content + "\n");
  }

  /**
   * Overwrites the given String <content> to the file at <filePath>, if the
   * file does not exist at that location, it will create the file and add
   * <contents> to it.
   * 
   * @param currDir Directory from which filePath is relative to
   * @param filePath relative path to desired file
   * @param content the desired content to add to the file
   */
  public void run(Directory currDir, String filePath, String content) {
    super.run(currDir, filePath, content);
  }


}
