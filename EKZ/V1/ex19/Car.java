package ex19;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class Car implements Vehicle {
    @Retention(RetentionPolicy.RUNTIME)
    @interface FrstAnno{
        String descr();
        int val();
    }

    @FrstAnno(descr = "Це є метод Ride він успідкований від interface Vehicle", val = 2)
    @Override
    public void Ride() {
        System.out.println("Car starts moving");
        Car car = new Car();
        try{
            // 1. Спочатку одержимо об’єкт car,
            // що описує клас Car
            Class<?> c = car.getClass();
            // 2. Одержимо об’єкт Method, що
            // представляє метод someMethod
            Method m = c.getMethod("Ride");
            // 3. Одержуємо анотацію методу
            FrstAnno anno = m.getAnnotation(FrstAnno.class);
            // 4. Відображуємо анотацію
            System.out.println(anno.descr() + " " + anno.val());
            }
            catch (NoSuchMethodException ex)
            {
                System.out.println("Метод не знайдено");
            }
    }
    @FrstAnno(descr = "Це є метод StartEngine він успідкований від interface Vehicle", val = 5)
    @Override
    public boolean StartEngine() {

        Car car = new Car();
        try{
            // 1. Спочатку одержимо об’єкт car,
            // що описує клас Car
            Class<?> c = car.getClass();
            // 2. Одержимо об’єкт Method, що
            // представляє метод Ride
            Method m = c.getMethod("StartEngine");
            // 3. Одержуємо анотацію методу
            FrstAnno anno = m.getAnnotation(FrstAnno.class);
            // 4. Відображуємо анотацію
            System.out.println(anno.descr() + " " + anno.val());
            }
            catch (NoSuchMethodException ex)
            {
                System.out.println("Метод не знайдено");
            }


        if (true)//Якщо не має поломок, то вона заводиться 
        {
            return true;
        }
        //Якщо є поломки, то машина не заводиться
        return false;
    }
    
}
interface Vehicle
{
    void Ride();//Почати рух
    boolean StartEngine();//Завести двигун
}