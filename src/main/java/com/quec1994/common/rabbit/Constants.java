package com.quec1994.common.rabbit;

/**
 * <P>ClassName: Constants</P>
 * <P>Description: 用于保存RabbitMQ的queue、exchange、route-key标识</P>
 *
 * @author quec1994
 * @version V1.0, 2019/3/1
 **/
public class Constants {

    /**
     * 保存用户-队列名称
     */
    public static final String SAVE_USER_QUEUE_NAME = "user.save.queue.name";
    public static final String DIRECT_MESSAGE_QUEUE_NAME = "direct.message.queue.name";
    public static final String TOPIC_MESSAGE_QUEUE_NAME = "topic.message.queue.name";
    public static final String TOPIC_MESSAGES_QUEUE_NAME = "topic.messages.queue.name";

    /**
     * 保存用户-交换机名称
     */
    public static final String DIRECT_EXCHANGE_EXCHANGE_NAME = "direct.exchange.exchange.name";
    public static final String TOPIC_EXCHANGE_EXCHANGE_NAME = "topic.exchange.exchange.name";


    /**
     * 保存用户-队列路由键
     */
    public static final String DIRECT_MESSAGE_QUEUE_ROUTE_KEY = "direct.message.queue.route.key";
    public static final String TOPIC_MESSAGE_QUEUE_ROUTE_KEY = "topic.message.route.key";
    public static final String TOPIC_ALL_QUEUE_ROUTE_KEY = "topic.#.route.key";

}
