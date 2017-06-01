package exceptions;

/**
 * Created by Illia Krysenko on 27.02.2017.
 */
public class NestedTryCatchExample {
    public static void main(String[] args) {
        try {

            try {
                throw new Exception();
            } /*catch (Exception e) {
                System.out.println("nested catch");
                e.printStackTrace();
            }*/ finally {
                System.out.println("nested finally");
            }


        } catch (Exception e) {
            System.out.println("outer catch");
            e.printStackTrace();
        } finally {
            System.out.println("outer finally");
        }
    }
}
