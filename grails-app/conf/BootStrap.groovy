import grails.util.GrailsUtil
import groovy.sql.Sql
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class BootStrap {

    def dataSource
    //        def dataSource = servletContext.getBean("dataSource");

    def init = { servletContext ->

        println 'bootstrap'
        switch (GrailsUtil.environment) {
            case "test":
                println 'test'



                String sqlFilePath = '/home/developer/fpa-201407230101-ProjectCalidadInspecciones-FullClean.sql'
                String sqlString = new File(sqlFilePath).text
                def sql = Sql.newInstance(ConfigurationHolder.config.dataSource.url, ConfigurationHolder.config.dataSource.username, ConfigurationHolder.config.dataSource.password, ConfigurationHolder.config.dataSource.driverClassName)
//                def sql = Sql.newInstance( dataSource)
                sql.execute(sqlString)

                println "Datasource:"+dataSource

//                String sqlFilePath2 = '/home/developer/dumps/Dump20140724-FPAFull/fpa_adjustment_factor.sql'
//                String sqlString2 = new File(sqlFilePath2).text
//                def sql2 = Sql.newInstance( dataSource)
//                sql2.execute(sqlString2)


                break
        }
    }
    def destroy = {
    }
}
