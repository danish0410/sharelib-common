package org.commonlib

class BuildUtils implements Serializable {
    def steps  // Injected Jenkins steps like echo, bat, etc.

    BuildUtils(steps) {
        this.steps = steps
    }

    def build(String appName, String appType, String port) {
        steps.echo "🔧 Starting build for application: ${appName}"
        steps.echo "📦 Application Type : ${appType}"
        steps.echo "🚪 Default Port      : ${port}"

        switch (appType?.toLowerCase()) {
            case 'springboot':
                steps.echo "☕ Detected Spring Boot app"
                steps.bat "mvn clean package -DskipTests"
                steps.bat "docker build -t ${appName}:latest ."
                break

            case 'nginx':
                steps.echo "🌐 Detected Nginx app"
                steps.bat "docker build -t ${appName}:latest ."
                break

            case 'php':
                steps.echo "🐘 Detected PHP app"
                steps.bat "docker build -t ${appName}:latest ."
                break

            default:
                steps.error "❌ Unknown application type: ${appType}"
        }

        steps.echo "✅ Build completed for ${appName} (${appType})"
    }
}
