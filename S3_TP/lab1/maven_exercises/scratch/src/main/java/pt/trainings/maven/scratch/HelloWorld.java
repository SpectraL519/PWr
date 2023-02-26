package pt.trainings.maven.scratch;

public class HelloWorld {
    /**
    * @param args
    */
    public static void main (String[] args) {
        Greetings greetings = new Greetings();
        System.out.println(greetings.sayHello(args.length>0?args[0]:null));
    }
}




// Compiling: mvn package
// Running: mvn exec:java -D"exec.mainClass"="pt.trainings.maven.scratch.HelloWorld"