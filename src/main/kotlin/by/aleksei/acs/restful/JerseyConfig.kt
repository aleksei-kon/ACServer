package by.aleksei.acs.restful

import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class JerseyConfig : ResourceConfig() {

    init {
        register(MultiPartFeature::class.java)
        packages(JerseyConfig::class.java.`package`.name)
    }
}