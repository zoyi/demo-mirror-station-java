package com.zoyi;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

/**
 * Created by huy on 15. 9. 17..
 */
public class ApplicationHelper {
  public static final String API_URL = "https://station5.walkinsights.com";
  public static final String SHOP_MONITORING_PATH = "api/shops/monitoring";
  public static final String SQUARE_MONITORING_PATH = "api/squares/monitoring";
  public static final String SHOP_MIRRORING_PATH = "api/shops/signals";
  public static final String SENSOR_PATH = "sensor.io";
  public static final String SIGNAL_EVENT = "signal";

  public static String getShopMonitoringURL () {
    return String.format("%s/%s", API_URL, SHOP_MONITORING_PATH);
  }

  public static String getSquareMonitoringURL () {
    return String.format("%s/%s", API_URL, SQUARE_MONITORING_PATH);
  }

  public static String getShopMirroingURL () {
    return String.format("%s/%s", API_URL, SHOP_MIRRORING_PATH);
  }

  public static String getSensorPath () {
    return String.format("/%s", SENSOR_PATH);
  }

  public static Socket defaultSocket(String url, IO.Options opts) throws URISyntaxException {
    final Socket socket;
    socket = IO.socket(getShopMonitoringURL(), opts);

    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
      public void call(Object... args) {
        System.out.println("connected");
      }
    }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
      public void call(Object... args) {
        System.out.println("fail to connect");
      }
    }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
      public void call(Object... args) {
        System.out.println("error");
        System.out.println(args[0]);
        socket.close();
      }
    }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
      public void call(Object... args) {
        System.out.println("disconnected");
      }
    });

    return socket;
  }
}
