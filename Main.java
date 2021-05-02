import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT+1);
        CountDownLatch countDownLatch = new CountDownLatch(CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT), new Road(40));               // создаем arraylist объектов типа stage, вместе они - вся трасса
        Car[] cars = new Car[CARS_COUNT];                                                                   //обозначаем массив объектов типа CAR, численностью CARS_COUNT (задано в мэйне выше)


        for (int i = 0; i < cars.length; i++) {                                                              //создаем сами машины (заполняем массив объетами типа CAR с рандомной скоростью и которые все выполняют RACE)
            cars[i] = new Car(cyclicBarrier, countDownLatch, race, 20 + (int) (Math.random() * 10));
        }


        for (int i = 0; i < cars.length; i++) {                                                              //действия каждой из созданных CAR заключаем в отдельный поток и запускаем
            new Thread(cars[i]).start();
        }


        try {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            cyclicBarrier.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            countDownLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

