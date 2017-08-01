package main.scala.com.tingfeng.scala.sql.impl

import main.scala.com.tingfeng.scala.sql.SqlT
import scala.collection.mutable.ArrayBuffer

class TableSql extends SqlT {

  var cls: Class[_] = null
  var alies: String = ""
  var tableName: String = null
  var joinSql: JoinSql = null
  var params: ArrayBuffer[Any] = null

  def this(cls: Class[_], alies: String) {
    this()
    this.cls = cls
    this.tableName = cls.getSimpleName()
    this.alies = alies
    this.params = new ArrayBuffer[Any]()
  }
  def this(table: String, alies: String) {
    this()
    this.tableName = table;
    this.alies = alies;
    this.params = new ArrayBuffer[Any]();
  }

  def getParams(): ArrayBuffer[_] = {
    if (this.joinSql != null) {
      params.++=(this.joinSql.getTable().params);
      params.++=(this.joinSql.getParamsList());
    }
    params;
  }

  def getSqlString(): String = {
    val sBuilder = new StringBuilder();
    sBuilder.append(this.tableName);
    sBuilder.append(" AS ");
    sBuilder.append(this.alies);
    if (null != this.joinSql) {
      val jCondition = this.joinSql;
      sBuilder.append(" ");
      sBuilder.append(jCondition.getJoinType());
      sBuilder.append(" JOIN ");
      val joinSql = jCondition.getSqlString();
      sBuilder.append(joinSql);
    }
    return sBuilder.toString();
  }
}