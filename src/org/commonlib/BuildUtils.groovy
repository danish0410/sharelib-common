package org.commonlib

class BuildUtils implements Serializable {
    def steps

    BuildUtils(steps) {
        this.steps = steps
    }

    def build(Map config) {
        def appType = config.appType?.toLowerCase()
        switch(appType) {
            case 'springboot':
                steps.echo "🔨 Building Spring Boot App: ${config.appName}"
                steps.sh "mvn clean package -f ${config.pathToPom ?: '.'}/pom.xml"
                break
            case 'nginx':
                steps.echo "📦 Building Nginx App: ${config.appName}"
                steps.sh "docker build -t ${config.appName}:latest ${config.context ?: '.'}"
                break
            case 'php':
                steps.echo "🚀 Deploying PHP App: ${config.appName}"
                steps.sh "cp -r ${config.source ?: '.'} /var/www/html/${config.appName}"
                break
            default:
                steps.error "❌ Unsupported appType: ${appType}"
        }
    }
}
