package com.tingfeng.scala.constant

import org.junit.Test
import org.junit.Assert
import main.scala.com.tingfeng.scala.constant.ConnectKeyWords

object SqlConnectKeyWordsTest {
  def main(args: Array[String]): Unit = {
    testPrint();
  }

  def testPrint() = {
    val select = ConnectKeyWords.select;
    println(select.getKeyWords());
    print(ConnectKeyWords.groupBy.getOrderValue());
  }
}