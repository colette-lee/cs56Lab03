class Student {
  private int perm;
  private String firstName;
  private String lastName;

  public Student(int perm, String firstName, String lastName){
    this.perm = perm;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String toString(){
    return perm+" "+firstName+" "+lastName;
  }

  public int getPerm(){return perm;}
  public String getFirstName(){return firstName;}
  public String getLastName(){return lastName;}

  public void setPerm(int perm){this.perm = perm;}
  public void setFirstName(String name){firstName = name;}
  public void setLastName(String name){lastName = name;}

}
