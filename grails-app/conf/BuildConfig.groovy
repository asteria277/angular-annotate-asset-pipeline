grails.project.work.dir = 'target'

grails.project.fork = false

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    String gebVersion = '0.9.3'
    String seleniumVersion = '2.42.2'

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        test "org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
        test "org.seleniumhq.selenium:selenium-support:$seleniumVersion"
        test "org.gebish:geb-spock:${gebVersion}"

        test "org.seleniumhq.selenium:selenium-remote-driver:$seleniumVersion"
        test "org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion"
    }
    plugins {
        runtime(":asset-pipeline:1.9.6") {
            export = false
        }

        build(":release:3.0.1",
                ":rest-client-builder:1.0.3",
                ":tomcat:7.0.53") {
            export = false
        }

        test ":geb:$gebVersion"
    }
}
