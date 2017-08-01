package main.scala.com.tingfeng.scala.constant

sealed class OrderType private (name: String){
   /**
    * 得到关键字
    */
  def getKeyWords():java.lang.String={
      name
   }
}

object OrderType {
   val asc = OrderType(" asc ")
   val desc = OrderType(" desc ")
    
  def apply(name: String):OrderType={
     new OrderType(name)
   }
}