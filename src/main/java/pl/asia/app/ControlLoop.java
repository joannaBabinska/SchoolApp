package pl.asia.app;

import pl.asia.dao.TeacherDao;
import pl.asia.io.file.ConsolePrinter;
import pl.asia.service.TeacherService;

public class ControlLoop {

  private final TeacherDao teacherDao;
  private final TeacherService teacherService;
  private final ConsolePrinter consolePrinter;

  public ControlLoop(TeacherDao teacherDao, TeacherService teacherService, ConsolePrinter consolePrinter) {
    this.teacherDao = teacherDao;
    this.teacherService = teacherService;
    this.consolePrinter = consolePrinter;
  }

  void mainLoop() {
    Options options;
    printOptions();
    getOptions();
  }

  private void getOptions() {
    boolean optionOK = false;
    Options options;
    while (!optionOK) {
      try {
        options =
      }

    }
  }


//  void loop() {
//    Options options;
//    do {
//      printOptions();
//    } switch ()
//  }

  private void printOptions() {
    consolePrinter.printLine("Wybierz opcję");
    for (Options options : Options.values())
      consolePrinter.printLine(options.toString());
  }


// opttion: dodaj nowego naucycuela
//    teacherService.addTeacher();


  private enum Options {
    EXIT(0, "Wyjście z programu"),
    ADD_TEACHER(1, "Dodaj nauczyciela");

    private final String descriptions;
    private final int value;

    Options(int optionsNumber, String descriptions) {
      this.descriptions = descriptions;
      this.value = optionsNumber;
    }

    public String getDescriptions() {
      return descriptions;
    }

    public int getValue() {
      return value;
    }

    @Override
    public String toString() {
      return value + " - " + descriptions;
    }

    private static Options createFromInt(int options) {
      try {
        return Options.values()[options];
      } catch (ArrayIndexOutOfBoundsException e){
          throw new NoSuchOptionException ("Brak opcji o id " + options);
      }
    }

  }
}