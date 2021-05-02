import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private final CyclicBarrier cyclicBarrier;
    private final CountDownLatch countDownLatch;


    //????????????????? непонятно что это есть и почему это здесь
    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;


    public String getName() {
        return name;
    }


    public int getSpeed() {
        return speed;
    }


    public Car(CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch, Race race, int speed) {              //конструктор каждой тачки, который на вход соазу принимает race, который она поедет
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }


    @Override
    public void run() {                                                                                          //основное действие каждой тачки, готовится, готова, по очереди проходит все Stages трассы из race
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        countDownLatch.countDown();
    }
}
