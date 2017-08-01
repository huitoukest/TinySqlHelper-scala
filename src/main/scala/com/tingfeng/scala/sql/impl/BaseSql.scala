package main.scala.com.tingfeng.scala.sql.impl

import main.scala.com.tingfeng.scala.sql.SqlStringT
import main.scala.com.tingfeng.scala.constant.ConnectKeyWords
import main.scala.com.tingfeng.scala.constant.ConnectType

class BaseSql(val connectType: ConnectType, val connectKeyWords: ConnectKeyWords) extends SqlStringT[String] {

  @Override
  def getDefaultConnectType(): ConnectType = {
    connectType
  }

  @Override
  def getConnectKeyWords(): ConnectKeyWords = {
    connectKeyWords
  }
}