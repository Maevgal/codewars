package kyu4;

import java.util.HashMap;
import java.util.Map;

public class TCP {
    /*https://www.codewars.com/kata/54acc128329e634e9a000362/java*/
    final static private String START = "CLOSED", ERROR = "ERROR";

    final static private Map<String, Map<String, String>> STATES = new HashMap<>() {{
        put("CLOSED", Map.of("APP_PASSIVE_OPEN", "LISTEN", "APP_ACTIVE_OPEN", "SYN_SENT"));
        put("LISTEN", Map.of("RCV_SYN", "SYN_RCVD", "APP_SEND", "SYN_SENT", "APP_CLOSE", "CLOSED"));
        put("SYN_RCVD", Map.of("APP_CLOSE", "FIN_WAIT_1", "RCV_ACK", "ESTABLISHED"));
        put("SYN_SENT", Map.of("RCV_SYN", "SYN_RCVD", "RCV_SYN_ACK", "ESTABLISHED", "APP_CLOSE", "CLOSED"));
        put("ESTABLISHED", Map.of("APP_CLOSE", "FIN_WAIT_1", "RCV_FIN", "CLOSE_WAIT"));
        put("FIN_WAIT_1", Map.of("RCV_FIN", "CLOSING", "RCV_FIN_ACK", "TIME_WAIT", "RCV_ACK", "FIN_WAIT_2"));
        put("CLOSING", Map.of("RCV_ACK", "TIME_WAIT"));
        put("FIN_WAIT_2", Map.of("RCV_FIN", "TIME_WAIT"));
        put("TIME_WAIT", Map.of("APP_TIMEOUT", "CLOSED"));
        put("CLOSE_WAIT", Map.of("APP_CLOSE", "LAST_ACK"));
        put("LAST_ACK", Map.of("RCV_ACK", "CLOSED"));
    }};

    public static String traverseStates(String[] events) {
        String state = START;
        for (String e : events) {
            state = STATES.get(state).get(e);
            if (state == null) return ERROR;
        }
        return state;
    }
}
