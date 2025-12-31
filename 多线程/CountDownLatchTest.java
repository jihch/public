package util.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    // 定义3个CountDownLatch，分别控制B等A、C等B、D等C（初始计数为1，countDown后变为0则释放等待）
    private static final CountDownLatch latchA = new CountDownLatch(1); // A完成后释放B
    private static final CountDownLatch latchB = new CountDownLatch(1); // B完成后释放C
    private static final CountDownLatch latchC = new CountDownLatch(1); // C完成后释放D

    // 共享变量：存储各线程的关键计算结果（实际场景可替换为自定义对象/数据）
    private static int resultA;
    private static int resultB;
    private static int resultC;

    @Test
    public void test() throws InterruptedException {
        // 线程A：无前置依赖，执行完关键计算后释放latchA
        Thread threadA = new Thread(() -> {

            try {
                // 线程A的非依赖前置逻辑（并行执行）
                System.out.println("线程A：执行前置逻辑（并行）");
                Thread.sleep(500); // 模拟前置计算耗时

                // 线程A的关键计算（B依赖此结果）
                resultA = 100; // 核心计算结果
                System.out.println("线程A：完成关键计算，结果=" + resultA);
                latchA.countDown(); // 释放latchA，通知B可以继续

                // 线程A的后置逻辑（无需等待，继续执行）
                System.out.println("线程A：执行后置逻辑");
                Thread.sleep(300);
                System.out.println("线程A：执行完毕");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }, "Thread-A");

        // 线程B：执行到关键行时，等待latchA(A的结果)，完成后释放latchB
        Thread threadB = new Thread(() -> {
            try {
                // 线程B的非依赖前置逻辑（并行执行）
                System.out.println("线程B：执行前置逻辑（并行）");
                Thread.sleep(300); // 模拟前置计算耗时

                // 线程B的依赖行（必须等A的结果）
                System.out.println("线程B：等待线程A的关键计算结果...");
                latchA.await(); // 阻塞，直到latchA的计数变为0（A完成关键计算）
                resultB = resultA + 50; // 依赖A的结果进行计算
                System.out.println("线程B：获取A的结果，完成关键计算，结果=" + resultB);
                latchB.countDown(); // 释放latchB，通知C可以继续

                // 线程B的后置逻辑（无需等待，继续执行）
                System.out.println("线程B：执行后置逻辑");
                Thread.sleep(200);
                System.out.println("线程B：执行完毕");

            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }, "Thread-B");

        // 线程C：执行到关键行时等待latchB（B的结果），完成后释放latchC
        Thread threadC = new Thread(() -> {
            try {
                // 线程C的非依赖前置逻辑（并行执行）
                System.out.println("线程C：执行前置逻辑（并行）");
                Thread.sleep(200); // 模拟前置计算耗时

                // 线程C的依赖行（必须等B的结果）
                System.out.println("线程C：等待线程B的关键计算结果...");
                latchB.await(); // 阻塞，直到latchB的计数变为0（B完成关键计算）
                resultC = resultB * 2; // 依赖B的结果进行计算
                System.out.println("线程C：获取B的结果，完成关键计算，结果=" + resultC);
                latchC.countDown(); // 释放latchC,通知D可以继续

                // 线程C的后置逻辑（无需等待，继续执行）
                System.out.println("线程C：执行后置逻辑");
                Thread.sleep(100);
                System.out.println("线程C：执行完毕");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }, "Thread-C");

        // 线程D：执行到关键行时等待latchC（C的结果），无后续依赖
        Thread threadD = new Thread(() -> {
            try {
                // 线程D的非前置依赖逻辑（并行执行）
                System.out.println("线程D：执行前置逻辑（并行）");
                Thread.sleep(100); // 模拟前置计算耗时

                // 线程D的依赖行（必须等C的结果）
                System.out.println("线程D：等待线程C的关键计算结果...");
                latchC.await(); // 阻塞，直到latchC的计数变为0（c完成关键计算）
                int resultD = resultC - 80; // 依赖C的结果进行计算
                System.out.println("线程D：获取C的结果，完成关键计算，结果=" + resultD);

                // 线程D的后置逻辑（无需等待，继续执行）
                System.out.println("线程D：执行后置逻辑");
                Thread.sleep(150);
                System.out.println("线程D：执行完毕");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }, "Thread-D");

        // 并行启动所有线程（核心：线程整体并行，仅关键行等待）
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        // 等待所有线程执行完毕（main线程等待，可选）
        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
        System.out.println("\n所有线程执行完毕，最终结果链：A=" + resultA
                + " -> B" + resultB
                + " -> C" + resultC
                + " -> D" + (resultC - 80)
        );

    }
}
