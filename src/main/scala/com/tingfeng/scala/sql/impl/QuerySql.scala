package main.scala.com.tingfeng.scala.sql.impl

import main.scala.com.tingfeng.scala.sql.SqlT
import scala.collection.mutable.ArrayBuffer
import main.scala.com.tingfeng.scala.sql.SqlStringT

class QuerySql  extends SqlT {
   private val  sb= new StringBuilder(500);
   
	 var  colums:ColumSql=null
	 var  tables:TablesSql=null
	 var  groupBySql:GroupBySql=null
	 var  havingSql:HavingSql=null
	 var  orderBySql:OrderBySql=null
	 var  limitSql:LimitSql=null
	 var  whereSql:WhereSql=null
	 private var  allParams=new ArrayBuffer[Any](10);
	
	/**
	 * 返回有顺序的ISqlString的List
	 * @return
	 */
	def getSqlList():ArrayBuffer[SqlStringT[_]]={
	    val sqlList=new ArrayBuffer[SqlStringT[_]]();
	    this.addNotNullISqlString(sqlList,colums);
	    this.addNotNullISqlString(sqlList,tables);
	    this.addNotNullISqlString(sqlList,whereSql);
	    this.addNotNullISqlString(sqlList,groupBySql);
      this.addNotNullISqlString(sqlList,havingSql);
	    this.addNotNullISqlString(sqlList,orderBySql);
	    this.addNotNullISqlString(sqlList,limitSql);
	    sqlList;
	}
	
	/**
	 * 将sqlString为非空的加入sqlList中
	 * @param sqlList
	 * @param sqlString
	 */
	protected def addNotNullISqlString[SQ <:SqlStringT[_]](sqlList: ArrayBuffer[SQ],sqlString: SQ):Unit ={
	    if(null!=sqlString){
	        sqlList.+=(sqlString);
	    }
	}
	
	def  getQueryString():String= {
		sb.setLength(0);
		val sqlList=this.getSqlList();
		for(i <- 0  until sqlList.size )
		{
		    val sqlString=sqlList(i);
		    sqlString.getSqlString(Some(sb));
		}
		return sb.toString();
	}
	
	def  printFormateString():Unit ={
        val sqlList=this.getSqlList();
        for(i <- 0 until sqlList.size){
            val sqlString=sqlList(i);
            System.out.println(sqlString.getSqlString()+"\t");
            System.out.println();      
        }	 
        this.printParams(this.getAllParams());
	}
	
	/**
	 * 参数打印
	 */
	protected def printParams[SQ <:SqlStringT[_]](sqlString: SQ):Unit ={
	    System.out.print("params:");
        if(null!=sqlString){
            for(i <- 0 until sqlString.getParamsList().size){
                if(i>0)
                    {
                        System.out.print(",");
                    }
                val obj = sqlString.getParamsList()(i);
                System.out.print(obj.toString());
            }
        }
	}
	
	/**
     * 参数打印
     */
    protected def printParams(sqlString: ArrayBuffer[_]):Unit ={
        System.out.print("params:");
        if(null!=sqlString){
            for(i <- 0  until sqlString.size ){
                if(i>0)
                    {
                        System.out.print(",");
                    }
                val obj = sqlString(i);
                System.out.print(obj.toString());
            }
        }
    }
	/**
	 * 转换为一个table
	 * @param aliesName
	 * @return
	 */
    def  toTableSql(aliesName:String):TableSql={
        val tables=new TableSql("("+this.getQueryString()+")",aliesName);
        tables.params = (this.getAllParams());
        tables
    }

    def  getAllParams():ArrayBuffer[Any]= {
        allParams.clear();
        val sqlList=this.getSqlList();
        for(i <- 0 until sqlList.size)
        {
            val sqlString=sqlList(i);
            val params=sqlString.getParamsList();
            this.allParams.++=(params);
        }
       this.allParams
    }

    def printAllParams()= {
       this.printParams(this.getAllParams());
    }
  
}