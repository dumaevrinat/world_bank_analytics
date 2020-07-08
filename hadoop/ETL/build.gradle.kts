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
            Class.forName("org.apache.hive.jdbc.HiveDriver")

            val connection = DriverManager.getConnection("jdbc:hive2://localhost:10000/wb_prediction", "hive_user", "hive_password")
            val statement = connection.createStatement()
            val respond = statement.executeQuery("select * from education")

            val columnsNumber: Int = respond.getMetaData().getColumnCount()

            while (respond.next()) {
                print("| ")
                for (i in 1..columnsNumber) {
                    print(i.toString() + ": " + respond.getString(i).toString() + " | ")
                }
                println()
            }

            print("| ")
            for (i in 1..columnsNumber) {
                print(i.toString() + ": " + respond.getMetaData().getColumnLabel(i) + " | ")
            }

            respond.close()
            statement.close()
            connection.close()
        } catch(e: java.sql.SQLException){
            println(e.message)
        }
    }
}