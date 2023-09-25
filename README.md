# redis-api-sample

* Redis에 데이터를 삽입/조회하는 API서비스입니다.

## Library & Framework
| No  | Item |
|---|---|
| 1   |spring-data-redis|
| 2   |lettuce-core|

## Lettuce

* `me.lkh.redisapitest.config.RedisConfig`
> RedisTemplate을 위한 설정

* Lettuce를 사용하여 구현했습니다.
* Jedis보다 Lettuce가 TPS/CPU 등 전분야에서 더 좋습니다.

`참고: https://jojoldu.tistory.com/418`



