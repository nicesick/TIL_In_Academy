# SQL2

## I. SQL 명령어

- RANK() OVER(ORDER BY ...) : ORDER BY 기준으로 랭크를 매겨준다.
- RANK() OVER(PARTITION BY ... ORDER BY ...) : PARTITION BY 기준으로 랭크를 매겨준다.

- ROW_NUMNER() OVER(ORDER BY ...) : 랭크는 기준이 같으면 같은 등수로 표시되지만, ROW_NUMBER 는 다르게 표시된다.

- CASE
  - WHEN ... THEN
  - ELSE ...
- END
- : CASE 구문을 이용해 경우를 나눌 수 있다.
- GROUP BY ROLLUP(LOC) : SUM 의 SUM 을 구해준다.



## II. JDBC COMMIT, ROLLBACK

- Connection con = DriverManager.getConnec...
- con.setAutoCommit(false) : 자동 커밋 해제
- con.commit() : 완성 된 후 커밋
- con.rollback() : 오류나면 롤백