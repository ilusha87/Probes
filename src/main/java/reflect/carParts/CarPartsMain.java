package reflect.carParts;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by admin on 06.06.2016.
 */
public class CarPartsMain {

    public static boolean verifyCarPart(String someClassName) {
        Class someClass = null;
        try {
            someClass =  Class.forName(someClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        if (someClass == null)
            return false;
        if (!someClass.isAnnotationPresent(CarPart.class))
            return false;

        CarPart anno = (CarPart) someClass.getAnnotation(CarPart.class);

        boolean hasPartCode = false,
                hasPartPrice = false,
                hasPartName = false,
                hasPartReplacements = false;
        Field[] fields = someClass.getFields();
        for(Field field: fields){
            if(anno.partName().equals(field.getName())) hasPartName = true;
            if(anno.partCode().equals(field.getName())) hasPartCode = true;
            if(anno.partPrice().equals(field.getName())) hasPartPrice = true;
            if(anno.partReplacements().equals(field.getName())) hasPartReplacements = true;
        }
        return hasPartName & hasPartCode & hasPartPrice & hasPartReplacements;
    }
    public static void main (String[] args) {
        System.out.println(verifyCarPart("test.reflect.carParts.CarPartExample"));
    }
}
