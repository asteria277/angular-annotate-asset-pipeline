grails.project.work.dir = 'target'

grails.project.fork = [
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    String gebVersion = '0.9.2'
    String seleniumVersion = '2.39.0'
    String spockVersion = '0.7'

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
        runtime(":asset-pipeline:1.8.11") {
            export = false
        }

        build(":tomcat:$grailsVersion",
                ":release:2.2.1",
                ":rest-client-builder:1.0.3") {
            export = false
        }

        test ":geb:$gebVersion"
        test(":spock:$spockVersion") {
            exclude "spock-grails-support"
        }
    }
}
