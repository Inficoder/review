# **mysql 引擎**

- InnoDB

  ```
  1.支持事务
  2.行锁
  3.外键
  4.MySQL的默认引擎
  ```

- MyISAM

  ```
  1.不支持事务
  2.插入查询速度较快
  ```

# **事务隔离级别**

- 事务
  - 原子性（Atomicity ）：事务中的所有操作要么全部成功，要么全部失败
  - 一致性（Consistency ）：多个操作完成后总数值一致
  - 隔离性（Isolation ）：多个事务之间互相不干扰
  - 持续性（Durability ）：事务执行后的改变是永久的

### Read Uncommitted（读取未提交内容）

```
在该隔离级别，所有事务都可以看到其他未提交事务的执行结果。本隔离级别很少用于实际应用，因为它的性能也不比其他级别好多少。读取未提交的数据，也被称之为脏读（Dirty Read）。
```

### Read Committed（读取提交内容）

```
这是大多数数据库系统的默认隔离级别（但不是MySQL默认的）。它满足了隔离的简单定义：一个事务只能看见已经提交事务所做的改变。这种隔离级别 也支持所谓的不可重复读（Nonrepeatable Read），因为同一事务的其他实例在该实例处理其间可能会有新的commit，所以同一select可能返回不同结果。
```

### Repeatable Read（可重读）

```
这是MySQL的默认事务隔离级别，它确保同一事务的多个实例在并发读取数据时，会看到同样的数据行。不过理论上，这会导致另一个棘手的问题：幻读 （Phantom Read）。简单的说，幻读指当用户读取某一范围的数据行时，另一个事务又在该范围内插入了新行，当用户再读取该范围的数据行时，会发现有新的“幻影” 行。InnoDB和Falcon存储引擎通过多版本并发控制（MVCC，Multiversion Concurrency Control）机制解决了该问题。
```

### Serializable（可串行化）

```
这是最高的隔离级别，它通过强制事务排序，使之不可能相互冲突，从而解决幻读问题。简言之，它是在每个读的数据行上加上共享锁。在这个级别，可能导致大量的超时现象和锁竞争。
这四种隔离级别采取不同的锁类型来实现，若读取的是同一个数据的话，就容易发生问题。

脏读(Drity Read)：某个事务已更新一份数据，另一个事务在此时读取了同一份数据，由于某些原因，前一个RollBack了操作，则后一个事务所读取的数据就会是不正确的。

不可重复读(Non-repeatable read):在一个事务的两次查询之中数据不一致，这可能是两次查询过程中间插入了一个事务更新的原有的数据。

幻读(Phantom Read):在一个事务的两次查询中数据笔数不一致，例如有一个事务查询了几列(Row)数据，而另一个事务却在此时插入了新的几列数据，先前的事务在接下来的查询中，就有几列数据是未查询出来的，如果此时插入和另外一个事务插入的数据，就会报错。

```

