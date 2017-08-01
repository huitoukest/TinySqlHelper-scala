package main.scala.com.tingfeng.scala.sql.impl

import main.scala.com.tingfeng.scala.sql.SqlStringT
import scala.collection.mutable.ArrayBuffer
import main.scala.com.tingfeng.scala.constant.ConnectKeyWords
import main.scala.com.tingfeng.scala.constant.ConnectType

class TablesSql extends SqlStringT[TableSql] {
  override val serialVersionUID = 1L;

  override def getSqlString(sb: Option[StringBuilder]): String = {
    var whereList = this.sqlList;
    var sBuilder: StringBuilder = null;
    if (whereList == null || whereList.isEmpty) {
      return "";
    }
    if (None == sb) {
      sBuilder = new StringBuilder();
    } else {
      sBuilder = sb.get
    }
    if (!whereList.isEmpty) {
      sBuilder.append(" ")
      sBuilder.append(this.getConnectKeyWords().getKeyWords())
      sBuilder.append(" ")
    }
    for (i <- 0 until whereList.size) {
      var tableSql = whereList(i)
      if (i > 0) {
        sBuilder.append(" , ")
      }
      sBuilder.append(tableSql.getSqlString())
      if (i == whereList.size) {
        sBuilder.append(" ")
      }
    }
     sBuilder.toString()
  }

  override def getParamsList(): ArrayBuffer[Any] = {
    if (this.paramsList != null) {
      this.paramsList.clear()
    } else {
      this.paramsList = new ArrayBuffer[Any]()
    }
    val whereList = this.sqlList
    for (param <- whereList) {
      this.paramsList.++=(param.getParams())
    }
    this.paramsList
  }

  override def getSqlString(): String = {
    this.getSqlString(None)
  }

  def getDefaultConnectType(): ConnectType = {
    ConnectType.comma
  }

  def getConnectKeyWords(): ConnectKeyWords = {
    ConnectKeyWords.from
  }
}