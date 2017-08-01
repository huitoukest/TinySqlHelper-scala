package main.scala.com.tingfeng.scala.constant

sealed class ConnectType private (name: String){
   /**
    * 得到连接类型关键字
    */
  def getKeyWords():java.lang.String={
      name
   }
}

object ConnectType {
   //顺序,默认100表示顺序最低
   val not = ConnectType(" not")
   val and = ConnectType("and")
   val or = ConnectType(" or")
   val comma = ConnectType(" ,")
   val blank = ConnectType(" ")
 
  def apply(name: String):ConnectType={
     new ConnectType(name)
   }
}