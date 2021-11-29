- springboot启动之后有两种方式实现执行别的特定代码
  - 实现`ApplicationRunner`接口
  - 实现`CommandLineRunner`接口
这里我们使用第一个实现`kafka`的消息生产，使用定时任务线程池`newScheduledThreadPool`实现周期性的生产一个消息
- 