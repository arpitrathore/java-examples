### 1. Build and package native binary
```sh 
mvn clean package -Pnative
```
### 2. Install to path
```sh 
sudo cp ./target/ccwc /usr/local/bin/
```
### 3. Usage
```sh
$ ccwc -l test.txt
        7142 test.txt
$ ccwc -w test.txt
        58164 test.txt
$ ccwc -c test.txt
        334996 test.txt
$ ccwc test.txt
        7142    58164   334996 test.txt
```

### 4. Help
```txt
$ ccwc --help
Usage: ccwc [-chlVw] <filePath>
counts the number for lines, words, characters
      <filePath>   The file to calculate for.
  -c               -c for counting characters
  -h, --help       Show this help message and exit.
  -l               -l for counting lines
  -V, --version    Print version information and exit.
  -w               -w for counting words
```
