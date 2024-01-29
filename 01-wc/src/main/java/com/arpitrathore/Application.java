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
  private boolean switchCharacters;

  @CommandLine.Option(
      names = {"-l"},
      description = "-l for counting lines")
  private boolean switchLines;

  @CommandLine.Option(
      names = {"-w"},
      description = "-w for counting words")
  private boolean switchWords;

  @CommandLine.Parameters(index = "0", description = "The file to calculate for.")
  private String fileName;

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
    if (!new File(this.fileName).exists()) {
      throw new FileNotFoundException("File " + this.fileName + " does not exist");
    }
    return WordCountUtility.getCount(
        this.fileName, this.switchLines, this.switchWords, this.switchCharacters);
  }
}
