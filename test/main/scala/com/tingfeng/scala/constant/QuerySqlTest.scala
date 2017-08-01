package com.tingfeng.scala.constant

import main.scala.com.tingfeng.scala.sql.impl.QuerySql
import main.scala.com.tingfeng.scala.sql.impl.TableSql
import main.scala.com.tingfeng.scala.sql.impl.TablesSql
import main.scala.com.tingfeng.scala.sql.impl.WhereSql
import main.scala.com.tingfeng.scala.sql.impl.TablesSql
import main.scala.com.tingfeng.scala.sql.impl.ColumSql
import main.scala.com.tingfeng.scala.sql.impl.OrderBySql
import main.scala.com.tingfeng.scala.constant.JoinType
import com.tingfeng.scala.bean.User
import main.scala.com.tingfeng.scala.sql.impl.TableSql
import com.tingfeng.scala.bean.Book
import main.scala.com.tingfeng.scala.sql.impl.TableSql
import main.scala.com.tingfeng.scala.sql.impl.ColumSql
import main.scala.com.tingfeng.scala.sql.impl.TablesSql
import main.scala.com.tingfeng.scala.sql.impl.JoinSql
import main.scala.com.tingfeng.scala.sql.impl.TableSql
import main.scala.com.tingfeng.scala.sql.impl.ColumSql
import main.scala.com.tingfeng.scala.sql.impl.TablesSql

object QuerySqlTest {
  def main(args: Array[String]): Unit = {
    testQuery()
  }

  def testQuery()={
      val querySql=new QuerySql()
          querySql.colums = null
                  
      val tables=new TablesSql()
      val ta=new TableSql(classOf[User],"user")
      val tb=new TableSql(classOf[Book],"book")
      val joinCondition=new JoinSql(tb, JoinType.right)
                    joinCondition.add("user.userName!=?",Some("张大叔"))
                    joinCondition.add("user.userName!=?",Some("王大叔"))
      ta.joinSql = joinCondition
      tables.add(ta)
              
      val colums=new ColumSql();
      colums.add("user.userName")
      colums.add("book.bookName")
      colums.add("book.author")
      
      
      val whereSql=new WhereSql()
                whereSql.add("user.userName=book.author")
                whereSql.add("user.userName=?",Some("王大叔"))
      val orderBySql=new OrderBySql()
                orderBySql.add("user.userName")
     
     querySql.colums = colums
     querySql.tables = tables
     querySql.whereSql = whereSql
     querySql.orderBySql = orderBySql
     
      val queryB=new QuerySql();
               val tableb=querySql.toTableSql(" ta")
               val tablebs=new TablesSql()
               tablebs.add(tableb)
               val columSql=new ColumSql()
               columSql.add("ta.userName")
       queryB.tables = tablebs
       queryB.colums = columSql
       
      System.out.println(queryB.getQueryString())
      System.out.println()
      //System.out.println(new SqlFormatUtils().format(queryB.getQueryString()))
      //SqlFormatUtils.printFormatSql(queryB.getQueryString())
      queryB.printAllParams()
  }
}