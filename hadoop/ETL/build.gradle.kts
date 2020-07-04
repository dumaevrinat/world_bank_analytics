import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import java.sql.DriverManager

plugins {
    kotlin("jvm") version "1.3.72"
}

group = "FLS"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies{
    compileOnly("org.apache.hive:hive-exec:3.1.1")
    compileOnly("org.apache.hive:hive-jdbc:3.1.1")
    runtimeOnly("org.apache.hive:hive-jdbc:3.1.1")
}

buildscript{
    repositories {
        mavenCentral()
    }
    dependencies{
        classpath("org.apache.hive","hive-jdbc","3.1.1")
    }
}

tasks.register("selectFromTable"){
    doLast{
        try{
            val jsch = JSch()
            val session: Session = jsch.getSession("aivanov", "195.209.231.228", 20322)
            session.setPassword("aiva1914Pass")
            session.setConfig("StrictHostKeyChecking", "no")
            session.connect()
            session.setPortForwardingL(5555, "localhost", 10000)

            Class.forName("org.apache.hive.jdbc.HiveDriver")
            /*for (i in 5001..6000) {
                try {
                    session.setPortForwardingL(11000 + i - 1023, "localhost", i)
                    val url: String = "jdbc:hive2://localhost:" + i + "/wb_prediction"
                    val connection = DriverManager.getConnection(url, "hive_user", "hive_password")

                    print(i)
                    println(" Success!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                } catch(e: java.sql.SQLException){
                    //print(i)
                    //println(" Fail")
                } finally {

                }
            }*/

            val connection = DriverManager.getConnection("jdbc:hive2://localhost:5555/wb_prediction", "hive_user", "hive_password")
            val statement = connection.createStatement()
            val respond = statement.executeQuery("select * from education")

            val columnsNumber: Int = respond.getMetaData().getColumnCount()

            while (respond.next()) {
                for (i in 1..columnsNumber) {
                    print(respond.getString(i).toString() + " ")
                }
                println()
            }

            respond.close()
            statement.close()
            connection.close()
            session.disconnect()
        } catch(e: java.sql.SQLException){
            println(e.message)
        }
    }
}