![img](https://pic1.zhimg.com/v2-5ec40a392d99638f295673c074dd9f50_b.jpg)

# **mysql 锁**

- ### MySQL InnoDB 的锁 和 MyISAM 的锁

  ```
  MyISAM只支持表锁，一锁就会锁整个表。而InnoDB不仅支持表锁，还支持粒度更小的行锁，仅对相关记录上锁，对于写入操作来说InnoDB性能更高。
  ```

- ### 锁的分类

  - shared locks（S锁）

    ```
    S锁，称为共享锁，事务在读取记录的时候获取 S 锁，它允许多个事务同时获取 S 锁，互相之间不会冲突。
    ```

  - exclusive locks (X锁)

    ```
    X锁，称为独占锁，事务在修改记录的时候获取 X 锁，且只允许一个事务获取 X 锁，其它事务需要阻塞等待。
    ```

    - ps1

    ```
    所以 S 锁之间不冲突，X 锁则为独占锁，所以 X 之间会冲突， X 和 S 也会冲突。不论是表级别锁还是行级别锁，S 和 X 的特性都是一样的。
    ```

    - ps2

    ```
    Q:事务在读取记录的时候需要获取 S 锁？这不对吧？
    
    A:确实不准确。普通的 select 是不需要加锁的，而 `SELECT ... LOCK IN SHARE MODE;` 这种读取需要对记录上  S 锁。`SELECT ... FOR UPDATE;` 需要对记录上 X 锁。
    ```

    - ps3

    ```
    针对InnoDB表使用行锁，被锁定字段不是主键，也没有针对它建立索引的话。行锁锁定的也是整张表。
    ```

  - **普通的 select 是不需要加锁的**，而 `SELECT ... LOCK IN SHARE MODE;` 这种读取需要对记录上 S 锁。`SELECT ... FOR UPDATE;` 需要对记录上 X 锁。

  - 表锁

    - `LOCK TABLES yes READ` 是对 yes 这个表上 S 锁。
    - `LOCK TABLES yes WRITE` 是对 yes 这个表上 X 锁。

  - 表锁很少用到

  ```
  update 、select 要用也是用行锁了，不可能用粒度粗的表锁。唯一能想到用上表锁的就是 DDL 语句了，比如 ALTER TABLE 的时候，应该锁定整个表，防止查询和修改，但是这个 server 已经提供了一个叫 MDL 的东西，即 Metadata Locks，所以已经用 MDL 来阻塞了，表锁也就排不上用场了。
  ```

  - 可以，但是如果真要到用表锁的时候，那表锁和行锁之间不是会冲突的吗？如果表里面已经加了行锁怎么办？得一条记录一条记录遍历过去找行锁吗？

  ```
  所以有了个叫意向锁（Intention Locks）的东西。
  
  1.IS（Intention Shared Lock），共享意向锁
  2.IX（Intention Exclusive Lock），独占意向锁。
  	
  这两个锁是表级别的锁，当需要对表中的某条记录上 S 锁的时候，先在表上加个 IS 锁，表明此时表内有 S 锁。当需要对表中的某条记录上 X 锁的时候，先在表上加个 IX 锁，表明此时表内有 X 锁。这样操作之后，如果要加表锁，就不需要遍历所有记录去找了，直接看看表上面有没有 IS 和 IX 锁。比如，此时要上表级别的 S 锁，如果表上没有 IX ，说明表中没有记录有独占锁，其实就可以直接上表级 S 锁。如果此时要上表级别的 X 锁，如果表上没有 IX 和 IS ，说明表中的所有记录都没加锁，其实就可以直接上表级 X 锁。因此 IS 和 IX 的作用就是在上表级锁的时候，可以快速判断是否可以上锁，而不需要遍历表中的所有记录。
  ```

  ## 行锁

  - 记录锁（Record Locks）

  ```
  记录锁(Record Locks)
  记录锁是 封锁记录，记录锁也叫行锁，例如：
  SELECT * FROM `test` WHERE `id`=1 FOR UPDATE;
  它会在 id=1 的记录上加上记录锁，以阻止其他事务插入，更新，删除 id=1 这一行。
  ```

  

  - 间隙锁（Gap Locks）

  ```
  间隙锁(Gap Locks)（重点）
  间隙锁是封锁索引记录中的间隔，或者第一条索引记录之前的范围，又或者最后一条索引记录之后的范围
  
  产生间隙锁的条件（RR事务隔离级别下；）：
  
  1.使用普通索引锁定；
  2.使用多列唯一索引；
  3.使用唯一索引锁定多行记录。
  
  对于使用唯一索引来搜索并给某一行记录加锁的语句，不会产生间隙锁。（这不包括搜索条件仅包括多列唯一索引的一些列的情况；在这种情况下，会产生间隙锁。）例如，如果id列具有唯一索引，则下面的语句仅对具有id值100的行使用记录锁，并不会产生间隙锁：
  ```

  ```sql
  SELECT * FROM child WHERE id = 100 FOR UPDATE;
  ```

  ## **唯一索引的间隙锁**

  数据库

  ```
  CREATE TABLE `test` (
    `id` int(1) NOT NULL AUTO_INCREMENT,
    `name` varchar(8) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  ```

  数据

  ```
  NSERT INTO `test` VALUES ('1', '小罗');
  INSERT INTO `test` VALUES ('5', '小黄');
  INSERT INTO `test` VALUES ('7', '小明');
  INSERT INTO `test` VALUES ('11', '小红');
  ```

  ```
  在进行测试之前，我们先来看看test表中存在的隐藏间隙：
  
  (-infinity, 1]
  (1, 5]
  (5, 7]
  (7, 11]
  (11, +infinity]
  
  
  只使用记录锁，不会产生间隙锁
  ```

  我们现在进行以下几个事务的测试：

  ```sql
  /* 开启事务1 */
  BEGIN;
  /* 查询 id = 5 的数据并加记录锁 */
  SELECT * FROM `test` WHERE `id` = 5 FOR UPDATE;
  /* 延迟30秒执行，防止锁释放 */
  SELECT SLEEP(30);
  
  # 注意：以下的语句不是放在一个事务中执行，而是分开多次执行，每次事务中只有一条添加语句
  
  /* 事务2插入一条 name = '小张' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (4, '小张'); # 正常执行
  
  /* 事务3插入一条 name = '小张' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (8, '小东'); # 正常执行
  
  /* 提交事务1，释放事务1的锁 */
  COMMIT;
  ```

  上诉的案例，由于主键是唯一索引，而且是只使用一个索引查询，并且只锁定一条记录，所以以上的例子，只会对 id = 5 的数据加上记录锁，而不会产生间隙锁。

  

  **产生间隙锁**

  我们继续在 id 唯一索引列上做以下的测试：

  ```sql
  /* 开启事务1 */
  BEGIN;
  /* 查询 id 在 7 - 11 范围的数据并加记录锁 */
  SELECT * FROM `test` WHERE `id` BETWEEN 5 AND 7 FOR UPDATE;
  /* 延迟30秒执行，防止锁释放 */
  SELECT SLEEP(30);
  
  # 注意：以下的语句不是放在一个事务中执行，而是分开多次执行，每次事务中只有一条添加语句
  
  /* 事务2插入一条 id = 3，name = '小张1' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (3, '小张1'); # 正常执行
  
  /* 事务3插入一条 id = 4，name = '小白' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (4, '小白'); # 正常执行
  
  /* 事务4插入一条 id = 6，name = '小东' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (6, '小东'); # 阻塞
  
  /* 事务5插入一条 id = 8， name = '大罗' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (8, '大罗'); # 阻塞
  
  /* 事务6插入一条 id = 9， name = '大东' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (9, '大东'); # 阻塞
  
  /* 事务7插入一条 id = 11， name = '李西' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (11, '李西'); # 阻塞
  
  /* 事务8插入一条 id = 12， name = '张三' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (12, '张三'); # 正常执行
  
  /* 提交事务1，释放事务1的锁 */
  COMMIT;
  ```

  从上面我们可以看到，(5, 7]、(7, 11] 这两个区间，都不可插入数据，其它区间，都可以正常插入数据。所以我们可以得出结论：**当我们给 (5, 7] 这个区间加锁的时候，会锁住 (5, 7]、(7, 11] 这两个区间。**

  

  我们再来测试如果我们锁住不存在的数据时，会怎样：

  ```sql
  /* 开启事务1 */
  BEGIN;
  /* 查询 id = 3 这一条不存在的数据并加记录锁 */
  SELECT * FROM `test` WHERE `id` = 3 FOR UPDATE;
  /* 延迟30秒执行，防止锁释放 */
  SELECT SLEEP(30);
  
  # 注意：以下的语句不是放在一个事务中执行，而是分开多次执行，每次事务中只有一条添加语句
  
  /* 事务2插入一条 id = 2，name = '小张1' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (2, '小张1'); # 阻塞
  
  /* 事务3插入一条 id = 4，name = '小白' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (4, '小白'); # 阻塞
  
  /* 事务4插入一条 id = 6，name = '小东' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (6, '小东'); # 正常执行
  
  /* 事务5插入一条 id = 8， name = '大罗' 的数据 */
  INSERT INTO `test` (`id`, `name`) VALUES (8, '大罗'); # 正常执行
  
  /* 提交事务1，释放事务1的锁 */
  COMMIT;
  ```

  我们可以看出，指定查询某一条记录时，如果这条记录不存在，会产生间隙锁。

  **结论**

  1. 对于指定查询某一条记录的加锁语句，**如果该记录不存在，会产生记录锁和间隙锁，如果记录存在，则只会产生记录锁**，如：WHERE `id` = 5 FOR UPDATE;
  2. 对于查找某一范围内的查询语句，会产生间隙锁，如：WHERE `id` BETWEEN 5 AND 7 FOR UPDATE;

  ## **普通索引的间隙锁**

  **数据准备**

  创建 test1 表：

  ```sql
  # 注意：number 不是唯一值
  
  CREATE TABLE `test1` (
    `id` int(1) NOT NULL AUTO_INCREMENT,
    `number` int(1) NOT NULL COMMENT '数字',
    PRIMARY KEY (`id`),
    KEY `number` (`number`) USING BTREE
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  ```

  在这张表上，我们有 id number 这两个字段，id 是我们的主键，我们在 number 上，建立了一个普通索引，为了方便我们后面的测试。现在我们要先加一些数据：

  ```sql
  INSERT INTO `test1` VALUES (1, 1);
  INSERT INTO `test1` VALUES (5, 3);
  INSERT INTO `test1` VALUES (7, 8);
  INSERT INTO `test1` VALUES (11, 12);
  ```

  在进行测试之前，我们先来看看test1表中 number 索引存在的隐藏间隙：

  1. (-infinity, 1]
  2. (1, 3]
  3. (3, 8]
  4. (8, 12]
  5. (12, +infinity]

  

  **案例说明**

  我们执行以下的事务（事务1最后提交），分别执行下面的语句：

  ```sql
  /* 开启事务1 */
  BEGIN;
  /* 查询 number = 3 的数据并加记录锁 */
  SELECT * FROM `test1` WHERE `number` = 3 FOR UPDATE;
  /* 延迟30秒执行，防止锁释放 */
  SELECT SLEEP(30);
  
  # 注意：以下的语句不是放在一个事务中执行，而是分开多次执行，每次事务中只有一条添加语句
  
  /* 事务2插入一条 number = 0 的数据 */
  INSERT INTO `test1` (`number`) VALUES (0); # 正常执行
  
  /* 事务3插入一条 number = 1 的数据 */
  INSERT INTO `test1` (`number`) VALUES (1); # 被阻塞
  
  /* 事务4插入一条 number = 2 的数据 */
  INSERT INTO `test1` (`number`) VALUES (2); # 被阻塞
  
  /* 事务5插入一条 number = 4 的数据 */
  INSERT INTO `test1` (`number`) VALUES (4); # 被阻塞
  
  /* 事务6插入一条 number = 8 的数据 */
  INSERT INTO `test1` (`number`) VALUES (8); # 正常执行
  
  /* 事务7插入一条 number = 9 的数据 */
  INSERT INTO `test1` (`number`) VALUES (9); # 正常执行
  
  /* 事务8插入一条 number = 10 的数据 */
  INSERT INTO `test1` (`number`) VALUES (10); # 正常执行
  
  /* 提交事务1 */
  COMMIT;
  ```

  我们会发现有些语句可以正常执行，有些语句被阻塞了。我们再来看看我们表中的数据：

  ![img](https://pic4.zhimg.com/80/v2-bd416a32e5f1740be516a61665ba285b_720w.jpg)执行之后的数据

  这里可以看到，number (1 - 8) 的间隙中，插入语句都被阻塞了，而不在这个范围内的语句，正常执行，这就是因为有间隙锁的原因。我们再进行以下的测试，方便我们更好的理解间隙锁的区域（我们要将数据还原成原来的那样）：

  ```sql
  /* 开启事务1 */
  BEGIN;
  /* 查询 number = 5 的数据并加记录锁 */
  SELECT * FROM `test1` WHERE `number` = 3 FOR UPDATE;
  /* 延迟30秒执行，防止锁释放 */
  SELECT SLEEP(30);
  
  /* 事务1插入一条 id = 2， number = 1 的数据 */
  INSERT INTO `test1` (`id`, `number`) VALUES (2, 1); # 阻塞
  
  /* 事务2插入一条 id = 3， number = 2 的数据 */
  INSERT INTO `test1` (`id`, `number`) VALUES (3, 2); # 阻塞
  
  /* 事务3插入一条 id = 6， number = 8 的数据 */
  INSERT INTO `test1` (`id`, `number`) VALUES (6, 8); # 阻塞
  
  /* 事务4插入一条 id = 8， number = 8 的数据 */
  INSERT INTO `test1` (`id`, `number`) VALUES (8, 8); # 正常执行
  
  /* 事务5插入一条 id = 9， number = 9 的数据 */
  INSERT INTO `test1` (`id`, `number`) VALUES (9, 9); # 正常执行
  
  /* 事务6插入一条 id = 10， number = 12 的数据 */
  INSERT INTO `test1` (`id`, `number`) VALUES (10, 12); # 正常执行
  
  /* 事务7修改 id = 11， number = 12 的数据 */
  UPDATE `test1` SET `number` = 5 WHERE `id` = 11 AND `number` = 12; # 阻塞
  
  /* 提交事务1 */
  COMMIT;
  ```

  我们来看看结果：

  ![img](https://pic1.zhimg.com/80/v2-60d83dfa236fb4a25ab4c3a5df45b0d0_720w.jpg)执行后的数据

  这里有一个奇怪的现象：

  - 事务3添加 id = 6，number = 8 的数据，给阻塞了；
  - 事务4添加 id = 8，number = 8 的数据，正常执行了。
  - 事务7将 id = 11，number = 12 的数据修改为 id = 11， number = 5的操作，给阻塞了；

  这是为什么呢？我们来看看下边的图，大家就明白了。

  ![img](https://pic2.zhimg.com/80/v2-e5fe73d5f7fda8c298ce60fd35915885_720w.jpg)隐藏的间隙锁图

  从图中可以看出，当 number 相同时，会根据主键 id 来排序，所以：

  1. 事务3添加的 id = 6，number = 8，这条数据是在 （3, 8） 的区间里边，所以会被阻塞；
  2. 事务4添加的 id = 8，number = 8，这条数据则是在（8, 12）区间里边，所以不会被阻塞；
  3. 事务7的修改语句相当于在 （3, 8） 的区间里边插入一条数据，所以也被阻塞了。

  

  **结论**

  1. 在普通索引列上，**不管是何种查询，只要加锁，都会产生间隙锁，这跟唯一索引不一样；**
  2. 在普通索引跟唯一索引中，数据间隙的分析，数据行是优先根据普通索引排序，再根据唯一索引排序。

  

## 临键锁(Next-key Locks)

**临键锁**，是**记录锁与间隙锁的组合**，它的封锁范围，既包含索引记录，又包含索引区间。



**注：**临键锁的主要目的，也是为了避免**幻读**(Phantom Read)。如果把事务的隔离级别降级为RC，临键锁则也会失效。

## **本文要点**

1. 记录锁、间隙锁、临键锁，都属于排它锁；
2. 记录锁就是锁住一行记录；
3. 间隙锁只有在事务隔离级别 RR 中才会产生；
4. 唯一索引只有锁住多条记录或者一条不存在的记录的时候，才会产生间隙锁，指定给某条存在的记录加锁的时候，只会加记录锁，不会产生间隙锁；
5. 普通索引不管是锁住单条，还是多条记录，都会产生间隙锁；
6. 间隙锁会封锁该条记录相邻两个键之间的空白区域，防止其它事务在这个区域内插入、修改、删除数据，这是为了防止出现 幻读 现象；
7. 普通索引的间隙，优先以普通索引排序，然后再根据主键索引排序（多普通索引情况还未研究）；
8. 事务级别是RC（读已提交）级别的话，间隙锁将会失效。