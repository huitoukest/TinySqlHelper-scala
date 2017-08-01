package main.scala.com.tingfeng.scala.sql

import scala.collection.mutable.ArrayBuffer

import main.scala.com.tingfeng.scala.constant.ConnectKeyWords
import main.scala.com.tingfeng.scala.constant.ConnectType
import java.io.Serializable

/**
 * 定义sqlString链接具有的基本方法
 * @version 1.0
 *
 */
trait SqlStringT[T <: Any] extends Serializable with SqlT {

  val serialVersionUID = 1L;
  protected var sqlList: ArrayBuffer[T] = null

  protected var paramsList: ArrayBuffer[Any] = null

  protected var connectTypes: ArrayBuffer[ConnectType] = null

  /**
   * 初始化方法
   */
  def init(): Unit = {
    if (this.sqlList == null) {
      this.sqlList = new ArrayBuffer[T](5);
    }
    if (paramsList == null) {
      this.paramsList = new ArrayBuffer[Any](5);
    }
    if (connectTypes == null) {
      this.connectTypes = new ArrayBuffer[ConnectType](3);
    }
  }

  /**
   * 返回默认的连接类型
   * @return
   */
  def getDefaultConnectType(): ConnectType
  /**
   * 返回当前的链接关键字
   * @return
   */
  def getConnectKeyWords(): ConnectKeyWords

  /**
   * 加入相关sql
   * @param sqlString
   * @param connectType 和之前sql/条件的连接类型,默认为NULL连接类型
   * @param param 参数的值,默认为无参数
   * @return
   */
  def add[P <: Any](sqlString: T, connectType: Option[ConnectType], param: Option[P]): SqlStringT[T] = {
    this.init()
    this.sqlList += sqlString
    this.connectTypes.+=(connectType getOrElse (this.getDefaultConnectType()))
    param match {
      case Some(_) => this.paramsList.+=(param.get)
      case _ =>
    }
    return this;
  }
  /**
   * 加入sql对象,默认没有参数
   * @param sqlObj
   * @return
   */
  def add[P <: Any](sqlString: T, param: Option[P]): SqlStringT[T] = {
    add(sqlString, None, param)
  }
  /**
   * 加入sql对象,默认没有参数
   * @param sqlObj
   * @return
   */
  def add(sqlString: T): SqlStringT[T] = {
    add(sqlString, None, None);
  }
  /**
   * 获取提交的参数
   * @return
   */
  def getParamsList(): ArrayBuffer[Any] = {
    this.paramsList
  }
  /**
   *
   * @param sb 在StringBuilder后附加sql条件
   * @return
   */
  def getSqlString(sb: Option[StringBuilder]): String = {
    var sList: ArrayBuffer[T] = sqlList
    var sBuilder: StringBuilder = null
    var sql: String = null

    if (sList == null || sList.size <= 0) {
      sql = "";
    }
    if (sb == None) {
      sBuilder = new StringBuilder();
    } else {
      sBuilder = sb.get;
    }
    for (i <- 0 until sList.size) {
      if (i == 0) {
        sBuilder.append(" ");
        sBuilder.append(this.getConnectKeyWords().getKeyWords());
        sBuilder.append(" ");
      }
      if (i > 0) {
        sBuilder.append(this.connectTypes(i).getKeyWords());
        sBuilder.append(" ");
      }
      sBuilder.append(sList(i));
      if (i == sList.size) {

      }
    }
     sBuilder.toString()
  }
  /**
   * 返回处理后的sql语句
   * @return
   */
  def getSqlString(): String = {
    this.getSqlString(None)
  }
}