package kyu4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TCPTest {

    @Test
    void traverseStates() {
        assertEquals("FIN_WAIT_1",   TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN", "RCV_SYN","RCV_ACK", "APP_CLOSE"}));
        assertEquals("ESTABLISHED",  TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN", "RCV_SYN","RCV_ACK"}));
        assertEquals("SYN_RCVD",     TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN", "RCV_SYN"}));
        assertEquals("LISTEN",       TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN"}));
        assertEquals("CLOSED",       TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","APP_CLOSE"}));
        assertEquals("TIME_WAIT",    TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN","APP_CLOSE","RCV_FIN","RCV_ACK"}));
        assertEquals("CLOSED",       TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN","APP_CLOSE","RCV_FIN","RCV_ACK","APP_TIMEOUT"}));
        assertEquals("FIN_WAIT_2",   TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN","APP_CLOSE","RCV_ACK"}));
        assertEquals("CLOSE_WAIT",   TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN"}));
        assertEquals("LAST_ACK",     TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","RCV_FIN","APP_CLOSE"}));
        assertEquals("SYN_SENT",     TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN"}));
    }


    @Test
    void traverseStates1() {

        assertEquals("ERROR",        TCP.traverseStates(new String[] {"RCV_SYN","RCV_ACK","APP_CLOSE"}));


    }

    @Test
    void traverseStates3() {
        assertEquals("CLOSED",       TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","APP_CLOSE"}));
        assertEquals("FIN_WAIT_1",   TCP.traverseStates(new String[] {"APP_ACTIVE_OPEN","RCV_SYN_ACK","APP_CLOSE"}));
        assertEquals("ERROR",        TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_PASSIVE_OPEN"}));
        assertEquals("TIME_WAIT",    TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_CLOSE","RCV_FIN_ACK","APP_TIMEOUT","APP_ACTIVE_OPEN","RCV_SYN","APP_CLOSE","RCV_FIN","RCV_ACK"}));
        assertEquals("ERROR",        TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_CLOSE","RCV_SYN"}));
        assertEquals("ERROR",        TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","APP_CLOSE","RCV_SYN"}));
        assertEquals("FIN_WAIT_1",   TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_CLOSE"}));
        assertEquals("CLOSING",      TCP.traverseStates(new String[] {"APP_PASSIVE_OPEN","RCV_SYN","RCV_ACK","APP_CLOSE","RCV_FIN"}));

    }


}