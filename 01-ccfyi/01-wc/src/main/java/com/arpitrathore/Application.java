package com.arpitrathore;

import picocli.CommandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "ccwc",
    mixinStandardHelpOptions = true,
    version = "1.0",
    description = "counts the number for lines, words, characters ")
class Application implements Callable<String> {

  @CommandLine.Option(
      names = {"-c"},
      description = "-c for counting characters")
  private boolean countCharacters;

  @CommandLine.Option(
      names = {"-l"},
      description = "-l for counting lines")
  private boolean countLines;

  @CommandLine.Option(
      names = {"-w"},
      description = "-w for counting words")
  private boolean countWords;

  @CommandLine.Parameters(index = "0", description = "The file to calculate for.")
  private String filePath;

  public static void main(String[] args) {
    var cmd = new CommandLine(new Application());
    cmd.execute(args);
    String result = cmd.getExecutionResult();
    if (result != null) {
      System.out.println(result);
    }
  }

  @Override
  public String call() throws Exception {
    if (!new File(this.filePath).exists()) {
      throw new FileNotFoundException("File " + this.filePath + " does not exist");
    }
    return WordCountUtility.getCount(
        this.filePath, this.countLines, this.countWords, this.countCharacters);
  }
}
