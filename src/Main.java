public class Main {

    public static void main(String[] args) {
        System.out.println(new OathParser().parseOathTTSSavefileString(args[0]).toString());
    }
}
