package group.msg.at.cloud.cloudtrain.adapter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * {@code Factory} for {@code UriBuilder}s which are aware of routers that forwarded the current request.
 * <p>
 * Use this class instead of the factory methods provided by {@link UriInfo} or {@link UriBuilder}, if your service
 * is running behind a router that uses path-based routing.
 * </p>
 * @TODO: clarify if this factory can be transformed into a @RequestScoped bean with injectable UriInfo and HttpHeaders
 */
public class RouterAwareUriBuilderFactory {

    public static UriBuilder from(UriInfo uriInfo, HttpHeaders httpHeaders) {
        URI actualUri = null;
        URI requestUri = uriInfo.getRequestUri();
        Optional<String> forwardedPrefix = getHeaderByNameIgnoringCase(httpHeaders, "X-Forwarded-Prefix");
        Optional<String> forwardedProtocol = getHeaderByNameIgnoringCase(httpHeaders,"X-Forwarded-Proto");
        if (forwardedPrefix.isPresent() || forwardedProtocol.isPresent()) {
            try {
                actualUri = new URI(forwardedProtocol.orElse(requestUri.getScheme()), requestUri.getAuthority(), forwardedPrefix.orElse("") + requestUri.getPath(), requestUri.getQuery(), requestUri.getFragment());
            } catch (URISyntaxException ex) {
                throw new IllegalStateException(String.format("failed to create a external URI from request URI [%s] and router path prefix [%s]", requestUri, forwardedPrefix));
            }
        }
        return UriBuilder.fromUri(actualUri != null ? actualUri : requestUri);
    }

    private static Optional<String> getHeaderByNameIgnoringCase(HttpHeaders headers, String name) {
        String result = null;
        result = headers.getHeaderString(name);
        if (result == null) {
            result = headers.getHeaderString(name.toUpperCase());
        }
        if (result == null) {
            result = headers.getHeaderString(name.toLowerCase());
        }
        return Optional.ofNullable(result);
    }
}
