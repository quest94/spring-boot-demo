package org.quest94.demo.composites.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quest94.demo.composites.rabbit.Constants.*;

/**
 * <P>ClassName: RabbitMQConfig
 * <P>Description: RabbitMQ 配置
 *
 * @author quec1994
 * @version V1.0, 2019/3/1
 **/
@Configuration
public class RabbitConfig {

    /**
     * 创建普通userQueue队列
     *
     * @return userQueue队列
     * @author V1.0, quec1994, 2019/3/1 14:10
     **/
    @Bean
    public Queue userQueue() {
        return new Queue(SAVE_USER_QUEUE_NAME);
    }

    //===============以下是验证 Directed Exchange（直连交换机）的队列==========

    /**
     * 配置队列实例，并且设置持久化队列
     *
     * @return 队列实例
     * @author V1.0, qyz12, 2019/3/1o16:26
     **/
    @Bean
    public Queue queue() {
        return new Queue(DIRECT_MESSAGE_QUEUE_NAME, true);
    }
    //===============以上是验证 Directed Exchange（直连交换机）的队列==========

    /**
     * 配置直连交换机实例，路由键（route-key）方式分发消息
     *
     * @return 直连交换机实例
     * @author V1.0, qyz12, 2019/3/1 16:26
     **/
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_EXCHANGE_NAME);
    }

    /**
     * 将队列绑定到直连交换机上，并设置消息分发的路由键
     *
     * @return 绑定关系
     * @author V1.0, qyz12, 2019/3/1 16:26
     **/
    @Bean
    public Binding binding() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(queue()).to(directExchange()).with(DIRECT_MESSAGE_QUEUE_ROUTE_KEY);
    }

    //===============以下是验证 Topic Exchange 的队列==========

    /**
     * 创建验证topic Exchange的queueMessage队列
     *
     * @return queueMessage队列
     * @author V1.0, quec1994, 2019/3/1 14:10
     **/
    @Bean
    public Queue queueMessage() {
        return new Queue(TOPIC_MESSAGE_QUEUE_NAME);
    }

    /**
     * 创建验证topic Exchange的queueMessages队列
     *
     * @return queueMessages队列
     * @author V1.0, quec1994, 2019/3/1 14:10
     **/
    @Bean
    public Queue queueMessages() {
        return new Queue(TOPIC_MESSAGES_QUEUE_NAME);
    }
    //===============以上是验证 Topic Exchange 的队列==========

    /**
     * 创建通配符交换机，通配符方式分发消息。
     * 主要有两种通配符：# 和 *
     * *（星号）：可以（只能）匹配一个单词
     * #（井号）：可以匹配多个单词（或者零个）
     *
     * @return exchange通配符交换机
     * @author V1.0, quec1994, 2019/3/1 14:10
     **/
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_EXCHANGE_NAME);
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     *
     * @return 绑定关系
     */
    @Bean
    Binding bindingExchangeMessage() {
        // 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(queueMessage()).to(topicExchange()).with(TOPIC_MESSAGE_QUEUE_ROUTE_KEY);
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     *
     * @return 绑定关系
     */
    @Bean
    Binding bindingExchangeMessages() {
        return BindingBuilder.bind(queueMessages()).to(topicExchange()).with(TOPIC_ALL_QUEUE_ROUTE_KEY);
    }

    //===============以下是验证 Fanout Exchange 的队列==========

    /**
     * 创建验证Fanout Exchange的aMessage队列
     *
     * @return aMessage队列
     * @author V1.0, quec1994, 2019/3/1 14:10
     **/
    @Bean
    public Queue aMessage() {
        return new Queue(FANOUT_A_QUEUE_NAME);
    }

    /**
     * 创建验证Fanout Exchange的bMessage队列
     *
     * @return bMessage队列
     * @author V1.0, quec1994, 2019/3/1 14:10
     **/
    @Bean
    public Queue bMessage() {
        return new Queue(FANOUT_B_QUEUE_NAME);
    }

    /**
     * 创建验证Fanout Exchange的cMessage队列
     *
     * @return cMessage队列
     * @author V1.0, quec1994, 2019/3/1 14:10
     **/
    @Bean
    public Queue cMessage() {
        return new Queue(FANOUT_C_QUEUE_NAME);
    }
    //===============以上是验证 Fanout Exchange 的队列==========

    /**
     * 创建扇形交换机，这种交换机会把消息发送到所有binding到该交换机上的queue。
     * 这种是publisher/subcribe(发布/订阅)模式。用来做广播最好。
     * 所有该exchagne上指定的routing-key都会被ignore掉。(原因：因为route-key无效)
     *
     * @return 扇形交换机
     * @author V1.0, quec1994, 2019/3/1 14:10
     **/
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_EXCHANGE_NAME);
    }

    /**
     * 将队列fanout.A队列与扇形交换机绑定
     *
     * @return 绑定关系
     * @author V1.0, quec1994, 2019/3/1 14:10
     */
    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(aMessage()).to(fanoutExchange());
    }

    /**
     * 将队列fanout.B队列与扇形交换机绑定
     *
     * @return 绑定关系
     * @author V1.0, quec1994, 2019/3/1 14:10
     */
    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(bMessage()).to(fanoutExchange());
    }

    /**
     * 将队列fanout.C队列与扇形交换机绑定
     *
     * @return 绑定关系
     * @author V1.0, quec1994, 2019/3/1 14:10
     */
    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(cMessage()).to(fanoutExchange());
    }

}
