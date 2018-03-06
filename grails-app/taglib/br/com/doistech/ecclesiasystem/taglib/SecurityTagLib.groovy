package br.com.doistech.ecclesiasystem.taglib

class SecurityTagLib {
    static namespace = "ipresec"

    def springSecurityService

    def redirectPage = { attrs ->
        def url = attrs.get('url')
        response.sendRedirect("${request.contextPath}" + url)
    }

//    def reportAppLink = { attrs ->
//        String hostTarget = attrs.get('hostTarget')
//        def url = attrs.get('url')
//        def text = attrs.get('text')
//        def type = attrs.get('type')
//        if (type == null || type == "") type = "0"
//
//        if (springSecurityService.isLoggedIn()) {
//            /// Get user details
//            def user = springSecurityService.principal
//            UserProfile usr = UserProfile.get(user.id)
//            String username = usr?.username
//            String encryptedUser = SecurityUtils.encrypt(username)
//
//            // Get ServerName, port and service
//            Boolean includePort = true
//            String scheme = request.getScheme()
//            String serverName = request.getServerName()
//            int serverPort = (new org.springframework.security.web.PortResolverImpl()).getServerPort(request)
//            Boolean inHttp = "http".equals(scheme.toLowerCase())
//            Boolean inHttps = "https".equals(scheme.toLowerCase())
//
//            // build server call
//            if (inHttp && (serverPort == 80)) {
//                includePort = false;
//            } else if (inHttps && (serverPort == 443)) {
//                includePort = false;
//            }
//            String urlParams = "${url}?whoami=${encryptedUser}"
//            String hostOrigin = scheme + "://" + serverName + ((includePort) ? (":" + serverPort) : "")
//            if (hostTarget == null) hostTarget = hostOrigin
//            String hostOriginEncripted = SecurityUtils.encrypt(hostOrigin)
//
//            String redirectUrl = hostTarget + "${url}?whoami=${encryptedUser}&cbh=${hostOriginEncripted}&type=${type}"
//
//            out << "<a href=${redirectUrl}>${text}</a>"
//        }
//    }
//
//    def userProfileName = { attrs ->
//        if (springSecurityService.isLoggedIn(
}
