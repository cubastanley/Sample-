pipeline {
	agent {docker {image 'maven:3.6.0'}}
	stages {
		stage('Build') {
			steps {
				echo '~~~~~~~~~~ Starting Build ~~~~~~~~~~~'
				sh 'mvn clean install -DskipTests'
			}
		}
		stage('Test') {
			steps {
				echo '~~~~~~~~~~ Running Tests ~~~~~~~~~~'
				sh 'mvn test'
			}
		}
		stage('Deploy') {
			steps {
				echo '~~~~~~~~~~ Deploying Applications ~~~~~~~~~~'
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
