package com.mm.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description WebSocket核心类
 * @Author MKC
 * @Date 2022/1/12
 */
@Slf4j
@ServerEndpoint("/webSocket/{sid}")
@Component
public class WebSocketServer {
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static AtomicInteger onlineNum = new AtomicInteger();
    //private static ThreadLocal<Integer> num = new ThreadLocal<>();

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
     */
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    /**
     * 发送消息
     * @param session session
     * @param message 消息
     * @throws IOException 异常
     */
    public void sendMessage(Session session, String message) throws IOException {
        if (session != null) {
            synchronized (session) {
                log.info("发送数据：{}", message);
                session.getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 给指定用户发送信息
     * @param userName 用户名
     * @param message 消息
     */
    public void sendInfo(String userName, String message) {
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立连接成功调用
     * @param session session
     * @param userName 用户名
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName) {
        sessionPools.put(userName, session);
        addOnlineCount();
        log.info("{}加入webSocket！当前人数为{}", userName, onlineNum);
        try {
            sendMessage(session, "欢迎" + userName + "加入连接！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接时调用
     * @param userName 用户名
     */
    @OnClose
    public void onClose(@PathParam(value = "sid") String userName) {
        sessionPools.remove(userName);
        subOnlineCount();
        log.info("{}断开webSocket连接！当前人数为{}", userName, onlineNum);
    }

    /**
     * 收到客户端信息
     * @param message 消息
     * @throws IOException 异常
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        message = "客户端：" + message + ",已收到";
        log.info("收到客户端消息：{}",message);
        for (Session session : sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 错误时调用
     * @param session session
     * @param throwable throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("发生错误");
        throwable.printStackTrace();
    }

    public static void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }
}
