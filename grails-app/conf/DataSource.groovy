dataSource {
	
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "test"
    password = "test"
	//username = "sa"
	//password = ""
    
	/*
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQLInnoDBDialect"
	username = "test"
	password = "test"
	searchpath = "test"
	ssl = false
	*/
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {
    development {
        dataSource {
			
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            //url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE;INIT=CREATE SCHEMA IF NOT EXISTS REALESTATE"
			url = "jdbc:h2:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE;INIT=CREATE SCHEMA IF NOT EXISTS REALESTATE"
            
			/**
			dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
			//url = "jdbc:mysql://78.35.168.147:42263/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true"
			//url = "jdbc:mysql://78.35.168.147:42263/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true"
			url = "jdbc:mysql://mybigdata.ddns.net:42263/test"
			*/
        }
    }
    test {
        dataSource {
			/*
            dbCreate = "update"
            //url = "jdbc:mysql://78.35.168.147:42263/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true"
			url = "jdbc:mysql://mybigdata.ddns.net:42263/test"
			*/
			
			dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:h2:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE;INIT=CREATE SCHEMA IF NOT EXISTS REALESTATE"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            properties {
               // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
               jmxEnabled = true
               initialSize = 5
               maxActive = 50
               minIdle = 5
               maxIdle = 25
               maxWait = 10000
               maxAge = 10 * 60000
               timeBetweenEvictionRunsMillis = 5000
               minEvictableIdleTimeMillis = 60000
               validationQuery = "SELECT 1"
               validationQueryTimeout = 3
               validationInterval = 15000
               testOnBorrow = true
               testWhileIdle = true
               testOnReturn = false
               jdbcInterceptors = "ConnectionState"
               defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
            }
        }
    }
}
