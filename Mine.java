public class Mine {
    public static void main(String[] args) {
        streamTest1();
        try {
            streamTest2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void streamTest1(){

        final int size = 10000000;
        float[] arr = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - a);
    }

    static void streamTest2() throws InterruptedException {
        final int size = 10000000;
        final int h = size / 10;
        float[] arr = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        float[] a1 = new float[h];
        float[] a2 = new float[h];
        float[] a3 = new float[h];
        float[] a4 = new float[h];
        float[] a5 = new float[h];
        float[] a6 = new float[h];
        float[] a7 = new float[h];
        float[] a8 = new float[h];
        float[] a9 = new float[h];
        float[] a10 = new float[h];

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.arraycopy(arr, (h * 2), a3, 0, h);
        System.arraycopy(arr, (h * 3), a4, 0, h);
        System.arraycopy(arr, (h * 4), a5, 0, h);
        System.arraycopy(arr, (h * 5), a6, 0, h);
        System.arraycopy(arr, (h * 6), a7, 0, h);
        System.arraycopy(arr, (h * 7), a8, 0, h);
        System.arraycopy(arr, (h * 8), a9, 0, h);
        System.arraycopy(arr, (h * 9), a10, 0, h);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
            a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a3[i] = (float)(a3[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a4[i] = (float)(a4[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a5[i] = (float)(a5[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a6[i] = (float)(a6[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t7 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a7[i] = (float)(a7[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t8 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a8[i] = (float)(a8[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t9 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a9[i] = (float)(a9[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t10 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a10[i] = (float)(a10[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();
        t9.join();
        t10.join();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.arraycopy(a3, 0, arr, (h * 2), h);
        System.arraycopy(a4, 0, arr, (h * 3), h);
        System.arraycopy(a5, 0, arr, (h * 4), h);
        System.arraycopy(a6, 0, arr, (h * 5), h);
        System.arraycopy(a7, 0, arr, (h * 6), h);
        System.arraycopy(a8, 0, arr, (h * 7), h);
        System.arraycopy(a9, 0, arr, (h * 8), h);
        System.arraycopy(a10, 0, arr, (h * 9), h);

        System.out.println(System.currentTimeMillis() - a);


    }

}
