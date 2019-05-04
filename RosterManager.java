class RosterManager {
  private Course[] courseArray;
  private int totalCourses;
  private static int maxCourses;
  private static int maxStudents;

  RosterManager(){
    totalCourses=0;
    maxCourses = 10;
    maxStudents = 50;
    courseArray = new Course[maxCourses];
  }

  private ClassRosterUI ui;

  public void run(){
    ui = new ClassRosterUI();
    System.out.println("Welcome to Class Roster Manager!\nSelect an action based on the following menu:\n----------");
    boolean exit = false;
    while(!exit){
      ui.printMenu();
      String command = ui.getCommand();
      switch (command){
        case "ac":
          try{
            addCourse(ui.getCourse());
          } catch (DuplicateCourseException e) {
            System.out.println("ERROR: Duplicate course");
          } catch (CourseLimitException e){
            System.out.println("ERROR: Exceeded maximum number of courses");
          }
          break;
        case "dc":
          try{
            deleteCourse(ui.getCourseCode(false));
          } catch (CourseNotFoundException e){
            System.out.println("ERROR: Course not found");
          } catch (EmptyCourseListException e){
            System.out.println("ERROR: Course list is empty");
          }
          break;
        case "as":
          try{
            addStudent(ui.getCourseCode(true), ui.getStudent());
          } catch (StudentLimitException e){
            System.out.println("ERROR: Exceeded maximum number of students");
          } catch (DuplicateStudentException e){
            System.out.println("ERROR: Student is already enrolled");
          } catch (CourseNotFoundException e){
            System.out.println("ERROR: Course not found");
          }
          break;
        case "ds":
          try {
            String code = ui.getCourseCode(true);
            deleteStudent(ui.getStudentID(), code);
          } catch (EmptyStudentListException e){
            System.out.println("ERROR: No students enrolled in this course");
          } catch (StudentNotFoundException e){
            System.out.println("ERROR: Student is not enrolled in this course");
          } catch (CourseNotFoundException e){
            System.out.println("ERROR: Course not found");
          }
          break;
        case "p":
          printRoster();
          break;
        case "q":
          exit=true;
          ui.quit();
          break;
      }
    }

  }

  public void addCourse(Course c) throws DuplicateCourseException, CourseLimitException {
    if(totalCourses==maxCourses){
      throw new CourseLimitException();
    }
    for(int i=0; i<totalCourses; i++){
      if(c.getCourseCode().equalsIgnoreCase(courseArray[i].getCourseCode())){
        throw new DuplicateCourseException();
      }
    }
    courseArray[totalCourses] = c;
    totalCourses++;
    System.out.println("----------------");
  }

  public void deleteCourse(String courseCode) throws CourseNotFoundException, EmptyCourseListException {
    System.out.println("in deleteCourse");
    if(totalCourses==0){
      throw new EmptyCourseListException();
    }
    Course c;
    for(int i=0; i<totalCourses; i++){
      if(courseArray[i].getCourseCode().equalsIgnoreCase(courseCode)){
        //delete
        for(int j=i; j<totalCourses-1; j++){
          courseArray[j]=courseArray[j+1];
        }
        break;
      }
      if (i==totalCourses-1) {
        throw new CourseNotFoundException();
      }
    }
    courseArray[totalCourses-1] = null;
    totalCourses--;
    System.out.println("----------------");
  }

  public void addStudent(String courseCode, Student s) throws StudentLimitException, DuplicateStudentException, CourseNotFoundException {
    Course c;
    for(int i=0; i<totalCourses; i++){
      c = courseArray[i];
      if(c.getCourseCode().equalsIgnoreCase(courseCode)){
        c.addStudent(s);
        break;
      }
      if(i==totalCourses-1){
        throw new CourseNotFoundException();
      }
    }
    System.out.println("----------------");
  }

  public void deleteStudent(int id, String courseCode) throws EmptyStudentListException, StudentNotFoundException, CourseNotFoundException {
    Course c;
    for (int i=0; i<totalCourses; i++){
      c = courseArray[i];
      if(c.getCourseCode().equalsIgnoreCase(courseCode)){
        c.removeStudent(id);
        break;
      }
      if(i==totalCourses-1){
        throw new CourseNotFoundException();
      }
    }
    System.out.println("----------------");
  }

  public void printRoster(){
    System.out.println("*******************");
    Course c;
    for(int i=0; i<totalCourses; i++){
      c = courseArray[i];
      System.out.println(c.getCourseCode()+": "+c.getCourseName());
      System.out.println("Enrolled: " + c.getNumEnrolled());
      Student[] studentArray = c.getStudentArray();
      for(int j=0; j<c.getNumEnrolled(); j++){
        Student s = studentArray[j];
        System.out.println("\t"+ s.getPerm() + " | " + s.getLastName() + ", " + s.getFirstName());
      }
    }
    System.out.println("*******************");
    System.out.println("----------------");
  }
}
