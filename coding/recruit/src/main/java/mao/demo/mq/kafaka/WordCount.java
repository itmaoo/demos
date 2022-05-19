package mao.demo.mq.kafaka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * 单词统计
 * 程序从streams-plaintext-input中读取消息，并把每条消息拆成单词，并统计这些单词的数量；把统计信息发送到streams-wordcount-output，可用如下命令查看主题输出情况：
 * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-wordcount-output --value-deserializer org.apache.kafka.common.serialization.LongDeserializer --property print.key=true
 */
public class WordCount {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "10.49.196.10:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        final StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> source = builder.stream("streams-plaintext-input");
        source.flatMapValues(value -> Arrays.asList(value.toLowerCase(Locale.getDefault()).split("\\W+")))//把每条消息拆成一个个单词
                .groupBy((key, value) -> value)//根据单词分组
                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"))//计算每个单词的个数并保存在名为"counts-store"的KeyValueStore中
                .toStream()
                .to("streams-wordcount-output", Produced.with(Serdes.String(), Serdes.Long()));//设置输出类型，键为String，值为long
        final Topology topology = builder.build();
        System.out.println(topology.describe());
        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);

        streams.start();
        latch.await();
    }
}