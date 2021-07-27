MySQL 中有六种日志文件，分别是：

重做日志（redo log）、回滚日志（undo log）、二进制日志（binlog）、错误日志（errorlog）、慢查询日志（slow query log）、一般查询日志（general log），中继日志（relay log）。



### binglog

最早接触 binlog 是做数据库主从同步的时候，知道是通过同步 binlog 实现的。binlog 是 没有 MySQL sever 层维护的一种二进制日志，与 innodb 引擎中的 redo/undo log 是完全不同的日志。其主要是用来记录对 MySQL 数据更新或潜在发生更新的 SQL 语句，并以 “事务”的形式保存在磁盘中。

binlog 主要有以下作用：

- 复制：MySQL 主从复制在 Master 端开启 binlog，Master 把它的二进制日志传递给 slaves 并回放来达到 master-slave 数据一致的目的
- 数据恢复：通过 mysqlbinlog 工具恢复数据
- 增量备份

#### 几个知识点：

- #### binlog 不会记录不修改数据的语句，比如`Select`或者`Show`

- binlog 会重写日志中的密码，保证不以纯文本的形式出现

- MySQL 8 之后的版本可以选择对 binlog 进行加密

- 具体的写入时间：在事务提交的时候，数据库会把 binlog cache 写入 binlog 文件中，但并没有执行`fsync()`操作，即只将文件内容写入到 OS 缓存中。随后根据配置判断是否执行 fsync。

- 删除时间：保持时间由参数`expire_logs_days`配置，也就是说对于非活动的日志文件，在生成时间超过`expire_logs_days`配置的天数之后，会被自动删除。

### 主从复制

复制是 MySQL 最重要的功能之一，MySQL 集群的高可用、负载均衡和读写分离都是基于复制来实现。复制步骤如下：

1. Master 将数据改变记录到二进制日志(binary log)中。
2. Slave 上面的 IO 进程连接上 Master，并请求从指定日志文件的指定位置（或者从最开始的日志）之后的日志内容。
3. Master 接收到来自 Slave 的 IO 进程的请求后，负责复制的 IO 进程会根据请求信息读取日志指定位置之后的日志信息，返回给 Slave 的 IO 进程。返回信息中除了日志所包含的信息之外，还包括本次返回的信息已经到 Master 端的 binlog 文件的名称以及 binlog 的位置。
4. Slave 的 IO 进程接收到信息后，将接收到的日志内容依次添加到 Slave 端的 relaylog 文件的最末端，并将读取到的 Master 端的 binlog 的文件名和位置记录到 masterinfo 文件中，以便在下一次读取的时候能够清楚的告诉 Master 从某个 binlog 的哪个位置开始往后的日志内容
5. Slave 的 SQL 进程检测到 relaylog 中新增加了内容后，会马上解析 relaylog 的内容成为在 Master 端真实执行时候的那些可执行的内容，并在自身执行。



## undo log 和 redo log

undo log 和 redo log 其实都不是 MySQL 数据库层面的日志，而是 InnoDB 存储引擎的日志。二者的作用联系紧密，事务的隔离性由锁来实现，原子性、一致性、持久性通过数据库的 redo log 或 undo log 来完成。redo log 又称为重做日志，用来保证事务的持久性，undo log 用来保证事务的原子性和 MVCC。

### redo log

### 功能

和大多数关系型数据库一样，InnoDB 记录了对数据文件的物理更改，并保证总是日志先行，也就是所谓的 WAL，即在持久化数据文件前，保证之前的 redo 日志已经写到磁盘。由于 redo log 是顺序整块写入，所以性能要更好。

重做日志两部分组成：一是内存中的重做日志缓冲(redo log buffer)，是易失的；二是重做日志文件(redo log file)，是持久的。redo log 记录事务操作的变化，记录的是数据修改之后的值，不管事务是否提交都会记录下来。



### 写入过程

在一条语句进行执行的时候，InnoDB 引擎会把新记录写到 redo log 日志中，然后更新内存，更新完成后就算是语句执行完了，然后在空闲的时候或者是按照设定的更新策略将 redo log 中的内容更新到磁盘中。

更详细的步骤，需要了解两个关键词：checkpoint 和 LSN(Log Sequence Number)，前者检查点简单来说就是把脏页刷到磁盘的时间点，这个时间点之前的数据都已经保存到了持久存储。而 LSN 是 InnoDB 使用的一个版本标记的计数，它是一个单调递增的值。数据页和 redo log 都有各自的 LSN。每次把 redo log 中的内容写入到实际的数据页之后，就会把 LSN 也同步过去。如果发生了宕机，我们可以根据数据页中的 LSN 值和 redo log 中 LSN 的值判断需要恢复的 redo log 的位置和大小。redo log 同样也有自己的缓存，所以也涉及到刷盘策略，是通过`innodb_flush_log_at_trx_commit`这个参数控制的。

当对应事务的脏页写入到磁盘之后，redo log 的使命也就完成了，重做日志占用的空间就可以重用（被覆盖）。



### undo log

undo log 有两个作用：提供回滚和多版本并发控制下的读(MVCC)，也即非锁定读

在数据修改的时候，不仅记录了redo，还记录了相对应的 undo，如果因为某些原因导致事务失败或回滚了，可以借助该 undo 进行回滚。

undo log 和 redo log 记录物理日志不一样，它是逻辑日志。可以认为当 delete 一条记录时，undo log 中会记录一条对应的 insert 记录，反之亦然，当 update 一条记录时，它记录一条对应相反的 update 记录。

有时候应用到行版本控制的时候，也是通过 undo log 来实现的：当读取的某一行被其他事务锁定时，它可以从 undo log 中分析出该行记录以前的数据是什么，从而提供该行版本信息，让用户实现非锁定一致性读取。

undo log 是采用段(segment)的方式来记录的，每个 undo 操作在记录的时候占用一个 undo log segment**。**

另外，undo log 也会产生 redo log，因为 undo log 也要实现持久性保护。

当事务提交的时候，InnoDB 不会立即删除 undo log，因为后续还可能会用到 undo log，如隔离级别为 repeatable read 时，事务读取的都是开启事务时的最新提交行版本，只要该事务不结束，该行版本就不能删除，即 undo log 不能删除。

当事务提交之后，undo log 并不能立马被删除，而是放入待清理的链表，由 purge 线程判断是否有其他事务在使用 undo 段中表的上一个事务之前的版本信息，决定是否可以清理 undo log 的日志空间。

在 MySQL 5.7 之前，undo log 存储在共享表空间中，因此有可能大大增加表空间的占用，5.7 之后可以通过配置选择存储在独立的表空间中。



### 三种日志总结

首先 InnoDB 完成一次更新操作的具体步骤：

1. 开启事务
2. 查询待更新的记录到内存，并加 X 锁
3. 记录 undo log 到内存 buffer
4. 记录 redo log 到内存 buffer
5. 更改内存中的数据记录
6. 提交事务，触发 redo log 刷盘
7. 记录 bin log
8. 事务结束

## 慢查询日志

几个配置参数：

- slow_query_log 慢查询开启状态
- slow_query_log_file 慢查询日志存放的位置（这个目录需要 MySQL 的运行帐号的可写权限，一般设置为 MySQL 的数据存放目录）
- long_query_time 查询超过多少秒才记录
- log_queries_not_using_indexes：未使用索引的查询也被记录到慢查询日志中（可选项）

修改参数可以通过配置文件，也可以在数据库中通过`SET`关键字来设置。

