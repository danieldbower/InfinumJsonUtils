class JsonUtilsGrailsPlugin {
    // the plugin version
    def version = "0.4"
    def groupId = 'com.avisocoaching'
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.5 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Json Utils Plugin" // Headline display name of the plugin
    def author = "Daniel Bower"
    def authorEmail = "daniel.bower@avisocoaching.com"
    def description = '''\
Helper classes for working with json apis
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/json-utils"

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Aviso Coaching", url: "http://avisocoaching.com/" ]

}
