public class Main {

    public static void main(String[] args) {
        //Person instance
        System.out.println("PERSON OBJECT STATE:");
        Person person = new Person();
        person.setName("Kai");
        person.setAge(32);
        System.out.println(person);  //calls toString()
        person.celebrateBirthday();
        System.out.println("after birthday:" + person);   //calls toString()
        System.out.println("weekend plans:"+person.getWeekendPlans());
        System.out.println();
    }
}
