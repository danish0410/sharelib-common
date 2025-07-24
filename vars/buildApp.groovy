def call(Map config = [:]) {
    def appName = config.get('appName', 'sample-app')
    def appType = config.get('appType', 'springboot')
    def port = config.get('port', '8080')

    def utils = new org.commonlib.BuildUtils(this)
    utils.build(appName, appType, port)
}
