package cn.shuai.tissot.dao.cache;

import cn.shuai.tissot.entity.Activity;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

// 生成测试快捷键 ctrl+shift+t
public class RedisDao {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    private RuntimeSchema<Activity> schema = RuntimeSchema.createFrom(Activity.class);

    public RedisDao(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisDao(String host, int port) {
        this.jedisPool = new JedisPool(host,port);
    }

    public Activity getSeckill(long id){
        // redis 操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:"+id;
                // 自定义序列化(protostuff)
                // get 到 bytes[]，再反序列化
                byte[] bytes = jedis.get(key.getBytes());
                if(bytes != null){
                    Activity activity = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,activity,schema);
                    return activity;
                }

            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putSeckill(Activity activity){
        // Object 序列化成 byte[]
        try{
            Jedis jedis = jedisPool.getResource();
            try{
                String key="seckill:"+activity.getActivityId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(activity,schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 超时缓存
                int timeout = 60 * 60; // 单位 s
                String result = jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
