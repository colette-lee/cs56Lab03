class Course {
  private String courseCode;
  private String courseName;
  private int numEnrolled;
  private Student[] studentArray;

  private static int maxStudents;

  public Course(String code, String name){
    courseCode = code;
    courseName = name;
    numEnrolled=0;
    maxStudents=50;
    studentArray = new Student[maxStudents];

  }

  public String getCourseCode(){return courseCode;}
  public String getCourseName(){return courseName;}
  public int getNumEnrolled(){return numEnrolled;}
  public Student[] getStudentArray() {return studentArray;}

  public void addStudent(Student s) throws StudentLimitException, DuplicateStudentException {

    if(numEnrolled>=maxStudents){
      throw new StudentLimitException();
    }
    for(int i=0; i<numEnrolled; i++){
      if(s.getPerm()==studentArray[i].getPerm()){
        throw new DuplicateStudentException();
      }
    }
    if(numEnrolled==0) {
      studentArray[0]= s;
      numEnrolled++;
      return;
    }

    String name;
    String newName = s.getLastName() + s.getPerm();
    for(int i=0; i<numEnrolled; i++){
      name = studentArray[i].getLastName() + studentArray[i].getPerm();
      if(newName.compareTo(name)<0){
        //move elements up
        for(int j=numEnrolled-1; j>=i; j--){
          studentArray[j+1]=studentArray[j];
        }
        studentArray[i]=s;
        break;

      }

      if(i==numEnrolled-1)
    	  studentArray[numEnrolled] = s;
    }
    numEnrolled++;

  }
  public void removeStudent(int studentId) throws EmptyStudentListException, StudentNotFoundException {
    Student s;
    if(numEnrolled==0){
      throw new EmptyStudentListException();
    }
    for(int i=0; i<numEnrolled; i++){
      s = studentArray[i];
      if(studentId == s.getPerm()){
        for(int j=i;j<numEnrolled-1; j++){
          studentArray[j] = studentArray[j+1];
        }
        break;
      }
      if (i==numEnrolled-1){
        throw new StudentNotFoundException();
      }
    }
    studentArray[numEnrolled-1]=null;
    numEnrolled--;

  }

}
