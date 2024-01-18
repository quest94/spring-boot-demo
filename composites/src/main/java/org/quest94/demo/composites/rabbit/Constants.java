package org.quest94.demo.composites.rabbit;

/**
 * <P>ClassName: Constants
 * <P>Description: 用于保存RabbitMQ的queue、exchange、route-key标识
 *
 * @author quest94
 * @version V1.0, 2019/3/1
 **/
public class Constants {

    /**
     * 队列名称
     */
    public static final String SAVE_USER_QUEUE_NAME = "user.save.queue.name";
    public static final String DIRECT_MESSAGE_QUEUE_NAME = "direct.message.queue.name";
    public static final String TOPIC_MESSAGE_QUEUE_NAME = "topic.message.queue.name";
    public static final String TOPIC_MESSAGES_QUEUE_NAME = "topic.messages.queue.name";
    public static final String FANOUT_A_QUEUE_NAME = "fanout.A.queue.name";
    public static final String FANOUT_B_QUEUE_NAME = "fanout.B.queue.name";
    public static final String FANOUT_C_QUEUE_NAME = "fanout.C.queue.name";

    /**
     * 交换机名称
     */
    public static final String DIRECT_EXCHANGE_EXCHANGE_NAME = "direct.exchange.exchange.name";
    public static final String TOPIC_EXCHANGE_EXCHANGE_NAME = "topic.exchange.exchange.name";
    public static final String FANOUT_EXCHANGE_EXCHANGE_NAME = "fanout.exchange.exchange.name";


    /**
     * 队列路由键
     */
    public static final String DIRECT_MESSAGE_QUEUE_ROUTE_KEY = "direct.message.queue.route.key";
    public static final String TOPIC_MESSAGE_QUEUE_ROUTE_KEY = "topic.message.route.key";
    public static final String TOPIC_MESSAGES_QUEUE_ROUTE_KEY = "topic.messages.route.key";
    public static final String TOPIC_TEST_QUEUE_ROUTE_KEY = "topic.test.route.key";
    public static final String TOPIC_ALL_QUEUE_ROUTE_KEY = "topic.#";

}
