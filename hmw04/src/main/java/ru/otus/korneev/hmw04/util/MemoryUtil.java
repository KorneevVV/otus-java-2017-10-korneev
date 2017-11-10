package ru.otus.korneev.hmw04.util;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.*;
import java.util.HashMap;
import java.util.Map;

public class MemoryUtil {
	private static Map<String, InfoAboutGC> info;

	static {
		info = new HashMap<>();
		info.put("Young GC", new InfoAboutGC());
		info.put("Old GC", new InfoAboutGC());
	}

	// Обработчик сообщений о сборке мусора
	private static NotificationListener gcHandler = (notification, handback) -> {
		if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
			GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
			if (gcInfo.getGcAction().equals("end of minor GC")) {
				InfoAboutGC inf = info.get("Young GC");
				inf.incrementCount();
				inf.sumTime(gcInfo.getGcInfo().getDuration());
			} else if (gcInfo.getGcAction().equals("end of major GC")) {
				InfoAboutGC inf = info.get("Old GC");
				inf.incrementCount();
				inf.sumTime(gcInfo.getGcInfo().getDuration());
			}
		}
	};

	/**
	 * Запускает процесс мониторинга сборок мусора.
	 */
	public static void startGCMonitor() {
		for (GarbageCollectorMXBean mBean : ManagementFactory.getGarbageCollectorMXBeans()) {
			switch (mBean.getName()) {
				case "Copy":
				case "PS Scavenge":
				case "ParNew":
				case "G1 Young Generation":
					info.get("Young GC").setNameGC(mBean.getName());
					break;
				case "MarkSweepCompact":
				case "PS MarkSweep":
				case "ConcurrentMarkSweep":
				case "G1 Old Generation":
					info.get("Old GC").setNameGC(mBean.getName());
					break;
			}
			((NotificationEmitter) mBean).addNotificationListener(gcHandler, null, null);
		}
	}

	public static void printResult() {
		System.out.println(info.get("Young GC"));
		System.out.println(info.get("Old GC"));
	}
}
