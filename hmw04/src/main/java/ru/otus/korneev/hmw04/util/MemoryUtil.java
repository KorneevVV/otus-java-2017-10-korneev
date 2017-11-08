package ru.otus.korneev.hmw04.util;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.*;
import java.util.HashMap;
import java.util.Map;

public class MemoryUtil {
    private static final int NORM_NAME_LENGTH = 25;
    private static final long SIZE_KB = 1024;
    private static final long SIZE_MB = SIZE_KB * 1024;
    private static final long SIZE_GB = SIZE_MB * 1024;
    private static final String SPACES = "                    ";
    private static Map<String, MemRegion> memRegions;
    private static Map<String, InfoAboutGC> info;

    static {
        // Запоминаем информацию обо всех регионах памяти
        memRegions = new HashMap<>(ManagementFactory.getMemoryPoolMXBeans().size());
        for (MemoryPoolMXBean mBean : ManagementFactory.getMemoryPoolMXBeans()) {
            memRegions.put(mBean.getName(), new MemRegion(mBean.getName(), mBean.getType() == MemoryType.HEAP));
        }
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
                inf.summTime(gcInfo.getGcInfo().getDuration());
            } else if (gcInfo.getGcAction().equals("end of major GC")) {
                InfoAboutGC inf = info.get("Old GC");
                inf.incrementCount();
                inf.summTime(gcInfo.getGcInfo().getDuration());
            }
        }
    };

    /**
     * Выводит в stdout информацию о текущем состоянии различных разделов памяти.
     */
    public static void printUsage(boolean heapOnly) {
        for (MemoryPoolMXBean mBean : ManagementFactory.getMemoryPoolMXBeans()) {
            if (!heapOnly || mBean.getType() == MemoryType.HEAP) {
                printMemUsage(mBean.getName(), mBean.getUsage());
            }
        }
    }

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

    /**
     * Останавливает процесс мониторинга сборок мусора.
     */
    public static void stopGCMonitor() {
        for (GarbageCollectorMXBean mBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            try {
                ((NotificationEmitter) mBean).removeNotificationListener(gcHandler);
            } catch (ListenerNotFoundException e) {
            }
        }
    }

    private static void printMemUsage(String title, MemoryUsage usage) {
        System.out.println(String.format("%s%s\t%.1f%%\t[%s]",
                memRegions.get(title).getNormName(),
                formatMemory(usage.getUsed()),
                usage.getMax() < 0 ? 0.0 : (double) usage.getUsed() / (double) usage.getMax() * 100,
                formatMemory(usage.getMax())));
    }

    private static String formatMemory(long bytes) {
        if (bytes > SIZE_GB) {
            return String.format("%.2fG", bytes / (double) SIZE_GB);
        } else if (bytes > SIZE_MB) {
            return String.format("%.2fM", bytes / (double) SIZE_MB);
        } else if (bytes > SIZE_KB) {
            return String.format("%.2fK", bytes / (double) SIZE_KB);
        }
        return Long.toString(bytes);
    }

    private static void appendMemUsage(StringBuilder sb, Map<String, MemoryUsage> memUsage) {
        for (Map.Entry<String, MemoryUsage> entry : memUsage.entrySet()) {
            if (memRegions.get(entry.getKey()).isHeap()) {
                sb.append(entry.getKey()).append(" used=")
                        .append(entry.getValue().getUsed() >> 10)
                        .append("K; ");
            }
        }
    }

    public static void printResult() {
        System.out.println(info.get("Young GC"));
        System.out.println(info.get("Old GC"));
    }

    // Вспомогательный класс для хранения информации о регионах памяти
    private static class MemRegion {
        private boolean heap;        // Признак того, что это регион кучи
        private String normName;    // Имя, доведенное пробелами до универсальной длины

        public MemRegion(String name, boolean heap) {
            this.heap = heap;
            normName = name.length() < NORM_NAME_LENGTH ? name.concat(SPACES.substring(0, NORM_NAME_LENGTH - name.length())) : name;
        }

        public boolean isHeap() {
            return heap;
        }

        public String getNormName() {
            return normName;
        }
    }
}
