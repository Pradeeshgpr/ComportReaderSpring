package com.artwiz.comport.reader.service;

import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

import javax.comm.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

@Service
public class ComportService {

    private Map<Long, Reader> readers = new HashMap<>();

    public long init(long baudRate)  {
        try {
            readers.put(baudRate, new Reader().init(baudRate));
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } finally {
          return -1;
        }
    }
    
    public void close(long reader) {
        readers.computeIfPresent(reader, (k,v) -> v.close());
        readers.remove(reader);
    }

    public String getValue(long reader) {
        if (readers.containsKey(reader)) {
            return readers.get(reader).getValue();
        } else {
            return "Unable to Read the content";
        }
    }

    class Reader implements SerialPortEventListener {

        private StringBuffer value = new StringBuffer();
        private SerialPort serialPort;
        private BufferedReader reader;

        public Reader close() {
            return null;
        }

        public String getValue() {
            String tmp = value.toString();
            value.delete(0,tmp.length());
            return tmp;
        }

        public void addValue(String val) {
            value.append(val);
        }

        public Reader init(long baudRate) throws NoSuchPortException, PortInUseException, TooManyListenersException, IOException, UnsupportedCommOperationException {
            Enumeration pList = CommPortIdentifier.getPortIdentifiers();
            System.out.println("Comport list");
            while(pList.hasMoreElements()) {
                CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
                System.out.println(cpi.getPortType() + " " + cpi.getName());
                /*final CommPortIdentifier cpi = CommPortIdentifier.getPortIdentifiers().;
                final CommPort commPort = cpi.open(getClass().getName(), 1000);
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams((int) baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                serialPort.addEventListener(this);
                reader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));*/
            }
            return this;
        }

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                String val = reader.readLine();
                if (val != null) {
                    addValue(val);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
