package com.tts.threads;

public class Deadlock {

    static class Friend {
        //no setter because it's a final
        private final String name;

        //constructor
        public Friend(String name) {
            this.name = name;
        }
        //getter
        public String getName() {
            return this.name;
        }
        //synchronized methods
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }//end Friend class

    public static void main(String[] args) {

        // this block is an example of a deadlock and just keeps
        // running bc of bowBack on ine 21. comment it out
        final Friend micha = new Friend("Micha");
        final Friend pierre = new Friend("Pierre");
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        new Thread(new Runnable() {
            public void run() { micha.bow(pierre); }
        }).start();
        new Thread(new Runnable() {
            public void run() { pierre.bow(micha); }
        }).start();
        //or do above with lamda
        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();

    }//end main()

}//end Deadlock class
