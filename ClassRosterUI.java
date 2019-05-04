import java.util.Scanner;
class ClassRosterUI {
  private static Scanner s;

  public ClassRosterUI(){
    s = new Scanner(System.in);
  }
  public static void printMenu(){
    System.out.println("ac: Add Course\ndc: Drop Course\nas: Add Student\nds: Drop Student\np: Print ClassRoster\nq: Quit Program\n----------------");
  }
  public static String getCommand(){
    String input;
    do {
      System.out.print("Enter Command: ");
      input = s.nextLine();

    } while (!(input.equals("ac")||input.equals("dc")||input.equals("as")||input.equals("ds")||input.equals("p")||input.equals("q")));
    return input;
  }

  public static Course getCourse(){
    System.out.print("Enter Course Code: ");
    String code = s.nextLine();
    System.out.print("Enter Course Name: ");
    String name = s.nextLine();
    Course c = new Course(code, name);
    return c;
  }

  public static String getCourseCode(boolean forStudent){
    if (forStudent)
      System.out.print("Enter Course Code for Student: ");
    else
      System.out.print("Enter Course Code: ");
    return s.nextLine();
  }

  public static Student getStudent(){
    System.out.print("Enter PERM: ");
    int perm = s.nextInt();
    while (perm<=0){
      System.out.print("Enter a valid PERM: ");
      perm = s.nextInt();
    }
    s.nextLine();
    System.out.print("Enter last name: ");
    String lName = s.nextLine();
    System.out.print("Enter first name: ");
    Student student = new Student(perm, s.nextLine(), lName);
    return student;
  }

  public static int getStudentID(){
    System.out.print("Enter PERM: ");
    int perm = s.nextInt();
    s.nextLine();
    return perm;
  }

  public static void quit(){
    s.close();
  }

}
