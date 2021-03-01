package com.buaa.review.algorithm.leetc_SQL;

/**
 * @ClassName Sql
 * @Description
 * @Author Bryce
 * @Date 2021-02-23 14:27
 */
public class Sql {
    /**
     * update salary set sex = if(sex='f',m,f)
     *
     * SELECT Email FROM Person GROUP BY Email HAVING COUNT(1) >= 2;
     *
     * SELECT DISTINCT(Email) FROM Person a JOIN Person b ON a.Email = b.Email WHERE a.Id != b.Id
     *
     * SELECT name, population, area FROM World WHERE area >= 3000000 OR population >= 25000000;
     *（用union会更快 因为OR索引会失效）
     *
     * SELECT id,movie,description,rating FROM cinema WHERE description != 'boring' AND id%2 != 0 ORDER BY rating DESC;
     * WHERE id & 1 AND
     * 位运算 奇数与1& =1 偶数与1& =0
     *
     * SELECT FirstName, LastName, City, State FROM Person LEFT JOIN Address ON Person.PersonId = Address.PersonId;
     *
     *SELECT e1.Name as Employee FROM Employee e1 JOIN Employee e2 ON e1.ManagerId = e2.Id WHERE e1.Salary > e2.Salary;
     *
     *SELECT Name as Customers FROM Customers LEFT JOIN Orders ON Customers.Id = Orders.CustomerId WHERE IFNULL(CustomerId, 0) = 0;
     *SELECT Name as Customers FROM Customers LEFT JOIN Orders ON Customers.Id = Orders.CustomerId WHERE CustomerId is NULL;
     *
     *
     *int a = {2,2,3,4,3}
     *
     *
     *
     * SELECT w1.id FROM Weather w1 JOIN Weather w2 ON w1.recordDate = date_add(w2.recordDate, interval 1 day) WHERE w1.Temperature > w2.Temperature ;
     * # datediff(日期1, 日期2)： 得到的结果是日期1与日期2相差的天数。 如果日期1比日期2大，结果为正；如果日期1比日期2小，结果为负。
     *
     *
     * SELECT class FROM courses GROUP BY class HAVING COUNT(DISTINCT(student)) >= 5;
     *
     * 176.SELECT (SELECT distinct  salary  FROM Employee ORDER BY salary DESC limit 1,1) as SecondHighestSalary;
     *
     * 626.UPDATE seat s1 JOIN seat s2 ON s1.id = if(s2.id & 1,s2.id + 1,s2.id - 1) SET s1.student = s2.student;
     *
     *
     * 626.select rank() over(order by (id-1)^1) as id,student from seat
     *  这妙在利用异或只把偶数减2，奇数不变，从而调位。（单数-1后，最后一位是0，异或把最后一位变回1，等于不变；偶数-1后，最后一位是1，异或把最后一位变成0，等于再减去1）
     *
     *+----+-------+
     * | Id | Score |
     * +----+-------+
     * | 1  | 3.50  |
     * | 2  | 3.65  |
     * | 3  | 4.00  |
     * | 4  | 3.85  |
     * | 5  | 4.00  |
     * | 6  | 3.65  |
     * +----+-------+
     * Score / Rank
     *
     * SELECT Score,IF(Score == (SELECT Score FORM Scores s2 WHERE s2.Id = s1.Id - 1), s2.Rank, Rank+1) as Rank FROM Scores s1 ORDER BY Score DESC
     *
     * 178.//需要用变量
     * SELECT Scores.Score,Rank FROM Scores JOIN
     * (SELECT Score, (@i:=@i+1) as Rank FROM
     * (
     * SELECT MAX(Score) as Score FROM Scores GROUP BY Score ORDER BY MAX(Score) DESC
     * ) res,(SELECT @i:= 0) as i) res2
     *  ON Scores.score = res2.Score ORDER BY Rank
     *
     * //通过找整体计算规律 而不是根据上一条数据做计算
     * 178.SELECT Score,(SELECT COUNT(DISTINCT(Score)) FROM Scores s2 WHERE s2.Score >= s1.Score) as Rank FROM Scores s1 ORDER BY Score DESC
     *
     * 180.
     * Logs 表：
     * +----+-----+
     * | Id | Num |
     * +----+-----+
     * | 1  | 1   |
     * | 2  | 1   |
     * | 3  | 1   |
     * | 4  | 2   |
     * | 5  | 1   |
     * | 6  | 2   |
     * | 7  | 2   |
     * +----+-----+
     *
     * SELECT DISTINCT(l1.num) as ConsecutiveNums FROM logs l1 left join logs l2 ON l1.id = l2.id - 1 left join logs l3 on l1.id = l3.id - 2 WHERE l1.num = l2.num and l2.num = l3.num;
     *
     * 177.CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
     * BEGIN
     *   SET N = N - 1;
     *   RETURN (
     *       # Write your MySQL query statement below.
     *       SELECT DISTINCT(Salary) FROM Employee ORDER BY Salary DESC LIMIT N, 1
     *   );
     * END
     *
     *
     *
     *
     *
     *
     *
     */
}
