package com.hyxiao.scanner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InvokerTable {

    //  第一个map是module，第二个map是 cmd
    public static ConcurrentHashMap<String, Map<String, Invoker>> invokerTable = new ConcurrentHashMap<>();

    public static void addInvoker(String module, String cmd, Invoker invoker) {
//        Map<String, Invoker> map = invokerTable.get(module);
//        if (map == null) {
//            map = new ConcurrentHashMap<>();
//            invokerTable.put(module, map);
//        }
        Map<String, Invoker> map = invokerTable.computeIfAbsent(module, k -> new ConcurrentHashMap<>());
        map.put(cmd, invoker);
    }

    public static Invoker getInvoker(String module, String cmd) {
        Map<String, Invoker> map = invokerTable.get(module);
        if (map != null) {
            return map.get(cmd);
        }
        return null;
    }


}
