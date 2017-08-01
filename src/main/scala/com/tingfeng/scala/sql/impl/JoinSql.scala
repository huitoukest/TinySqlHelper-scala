package main.scala.com.tingfeng.scala.sql.impl

import main.scala.com.tingfeng.scala.constant.ConnectType
import main.scala.com.tingfeng.scala.constant.ConnectKeyWords
import main.scala.com.tingfeng.scala.constant.JoinType

class JoinSql(val table: TableSql, val joinType: JoinType) extends BaseSql(ConnectType.and, ConnectKeyWords.on) {
  def getTable(): TableSql = {
    table
  }

  def getJoinType(): JoinType = {
    joinType
  }
}