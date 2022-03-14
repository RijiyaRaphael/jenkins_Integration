#!/usr/bin/env groovy
node {

    stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/RijiyaRaphael/jenkins_Integration.git",
                                credentialsId: "rijiya"
                        ]
                ],
                branches: [[name: "main"]]
        ], poll: false
    }

    stage('Complete any setup steps') {
        echo "Complete set-up steps"
        echo "${octoperf_test_value}"
    }

    stage('Execute Performance Tests') {
        dir("${WORKSPACE}/scripts") {
            bat "F:\apache-jmeter-5.3\apache-jmeter-5.3\bin\jmeter  -n -t jmeterdemo.jmx -l dmo4.jtl -Joptestvalue=${octoperf_test_value}"
        }
    }

    stage('Analyse Results') {
        echo "Analyse results"
    }
}
