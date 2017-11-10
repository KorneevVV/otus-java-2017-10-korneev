package ru.otus.korneev.hmw04;

import ru.otus.korneev.hmw04.util.MemoryUtil;

import java.util.LinkedList;

public class Main {
	private static final int NUMBER_OF_REPEAT = 180; // is equal to the work program ~ 4:30
	private static final int NUMBER_OF_ELEMENTS = 1000;

	public static void main(String[] args) {
		Thread myThread = new Thread() {
			private int count = 0;
			private LinkedList<Object> objects = new LinkedList<>();

			@Override
			public void run() {
				MemoryUtil.startGCMonitor();
				long startTime = System.currentTimeMillis();
				while (count != NUMBER_OF_REPEAT) {
					for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
						objects.add(new byte[5 * 1024]);
						if (i % 2 == 0) {
							objects.removeFirst();
						}
					}
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count++;
				}
				MemoryUtil.printResult();
				System.out.println("The duration of the measurement: " + (System.currentTimeMillis() - startTime) + " ms. \n");
			}
		};
		myThread.start();
	}
}
