import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Illia Krysenko on 08.12.2016.
 */
@FunctionalInterface
interface IFuncInt {
    void func(String num1, String num2);
    public String toString();
}

public class LambdaVarDemo {

    public static void main(String[] args){
        List<String> list0 = new ArrayList<>();
        list0.add("asd");
        list0.add("__");
        list0.add("66");
        final List list = list0;
        IFuncInt funcInt = (num1, num2) -> {
            list.add(num1 + num2);
            list.remove(2);
        };
        funcInt.func("rr","yy");
        list0 = new ArrayList<>();
        //list = new ArrayList<>();
    }
}
