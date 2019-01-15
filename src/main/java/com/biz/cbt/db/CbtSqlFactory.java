package com.biz.cbt.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.biz.cbt.dao.CbtDao;

public class CbtSqlFactory {

	SqlSessionFactory sessionFactory;

	public SqlSessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public CbtSqlFactory() {
		// TODO Auto-generated constructor stub
		Properties props = new Properties();
		
		props.put("DRIVER", DBContract.CBT_PRO.Driver);
		props.put("URL", DBContract.CBT_PRO.url);
		props.put("USER", DBContract.CBT_PRO.user);
		props.put("PASSWORD", DBContract.CBT_PRO.password);
		
		CbtDataSourceFactory dataFactory = new CbtDataSourceFactory();
		dataFactory.setProperties(props);
		
		DataSource dataSource = dataFactory.getDataSource();
		
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		
		Environment env = new Environment("CbtEnv", transactionFactory, dataSource);
		
		Configuration config = new Configuration(env);
		config.addMapper(CbtDao.class);
		
		this.sessionFactory = new SqlSessionFactoryBuilder().build(config);
	
	}

}
