# Method Overriding

## Learning Goals

- Demonstrate method overriding
- Use the`super` keyword to call a method in the superclass

## Introduction - Code Along

UML diagram of inheritance hierarchy

Fork and clone this lesson.  The code contains a `Person` class along with a driver class named `Main`.
You will add two subclasses `Teacher` and `Student`.

![uml subclass overriding](https://curriculum-content.s3.amazonaws.com/6677/pillars/uml_overriding.png)

## Person

The `Person` class will be the base class (i.e. superclass)
of the inheritance hierarchy.  Each `Person` object
has fields `name` and `age`, along with methods that access and mutate
the fields.

```java
public class Person {

    private String name;
    private int age;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public void celebrateBirthday() { 
        age++; 
    }

    public String getWeekendPlans() {
        return "sleep late and relax all day";
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}
```

We have a separate driver class named `Main` to
create a `Person` class instance and invoke the methods:

```java
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
        System.out.println("weekend plans:" + person.getWeekendPlans());
    }
}
```

The program prints as output:

```text
PERSON OBJECT STATE:
name=Kai, age=32
after birthday:name=Kai, age=33
weekend plans:sleep late and relax all day
```

## Teacher extends Person

Create a new class named `Teacher` that extends `Person`.
Initially the class body is empty:

```java
public class Teacher extends Person {

}
```

`Teacher` inherits the fields and methods from `Person`.


Update the `main` method to instantiate
a `Teacher` and call the inherited methods `setName()`, `setAge()`,
`toString()`, `celebrateBirthday()` and `getWeekendPlans()`:

```java
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

        //Teacher instance, inherits fields and methods from Person
        System.out.println("TEACHER OBJECT STATE:");
        Teacher teacher = new Teacher();
        teacher.setName("Tal");
        teacher.setAge(40);
        System.out.println(teacher);   //calls toString()
        teacher.celebrateBirthday();
        System.out.println("after birthday:" + teacher);   //calls toString()
        System.out.println("weekend plans:"+teacher.getWeekendPlans());
    }
}
```

The program prints as output:

```text
PERSON OBJECT STATE:
name=Kai, age=32
after birthday:name=Kai, age=33
weekend plans:sleep late and relax all day

TEACHER OBJECT STATE:
name=Tal, age=40
after birthday:name=Tal, age=41
weekend plans:sleep late and relax all day
```

Try setting a breakpoint after the `Teacher` object is created.  Use the Java Visualizer
to confirm the `Teacher` object inherits the fields `name` and  `age`. Use "Step Into" to
execute each inherited method.

![breakpoint teacher](https://curriculum-content.s3.amazonaws.com/6677/pillars/breakpoint_teacher.png)

## Method Overriding

Teachers often spend their weekend grading
homework assignments rather than sleeping late and relaxing all day.
We need the `Teacher` class to inherit all of
the `Person` method implementations except for `getWeekendPlans()`.
**Method overriding** is a programming technique that allows
the subclass `Teacher` to provide a different implementation
than the one inherited from the superclass `Person`.

Update the `Teacher` class to override the `getWeekendPlans()` method as shown:

```java
public class Teacher extends Person {

    @Override
    public String getWeekendPlans() {
        return "grade homework assignments";
    }
    
}
```

We use the annotation `@Override` to denote the `getWeekendPlans()` method
is overriding the inherited method.  While the `@Override` annotation
is optional, it is considered a good practice to use it to force
the compiler to check for an inherited method
with the same signature (method name and parameters).

Now the call `teacher.getWeekendPlans()` executes the overriden method
defined in `Teacher` rather than the inherited method defined in `Person`.

```text
PERSON OBJECT STATE:
name=Kai, age=32
after birthday:name=Kai, age=33
weekend plans:sleep late and relax all day

TEACHER OBJECT STATE:
name=Tal, age=40
after birthday:name=Tal, age=41
weekend plans:grade homework assignments
```

Try using the debugger to step into the call `teacher.getWeekendPlans()` and confirm
it executes the implementation in `Teacher` rather than `Person`.

There are a couple of rules when overriding methods:

- Only inherited methods can be overridden.
- This means no private methods can be overridden.
- The parent class and child class must have the exact same method name
  and parameter list in order for the child class to properly override.
  The return type must be the same or a subclass of the inherited method's return type.
- Methods declared with the non-access modifiers static and final cannot be overridden.
- The overriding method does not necessarily need to have the same access modifier as the parent class' method,
  but the overriding method must not have a more restrictive access modifier.


## Student extends Person

Create another new class named `Student` that extends `Person`.

`Student` inherits `name` and `age` from `Person`,
and should define an additional field `favoriteSubject` and accessor/mutator methods.
`Student` also overrides the inherited `getWeekendPlans()`  method as shown:

```java
public class Student extends Person {
    private String favoriteSubject;

    public String getFavoriteSubject() {return favoriteSubject;}

    public void setFavoriteSubject(String favoriteSubject) {this.favoriteSubject = favoriteSubject;}

    @Override
    public String getWeekendPlans() {
        return "wake up early and study all day";
    }
    
}
```

Update the `main` method to create a `Student` instance:

```java
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

        //Teacher instance, inherits fields and methods from Person
        System.out.println("TEACHER OBJECT STATE:");
        Teacher teacher = new Teacher();
        teacher.setName("Tal");
        teacher.setAge(40);
        System.out.println(teacher);   //calls toString()
        teacher.celebrateBirthday();
        System.out.println("after birthday:" + teacher);   //calls toString()
        System.out.println("weekend plans:"+teacher.getWeekendPlans());
        System.out.println();

        //Student instance, inherits fields and methods from Person
        System.out.println("STUDENT OBJECT STATE:");
        Student student = new Student();
        student.setName("Hao");
        student.setAge(24);
        student.setFavoriteSubject("Java programming");
        System.out.println(student);   //calls toString()
        student.celebrateBirthday();
        System.out.println("after birthday:" + student);   //calls toString()
        System.out.println("weekend plans:"+student.getWeekendPlans());
    }
}
```

Confirm the program output:

```text
PERSON OBJECT STATE:
name=Kai, age=32
after birthday:name=Kai, age=33
weekend plans:sleep late and relax all day

TEACHER OBJECT STATE:
name=Tal, age=40
after birthday:name=Tal, age=41
weekend plans:grade homework assignments

STUDENT OBJECT STATE:
name=Hao, age=24
after birthday:name=Hao, age=25
weekend plans:wake up early and study all day
```

## Referencing superclass method in subclass

Since  `Student` does not override the `toString()` method inherited from `Person`,
we see the output prints their `name` and `age`, but not `favoriteSubject`:

```java
STUDENT OBJECT STATE:
name=Hao, age=24
after birthday:name=Hao, age=25
weekend plans:wake up early and study all day
```

We need to override the `toString()` method in `Student` to include the favorite subject
as part of the string returned from the method.

### Technique #1 : Call inherited public accessor methods

Update `Student` to override the `toString()` method as shown below.
Since `name` and `age` are declared as private in the superclass `Person`,
we need to call the public accessor methods `getName()` and `getAge()`
to get the values:

```java
public class Student extends Person {
    private String favoriteSubject;

    public String getFavoriteSubject() {return favoriteSubject;}

    public void setFavoriteSubject(String favoriteSubject) {this.favoriteSubject = favoriteSubject;}

    @Override
    public String getWeekendPlans() {
        return "wake up early and study all day";
    }

    @Override
    public String toString() {
        return "name=" + getName() + ", age=" + getAge() +
                ", favoriteSubject=" + favoriteSubject ;
    }
}
```

Run the `main` method to confirm the output includes the student's name, age, and favorite subject:

```text
STUDENT OBJECT STATE:
name=Hao, age=24, favoriteSubject=Java programming
after birthday:name=Hao, age=25, favoriteSubject=Java programming
weekend plans:wake up early and study all day
```

### Technique #2 : Call superclass method using `super` keyword

While calling inherited public accessor methods works, notice
there is similar code in terms of formatting the substring
for the person's name and age.

<table>
<tr>
<th>Person</th>
<th>Student</th>
</tr>

<tr>

<td>

<pre>
<code>

@Override
public String toString() {
    return "name=" + name + ", age=" + age;
}

</code>
</pre>

</td>

<td>

<pre>
<code>

@Override
public String toString() {
    return "name=" + getName() + ", age=" + getAge() +
         ", favoriteSubject=" + favoriteSubject ;
}

</code>
</pre>

</td>

</tr>

</table>

A better approach is to have the `toString()` method in `Student` call
the inherited method in `Person` to get the formatted string for the `name`
and `age`.

A subclass method can perform an implicit call to an overriden superclass method using
the keyword `super`.  Thus, we can avoid code duplication by updating
the `toString()` method in `Student`  to call `super.toString()`:

```java
public class Student extends Person {
    private String favoriteSubject;

    public String getFavoriteSubject() {
        return favoriteSubject;
    }

    public void setFavoriteSubject(String favoriteSubject) {
        this.favoriteSubject = favoriteSubject;
    }

    @Override
    public String getWeekendPlans() {
        return "wake up early and study all day";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", favoriteSubject=" + favoriteSubject ;
    }
}
```


```text
STUDENT OBJECT STATE:
name=Hao, age=24, favoriteSubject=Java programming
after birthday:name=Hao, age=25, favoriteSubject=Java programming
weekend plans:wake up early and study all day
```

It is best to observe this in action by setting a breakpoint at the print statement and
use "Step Into" to watch how the methods are called.

![breakpoint tostring](https://curriculum-content.s3.amazonaws.com/6677/pillars/breakpoint_tostring.png)

## Conclusion

A subclass can override an inherited method to provide a different implementation.
There are a couple of rules when overriding methods:

- Only inherited methods can be overridden.
- This means no private methods can be overridden.
- The parent class and child class must have the exact same method name
  and parameter list in order for the child class to properly override.
  The return type must be the same or a subclass of the inherited method's return type.
- Methods declared with the non-access modifiers static and final cannot be overridden.
- The overriding method does not necessarily need to have the same access modifier as the parent class' method,
  but the overriding method must not have a more restrictive access modifier.


## Resources

- [Java Tutorial - Overriding Methods](https://docs.oracle.com/javase/tutorial/java/IandI/override.html)
