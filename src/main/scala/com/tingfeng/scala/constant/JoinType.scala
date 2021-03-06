package main.scala.com.tingfeng.scala.constant

sealed class JoinType private (name: String){
   /**
    * 得到join类型关键字
    */
  def getKeyWords():java.lang.String={
      name
   }
}

object JoinType {
   val inner = JoinType(" inner ")
   val outer = JoinType(" full outter ")
   val left = JoinType(" left ")
   val right = JoinType(" right ")
    
  def apply(name: String):JoinType={
     new JoinType(name)
   }
}