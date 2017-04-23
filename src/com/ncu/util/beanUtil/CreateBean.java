package com.ncu.util.beanUtil;

import com.mysql.jdbc.Connection;
import com.ncu.util.StringUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * 生成表格bean的工具类
 * @author oulc
 *
 */
public class CreateBean {
	String path = "D:/studyWorkSpace/renting/src/com/ncu/table";
	String tableName = "apply_group_message";
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://192.168.191.5:3306/renting";
	String user = "zuowy";
	String password = "123456";
	Connection con = null;

	Table table = null;
	
	public static void main(String args[]) throws SQLException{
		CreateBean createBean = new CreateBean();
	}
	
	public void getConnection(){
		try {
			Class.forName(driver);
			con = (Connection)DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public CreateBean() throws SQLException{
		getConnection();
		this.table = new Table(tableName,con);
		createFile();
	}
	/**
	 * 获取表格的描述
	 * @throws SQLException
	 */
	
	public void createFile(){
		createBeanFile();
		createEngineFile();
		createIvalueFile();
		System.out.println("文件生成完成");
	}
	public void createBeanFile(){
		File beanFile = new File(path+"/bean/"+table.getBeanName()+".java");
		FileOutputStream out = null;
		BufferedOutputStream bufferOut = null;
		try {
			if(!beanFile.exists()){
				beanFile.createNewFile();
			}
			out = new FileOutputStream(beanFile);
			bufferOut = new BufferedOutputStream(out);
			String beanContent = createBeanContent();
			bufferOut.write(beanContent.getBytes());
			bufferOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				bufferOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void createEngineFile(){
		File engineFile = new File(path+"/engine/"+table.getEngineName()+".java");
		FileOutputStream out = null;
		BufferedOutputStream bufferOut = null;
		try {
			if(!engineFile.exists()){
				engineFile.createNewFile();
			}
			out = new FileOutputStream(engineFile);
			bufferOut = new BufferedOutputStream(out);
			String engineContent = createEngineContent();
			bufferOut.write(engineContent.getBytes());
			bufferOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				bufferOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void createIvalueFile(){
		File ivalueFile = new File(path+"/ivalue/"+table.getIvalueName()+".java");
		FileOutputStream out = null;
		BufferedOutputStream bufferOut = null;
		try {
			if(!ivalueFile.exists()){
				ivalueFile.createNewFile();
			}
			out = new FileOutputStream(ivalueFile);
			bufferOut = new BufferedOutputStream(out);
			String ivalueContent = createIvalueContent();
			bufferOut.write(ivalueContent.getBytes());
			bufferOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				bufferOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 创建Bean的内容
	 * @return
	 */
	public String createBeanContent(){
		StringBuilder content = new StringBuilder();
		String packageContent = path.substring(path.indexOf("src")+4).replace("/", ".")+".bean";
		String tableName = table.getTableName();
		String ivalueName = table.getIvalueName();
		String beanName = table.getBeanName();
		
		content.append("package ").append(packageContent).append(";\r\n\r\n");
		
		content.append("import java.util.ArrayList;\r\n\r\n")
		.append("import com.ncu.util.beanUtil.BeanUtil;\r\n")
		.append("import javax.persistence.*\r\n;")
		.append("import java.util.Date;\r\n")
		.append("import java.io.Serializable;\r\n");
		
		content.append("import ")
		.append(path.substring(path.indexOf("src")+4).replace("/", "."))
		.append(".ivalue.")
		.append(ivalueName)
		.append(";\r\n\r\n");
		
		content.append("@Entity")
		.append("\r\n")
		.append("@Table(name =\"")
		.append(tableName)
		.append("\")\r\n");
		
		content.append("public class ")
		.append(beanName)
		.append(" implements ")
		.append(ivalueName)
		.append(",Serializable{\r\n\r\n");
		
		StringBuilder def = new StringBuilder();
		StringBuilder method = new StringBuilder();
		for(TableColDsc tableColDsc:table.getTableColDsc()){
			
			if(tableColDsc.isUnique()){
				def.append("  @Id\r\n")
				.append("  @GeneratedValue(strategy = GenerationType.IDENTITY)\r\n");
			}
			def.append("  @Column(name = ")
			.append(ivalueName)
			.append(".")
			.append(tableColDsc.getS_Code())
			.append(")\r\n")
			.append("  private ")
			.append(tableColDsc.getColType())
			.append(" ")
			.append(tableColDsc.gethumpColNameFL())
			.append(";\r\n\r\n");
			
			method.append("  public void set")
			.append(tableColDsc.getHumpColNameFU())
			.append("(")
			.append(tableColDsc.getColType())
			.append(" value){\r\n")
			.append("    this.")
			.append(tableColDsc.gethumpColNameFL())
			.append(" = value;\r\n")
			.append("  }\r\n\r\n")
			.append("  public ")
			.append(tableColDsc.getColType())
			.append(" get")
			.append(tableColDsc.getHumpColNameFU())
			.append("(){\r\n")
			.append("    return ")
			.append(tableColDsc.gethumpColNameFL())
			.append(";\r\n  }\r\n\r\n");
		}
		content.append(def)
		.append("  @Transient\r\n  public static Class beanClass = ")
		.append(beanName)
		.append(".class;\r\n\r\n")
		.append("  @Transient\r\n")
		.append("  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);\r\n\r\n")
		.append(method)
		.append("}");
		return content.toString();
		
	}
	public String createEngineContent(){
		StringBuilder content = new StringBuilder();
		String tableName = table.getTableName();
		String beanName = table.getBeanName();//bean的名称
		String packageContent = path.substring(path.indexOf("src")+4).replace("/", ".")+".engine";
		String beanPath = path.substring(path.indexOf("src")+4).replace("/", ".")+".bean";
		content.append("package ")
		.append(packageContent)
		.append(";\r\n\r\n")
		.append("import java.util.HashMap;\r\n")
		.append("import java.util.List;\r\n")
		.append("import org.springframework.stereotype.Repository;\r\n")
		.append("import org.springframework.beans.factory.annotation.Autowired;\r\n")
		.append("import ")
		.append(beanPath)
		.append(".")
		.append(beanName)
		.append(";\r\n")
		.append("import com.ncu.util.beanUtil.BeanUtil;\r\n\r\n")
		.append("@Repository\r\n")
		.append("public class ")
		.append(StringUtil.toHumpWord(tableName, true))
		.append("Engine{\r\n")
				.append("@Autowired\r\nBeanUtil beanUtil;\r\n\r\n")
		.append("  public List queryByCondition(String condition , HashMap<String,String> params,int beginPage ,int endPage) throws Exception{\r\n")
		.append("    return beanUtil.queryByCondition(condition, params, beginPage, endPage, ")
		.append(beanName)
		.append(".beanClass.getSimpleName());\r\n")
		.append("  }\r\n")
		.append("  public void save(Object value) throws Exception{\r\n    beanUtil.save(value);\r\n  }\r\n")
		.append("  public long queryCountByCondition(String condition , HashMap<String,String> params)throws Exception{\r\n  ")
		.append("  return beanUtil.getCount(condition,params,")
		.append(beanName)
		.append(".beanClass.getSimpleName());\r\n  }\r\n")
				.append("  public void delete (Object value)throws Exception{")
				.append("  \r\n    beanUtil.delete(value);\r\n  }")
		.append("\r\n}\r\n");
		return content.toString();
	}
	public String createIvalueContent(){
		StringBuilder content = new StringBuilder();
		String packageContent = path.substring(path.indexOf("src")+4).replace("/", ".")+".ivalue";
		String iValueName = table.getIvalueName();//ivalue的名称
		
		content.append("package ")
		.append(packageContent)
		.append(";\r\n\r\n")
		.append("import java.util.Date;\r\n\r\n")
		.append("public interface ")
		.append(iValueName)
		.append("{\r\n");
		
		StringBuilder setMethod = new StringBuilder();
		StringBuilder getMethod = new StringBuilder();
		for(TableColDsc tableColDsc:table.getTableColDsc()){
			content.append("  public final static ")
			.append("String ")
			.append(tableColDsc.getS_Code())
			.append(" = \"")
			.append(tableColDsc.getColName())
			.append("\";\r\n");
			
			setMethod.append("  public void set")
			.append(tableColDsc.getHumpColNameFU())
			.append("(")
			.append(tableColDsc.getColType())
			.append(" value);\r\n");
			
			getMethod.append("  public ")
			.append(tableColDsc.getColType())
			.append(" get")
			.append(tableColDsc.getHumpColNameFU())
			.append("(")
			.append(");\r\n");
			
		}
		content.append(setMethod).append(getMethod);
		content.append("}");
		return content.toString();
	}
	
	
	

}
