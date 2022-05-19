package mao.demo.mq.kafaka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * kafka生产者示例
 */
public class ProducerCase {
    private Properties props;
    private Producer producer;

    @Before
    public void before() {
        props = new Properties();
        props.put("bootstrap.servers", "10.49.196.10:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");//所有follower都响应了才认为消息提交成功，即"committed"
        //props.put("retries", "10");//连接失败重试次数
        //props.put("batch.size", "16384");//每个分区缓冲区大小，当数据大小达到设定值后，就会立即发送，不顾下面的linger.ms
        //props.put("linger.ms", "1");//producer将会等待给定的延迟时间以允许其他消息记录一起发送，默认为0
        //props.put("buffer.memory", "33554432");//producer缓冲区大小
        //props.put("max.block.ms", "60000");//当缓冲区满了，发送消息时最大等待时间
    }

    @After
    public void after() throws Exception {
        producer.close();
    }

    /**
     * 简单使用
     */
    @Test
    public void simpleUse() throws Exception {
        producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            //发送key和value
            producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), "消息-" + i));
            //只发送value
            producer.send(new ProducerRecord<String, String>("test", "消息2-" + i));
        }
    }

    /**
     * 以事务方式发送消息
     * @throws Exception
     */
    @Test
    public void transactional() throws Exception {
        props.put("transactional.id", "tid123");//必须设置，不同的生产者需要设置不同的事务id
        producer = new KafkaProducer<>(props);

        producer.initTransactions();
        try {
            producer.beginTransaction();
            for (int i = 0; i < 10; i++) {
                //发送key和value
                producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), "消息-" + i));
                //只发送value
                producer.send(new ProducerRecord<String, String>("test", "消息2-" + i));
            }

            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            // We can't recover from these exceptions, so our only option is to close the producer and exit.
            producer.close();
        } catch (KafkaException e) {
            // For all other exceptions, just abort the transaction and try again.
            producer.abortTransaction();
        }
    }

}