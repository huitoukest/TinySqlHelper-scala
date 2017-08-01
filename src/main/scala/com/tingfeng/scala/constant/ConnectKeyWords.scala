package main.scala.com.tingfeng.scala.constant

sealed class ConnectKeyWords private (name: String,value: Integer){
   /**
    * 得到关键字
    */
  def getKeyWords():java.lang.String={
     name
   }
   /**
    * 得到排序值,顺序,默认100表示顺序最低
    */
  def getOrderValue():java.lang.Integer={
      value
   }
}

object ConnectKeyWords {
   //顺序,默认100表示顺序最低
   val select = ConnectKeyWords("select",1)
   val from = ConnectKeyWords("from",2)
   val join = ConnectKeyWords("join",3)
   val on = ConnectKeyWords("on",4)
   val groupBy = ConnectKeyWords("group by",5)
   val having = ConnectKeyWords("having",6)
   val where =  ConnectKeyWords("where",7)
   val orderBy = ConnectKeyWords("order by",8)
   val limit = ConnectKeyWords("limit",9)
   val offset = ConnectKeyWords("offset",10)
 
  def apply(name: String,value: Integer):ConnectKeyWords={
     new ConnectKeyWords(name,value)
   }
}