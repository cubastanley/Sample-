pipeline {
	agent {docker {image 'maven:3.6.0'}}
	stages {
		stage('build') {
			steps {
				sh 'mvn clean install'
				sh 'mvn clean install -Ppayara-data-repair'
			}
		}
	}
	post {
		success {
			mail to: 'cuba.stanley@payara.fish',
				subject: "Build success on Pipeline: ${currentBuild.fullDisplayName}",
				body: "The build for pipeline ${currentBuild.fullDisplayName} was successful!"
		}
		failure {
			mail to: 'cuba.stanley@payara.fish',
				subject: "Build failure on Pipeline: ${currentBuild.fullDisplayName}",
				body: "There were build errors in the pipeline ${env.BUILD_URL}"
		}
	}
}
