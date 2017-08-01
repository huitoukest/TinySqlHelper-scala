package main.scala.com.tingfeng.scala.constant

sealed class ConnectKeyWords private (name: String) extends  SqlKeyWordsT{
   /**
    * 得到关键字
    */
   override def getKeyWords():java.lang.String={
     name
   }
}

object ConnectKeyWords {
   //顺序,默认100表示顺序最低
   val select = ConnectKeyWords("select")
   val from = ConnectKeyWords("from")
   val join = ConnectKeyWords("join")
   val on = ConnectKeyWords("on")
   val groupBy = ConnectKeyWords("group by")
   val having = ConnectKeyWords("having")
   val where =  ConnectKeyWords("where")
   val orderBy = ConnectKeyWords("order by")
   val limit = ConnectKeyWords("limit")
   val offset = ConnectKeyWords("offset")
 
  def apply(name: String):ConnectKeyWords={
     new ConnectKeyWords(name)
   }
}