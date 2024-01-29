package com.arpitrathore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class WordCountUtility {

  /**
   * Gets count of characters, words and lines from a file
   *
   * @param filePath        the file path
   * @param countLines      whether to get lines count
   * @param countWords      whether to get words count
   * @param countCharacters whether to get characters count
   * @return String representation of count of characters, words and lines
   * @throws IOException if unable to read file
   */
  public static String getCount(final String filePath, boolean countLines, boolean countWords,
      boolean countCharacters) throws IOException {
    var result = new StringBuilder();
    var bytes = Files.readAllBytes(Path.of(filePath));

    var switchAll = (countCharacters == countLines) && (countLines == countWords);
    if (switchAll) {
      countCharacters = true;
      countLines = true;
      countWords = true;
    }

    if (countLines) {
      int linesCount = calculateLinesCount(bytes);
      result.append("\t").append(linesCount);
    }

    if (countWords) {
      int wordsCount = calculateWordsCount(bytes);
      result.append("\t").append(wordsCount);
    }

    if (countCharacters) {
      int charCount = calculateCharsCount(bytes);
      result.append("\t").append(charCount);
    }

    result.append(" ").append(filePath);
    return result.toString();
  }

  private static int calculateCharsCount(byte[] bytes) {
    return bytes.length;
  }

  private static int calculateLinesCount(byte[] bytes) {
    var count = 0;
    for (byte byt : bytes) {
      if (byt == '\n') {
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
