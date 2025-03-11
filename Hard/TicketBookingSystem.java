package Hard;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private static int availableSeats = 10;
    private static final Lock lock = new ReentrantLock();

    static class BookingThread extends Thread {
        private String passenger;
        private int seatsToBook;

        public BookingThread(String passenger, int seatsToBook, int priority) {
            this.passenger = passenger;
            this.seatsToBook = seatsToBook;
            setPriority(priority);
        }

        @Override
        public void run() {
            lock.lock();
            try {
                if (availableSeats >= seatsToBook) {
                    System.out.println(passenger + " booked " + seatsToBook + " seat(s).");
                    availableSeats -= seatsToBook;
                } else {
                    System.out.println("Booking failed for " + passenger + ". Not enough seats available.");
                }
                System.out.println("Remaining seats: " + availableSeats);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        BookingThread vip1 = new BookingThread("VIP John", 2, Thread.MAX_PRIORITY);
        BookingThread normal1 = new BookingThread("Normal Alice", 4, Thread.NORM_PRIORITY);
        BookingThread vip2 = new BookingThread("VIP Sarah", 3, Thread.MAX_PRIORITY);
        BookingThread normal2 = new BookingThread("Normal Bob", 2, Thread.NORM_PRIORITY);

        vip1.start();
        normal1.start();
        vip2.start();
        normal2.start();
    }
}
