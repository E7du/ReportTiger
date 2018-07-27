## ReportTiger 数据统计服务 ##

系统用于移动端的数据统计分析。

系统工作流程：
    
- 1. PC端创建App，获得AppSecretKey；
- 2. PC端创建统计Code；
- 3. 移动端（iOS，Android）使用AppSecretKey获取ReportTiger服务的授权；
- 4. 移动端（iOS，Android）使用Tiger客户端SDK(后续会开源)根据Code记录数据；

ReportTiger使用说明

- 1. 使用前先设置Consts.DEFAULT_APP_PACKAGE_NAME(默认为cn.zhucongqi.) 以及Consts.TABLE_NAME_PREFIX(默认为report_tiger_)；
- 2. 导入report_tiger_db.sql到您的数据库；
- 3. 配置conf/jf-app-cfg.conf中数据库部分的配置；
- 4. PC端创建的Code是指对应的统计项数据的别名，比如要统计用户的登录情况，那么Code可以设置为login，统计用户的区域分类情况， 可以设置Code为user等；
- 5. PC端创建Code之后处理，ReportTiger会根据code生成Consts.TABLE_HASH_SIZE*2+2个数据库表，分别是Platform(iOS,Android)对应的Consts.TABLE_HASH_SIZE个数据库表，这些数据表主要用于分不同的平台来储存数据，还会生成Platform对应的info表，这个表用于记录对应Platform的hashid的记录情况；
- 6. 记录数据的处理规则，先在code_info中写入一条数据，获得对应的id，将此id作为code_data的id且把对应的数据写入到data field；