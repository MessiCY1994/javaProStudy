# javaProStudy
mybatis设计流程
1.接口设计---SqlSession接口（CRUD的方法）、DefaultSqlSession实现类
		SqlSession接口
			Object selectOne(String statementId，Object object)
			List<Object> selectList(String statementId，，Object object)
			void insert(String statementId，，Object object)
	2.配置文件编写？(直接参考mybatis本身配置文件的编写)
		全局配置文件如何编写
			配置数据源ds
		mapper映射文件如何编写（配置SQL语句，一个SQL语句对应一个statement执行，每个statement都有一个唯一的id）			
			SQL语句
			参数
			映射对象
	
	3.读取全局配置文件，将全局配置文件信息封装到一个对象中（Configuration）
		
		Configuration
			DataSource信息
			Map<String,MappedStatement>	 
	4.读取映射配置文件
		很多个MappedStatement对象（对应映射文件中的一个select标签）
			SQL语句
			statement类型
			输入参数java类型
			输出结果java类型
	4.接口实现类的功能实现？（读取配置文件的工作，一定是提前完成，而且只需要完成一次）
		a).获取连接
			//读取配置文件，获取数据源对象，根据数据源获取连接
			通过Configuration对象获取DataSource对象
			通过DataSource对象，获取Connection
		b).执行statement操作（要考虑是执行哪种Statement，不同的statement，操作不同，参数设置也不同，结果集处理也不同）
			获取statement的类型
				//读取映射配置文件，获取要执行的SQL语句的statement类型（statement、preparedstatement、callablestatement）
				根据statement的ID，去Configuration对象中查找对应的Statement对象
				通过MappedStatement对象获取statementType类型
			//如果是preparedstatement
			
			//读取映射配置文件，获取要执行的SQL语句
			通过MappedStatement对象获取SQL语句（SQL语句的获取需要仔细处理）
				SELECT * FROM user where id = #{id} and username = #{username}
				
				SQL语句：		SELECT * FROM user where id = ? and username= ?
				解析占位符参数：List<ParameterMapping>		
									ParameterMapping(参数名称)
									解析#{}中的参数名称：id
			给SQL语句设置参数
				遍历List<ParameterMapping>挨个处理入参
					获取入参的Java类型，根据类型（8种基本类型、String类型、POJO类型等）判断如何获取参数值
						比如说如果是Integer类型，则只有将入参对象直接赋值给SQL语句即可
							preparedStatement.setObject(1, "王五");
						如果是POJO类型，通过反射根据参数名称获取POJO对象的属性值
							preparedStatement.setObject(1, "王五");

			执行statement
				rs = preparedStatement.executeQuery();
			处理结果集
				获取要封装的java对象类型（Class对象）
					通过MappedStatement对象获取结果映射的Java类型
					
				遍历结果集，取出结果集中的每条结果的列名
					通过rs获取metaData（列名）
					
				根据列名通过反射获取java对象中的field名称
					要求：SQL语句的列名一定要和java对象中的属性名称一致。
				通过反射给指定field赋值
	
	4.配置文件解析
		dom4j---第三方
		指定要解析的配置文件的路径（类路径、磁盘路径、网络路径）---Resource
		通过类加载器去指定路径加载，放入InputStream流对象
			读取Source资源中的数据
		通过InputStream流对象，去创建Document对象（dom4j）---此时没有针对xml文件中的语义进行解析
			DocumentReader---去加载InputStream流，创建Document对象的
		进行mybatis语义解析（全局配置文件语义解析、映射文件语义解析）
			XMLConfigParser---解析全局配置文件
			XMLMapperParser---解析全局配置文件
	5.SqlSession对象创建？
		使用工厂模式创建----SqlSessionFactory
	6.工厂对象创建
		使用构建者模式创建---SqlSessionFactoryBuiler
