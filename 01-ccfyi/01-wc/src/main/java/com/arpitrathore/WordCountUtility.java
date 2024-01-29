package com.arpitrathore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class WordCountUtility {

  /**
   * Gets count of characters, words and lines from a file
   *
   * @param fileName the file name
   * @param switchLines whether to get lines count
   * @param switchWords whether to get words count
   * @param switchCharacters whether to get characters count
   * @return String representation of count of characters, words and lines
   * @throws IOException if unable to read file
   */
  public static String getCount(
      String fileName, boolean switchLines, boolean switchWords, boolean switchCharacters)
      throws IOException {
    var result = new StringBuilder();
    var bytes = Files.readAllBytes(Path.of(fileName));

    var switchAll = (switchCharacters == switchLines) && (switchLines == switchWords);
    if (switchAll) {
      switchCharacters = true;
      switchLines = true;
      switchWords = true;
    }

    if (switchLines) {
      int linesCount = calculateLinesCount(bytes);
      result.append("\t").append(linesCount);
    }

    if (switchWords) {
      int wordsCount = calculateWordsCount(bytes);
      result.append("\t").append(wordsCount);
    }

    if (switchCharacters) {
      int charCount = calculateCharsCount(bytes);
      result.append("\t").append(charCount);
    }

    result.append(" ").append(fileName);
    return result.toString();
  }

  private static int calculateCharsCount(byte[] bytes) {
    return bytes.length;
  }

  private static int calculateLinesCount(byte[] bytes) {
    var count = 0;
    for (byte byte2 : bytes) {
      if (byte2 == '\n') {
        count++;
      }
    }
    return count;
  }

  private static int calculateWordsCount(byte[] bytes) {
    var count = 0;
    var lastWordCount = 0;
    for (byte byt : bytes) {
      if (Character.isWhitespace(byt)) {
        if (lastWordCount > 0) {
          count++;
          lastWordCount = 0;
        }
      } else {
        lastWordCount++;
      }
    }
    if (lastWordCount > 0) {
      count++;
    }
    return count;
  }
}
