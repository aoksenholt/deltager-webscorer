package no.aoksenholt.deltagerwebscorer.model;

public class Webscorer {
    private String firstname;
    private String lastname;
    private String teamname;
    private String gender;
    private long age;
    private String distance;
    private String category;
    private String starttime;
    private String email;
    private String info1; // T-skjortstørrelse
    private String info2; // Kondismedlemsnummer

    public Webscorer() {
	// TODO Auto-generated constructor stub
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public String getTeamname() {
	return teamname;
    }

    public void setTeamname(String teamname) {
	this.teamname = teamname;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public long getAge() {
	return age;
    }

    public void setAge(long age) {
	this.age = age;
    }

    public String getDistance() {
	return distance;
    }

    public void setDistance(String distance) {
	this.distance = distance;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public String getStarttime() {
	return starttime;
    }

    public void setStarttime(String starttime) {
	this.starttime = starttime;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getInfo1() {
	return info1;
    }

    public void setInfo1(String info1) {
	this.info1 = info1;
    }

    public String getInfo2() {
	return info2;
    }

    public void setInfo2(String info2) {
	this.info2 = info2;
    }

    @Override
    public String toString() {
	return String.join(",", lastname, firstname, teamname, gender, String.valueOf(age), distance, category, info1, info2);
    }

}
